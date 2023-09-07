package com.example.vibemusic.dto;

import com.example.vibemusic.domain.MemberRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSessionDTO {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
    private boolean isAdmin; // 관리자 여부를 나타내는 필드

    // 생성자, 게터 및 세터 등의 필요한 메서드 추가

    public boolean isAdmin() {
        // roles에 ADMIN 역할이 포함되어 있는지 여부를 판단하여 isAdmin 값을 설정
        return roles != null && roles.contains(MemberRole.ADMIN);
    }

}
