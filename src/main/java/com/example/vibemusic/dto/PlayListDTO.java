package com.example.vibemusic.dto;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.Music;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayListDTO {
    private Long plNo;
    private String plName;
    private Member member;
    private List<Music> musics;

    private List<MusicDTO> musicList;
    public List<MusicDTO> getMusicList() {
        return musicList;
    }

}
