package com.example.vibemusic.domain;

import com.example.vibemusic.dto.MemberLoginDTO;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"roleSet", "playList"}) // 출력에서 playList와 roleSet 필드를 제외
public class Member extends BaseEntity{

    @Id
    private String mid;

    @Column(nullable = false)
    private String mpw;

    @Column(nullable = false)
    private String email;

    private String phone, address, birthDate, name;

    private boolean del;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private PlayList playList;

    public void changePassword(String mpw){
        this.mpw = mpw;
    }

    public void changeAllInformation(MemberLoginDTO memberLoginDTO) {
        this.phone = memberLoginDTO.getPhone();
        this.address = memberLoginDTO.getAddress();
        this.birthDate = memberLoginDTO.getBirthDate();
        this.name = memberLoginDTO.getName();
//        this.mpw = memberLoginDTO.getMpw();
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){
        this.social = social;
    }

}
