package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.dto.MusicDTO;

import java.util.List;

public interface ChartService {

    List<MusicDTO> DanceGenre(Long no);

    List<MusicDTO> BalladeGenre(Long no);

    List<MusicDTO> HipHopGenre(Long no);

    List<MusicDTO> PopGenre(Long no);

    public List<Music> getAllMusicSortedByPlayCount();

    List<MusicDTO> RandomDanceGenre(Long no);

    List<MusicDTO> RandomPopGenre(Long no);

    List<MusicDTO> RandomHipHopGenre(Long no);

    List<MusicDTO> RandomBalladeGenre(Long no);
}
