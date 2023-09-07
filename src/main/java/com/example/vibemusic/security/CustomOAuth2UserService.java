package com.example.vibemusic.security;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.MemberRole;
import com.example.vibemusic.repository.MemberRepository;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest..........");
        log.info("userRequest : {}",userRequest);

        log.info("oauth2 user..............................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME : {}", clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName){
            case "kakao" :
                email = getKakaoEmail(paramMap);
                break;
            case "Google" :
                email = getGoogleEmail(paramMap);
                break;
        }

        log.info("===================================================");
        log.info("email : {}", email);
        log.info("===================================================");

        return generateDTO(email, paramMap);
    }

    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params){
        Optional<Member> result = memberRepository.findByEmail(email);

        String phone = (String) params.get("phone");
        String address = (String) params.get("address");
        String birthDate = (String) params.get("birthDate");
        String name = (String) params.get("name");
        if(name == null){
            name = (String) params.get("nickname");
        }
        if(birthDate == null){
            birthDate = (String) params.get("birthday");
        }

        String[] parts = email.split("@");
        String mid = parts[0];

        log.info("phone : {}", phone);
        log.info("address : {}", address);
        log.info("birthDate : {}", birthDate);
        log.info("name : {}", name);

        if(result.isEmpty()){
            Member member = Member.builder()
                    .mid(mid)
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .phone(phone)
                    .address(address)
                    .birthDate(birthDate)
                    .name(name)
                    .build();
            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email,"1111",email, phone, address, birthDate, name, false, true,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

            memberSecurityDTO.setProps(params);

            return memberSecurityDTO;
        }else {
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    member.getMid(),
                    member.getMpw(),
                    member.getEmail(),
                    member.getPhone(),
                    member.getAddress(),
                    member.getBirthDate(),
                    member.getName(),
                    member.isDel(),
                    member.isSocial(),
                    member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList())
            );
            return memberSecurityDTO;
        }
    }

    private String getKakaoEmail(Map<String, Object> paramMap){
        log.info("KAKAO---------------------------------------------");

        Object value = paramMap.get("kakao_account");

        log.info("value : {}", value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String) accountMap.get("email");

        log.info("email : {}", email);

        return email;
    }


    private String getGoogleEmail(Map<String, Object> paramMap){
        log.info("GOOGLE==============================");
        String value = (String) paramMap.get("email");

        log.info("value : {}", value);

//        LinkedHashMap accountMap = (LinkedHashMap) value;
//
//        String email = (String) accountMap.get("email");
//
//        log.info("email : {}", email);

        return value;
    }
}
