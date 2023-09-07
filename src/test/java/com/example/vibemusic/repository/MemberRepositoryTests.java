package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email"+i+"@naver.com")
                    .build();

            member.addRole(MemberRole.USER);

            if(i >= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.getWithRoles("member100");
        Member member = result.orElseThrow();
        log.info("member : {}",member);
        log.info("member.getRoleSet : {}",member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info("memberRole.name : {}",memberRole.name()));
    }

    @Commit
    @Test
    public void testUpdate() {

        String mid = "berry1699";
        String mpw = passwordEncoder.encode("$2a$10$4TJ2vlrkTpeknfOgDcI8TuzBWTSL/KH78nDo9MYLPZxaHZ3FwBwXC");

        memberRepository.updatePassword(mpw, mid);
    }


}
