package com.example.vibemusic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long rno;

    @NotEmpty
    public String rreplyer;

    @NotEmpty
    private String r_replyText;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @JsonIgnore
    private LocalDateTime modDate;

    @NotNull
    private Long no;
    // 필요한 경우 다른 관계 필드도 추가
}
