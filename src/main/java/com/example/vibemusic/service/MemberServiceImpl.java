package com.example.vibemusic.service;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.MemberRole;
import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.dto.MemberLoginDTO;
import com.example.vibemusic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member findByUsername(String username) {

        Member byMid = memberRepository.findByMid(username);

        return byMid;
    }

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("==================================================");
        log.info("member : {}",member);
        log.info("member.getRole set : {}", member.getRoleSet());

        memberRepository.save(member);
    }

    @Override
    public void login(MemberLoginDTO memberLoginDTO) throws MidExistException {
        String mid = memberLoginDTO.getMid();
        boolean exist = memberRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberLoginDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberLoginDTO.getMpw()));

        memberRepository.getWithRoles(mid);

    }

    @Override
    @Transactional
    public void modifyInformation(MemberLoginDTO memberLoginDTO) throws MidExistException {
        String mid = memberLoginDTO.getMid();

        log.info("Service Mid : {}", mid);
        boolean exist = memberRepository.existsById(mid);
        log.info("ID 존재 여부  : {}", exist);

        if(!exist){
            throw new MidExistException();
        }

        Optional<Member> byId = memberRepository.findById(mid);
        Member member = byId.orElseThrow();
        member.changeAllInformation(memberLoginDTO);
        member.changePassword(passwordEncoder.encode(memberLoginDTO.getMpw()));
        memberRepository.save(member);

//        String phone = memberLoginDTO.getPhone();
//        String address = memberLoginDTO.getAddress();
//        String birthDate = memberLoginDTO.getBirthDate();
//        String name = memberLoginDTO.getName();

//        memberRepository.updateInformation(phone, address, birthDate, name, mid);

    }
}
