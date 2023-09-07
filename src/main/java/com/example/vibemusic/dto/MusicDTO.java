package com.example.vibemusic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MusicDTO {
    private Long no;
    private String m_sound;
    private String m_title;
    private String m_artist;
    private String m_genre;
    private int rDate;
    private String m_playtime;
    private String m_image;
    private int mPlayCount;
    private String m_Recommend;

}