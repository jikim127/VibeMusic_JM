package com.example.vibemusic.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long u_no;
    private String u_id;
    private String u_name;
    private String u_password;
    private String u_phone;
    private String u_email;
    private Date u_birthDate;
    private boolean u_adult;

}
