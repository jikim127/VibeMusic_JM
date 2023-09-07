package com.example.vibemusic.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTO {

    private Long qno;
    @NotEmpty
    @Size(min = 3, max = 100)
    private String qTitle;
    @NotEmpty
    private String qContent;

    private String qImage;
    @NotEmpty
    private String qWriter;

    private int qViewCount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
}
