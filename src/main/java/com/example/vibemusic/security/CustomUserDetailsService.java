package com.example.vibemusic.security;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.repository.MemberRepository;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : {}",username);

        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty())    {
            throw new UsernameNotFoundException("username not found....");
        }

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
                false,
                member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())).collect(Collectors.toList())
        );
        log.info("memberSecurityDTO : {}", memberSecurityDTO);

        return memberSecurityDTO;
    }
}
