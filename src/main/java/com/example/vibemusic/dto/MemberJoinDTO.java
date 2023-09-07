package com.example.vibemusic.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid, mpw, email, phone, address, birthDate, name;
    private boolean del, social;
    private Long pl_no;
}
