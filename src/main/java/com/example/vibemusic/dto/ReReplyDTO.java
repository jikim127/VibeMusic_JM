package com.example.vibemusic.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReReplyDTO {
    private Long rr_no;
    private String rr_replyer;
    private String rr_replyText;
    private Date rr_regDate;
    private Date rr_modDate;
    // 필요한 경우 다른 관계 필드도 추가
}
