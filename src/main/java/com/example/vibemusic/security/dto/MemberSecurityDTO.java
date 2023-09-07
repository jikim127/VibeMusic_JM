package com.example.vibemusic.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User implements OAuth2User {

    private Map<String, Object> props;

    private String mid, mpw, email, phone, address, birthDate, name;
    private boolean del, social;

    public MemberSecurityDTO(String username, String password, String email, String phone,
                             String address, String birthDate, String name,
                             boolean del, boolean social, Collection<? extends GrantedAuthority> authorities){
        super(username,password,authorities);

        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.name = name;
        this.del = del;
        this.social = social;

    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    @Override
    public String getName() {
        return this.mid;
    }
}
