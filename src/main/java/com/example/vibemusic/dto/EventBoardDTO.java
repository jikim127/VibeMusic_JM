package com.example.vibemusic.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventBoardDTO {

    private Long ebno;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
