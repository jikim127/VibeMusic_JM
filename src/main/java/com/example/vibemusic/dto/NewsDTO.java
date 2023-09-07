package com.example.vibemusic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewsDTO {

    private Long nNo;
    private String nTitle;
    private String nContent;
    private String nImage;
    private String nNewsLinks;
    private String nFullNews;
    private int nViewCount;
    private String nRegDate;

}
