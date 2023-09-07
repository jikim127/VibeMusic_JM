package com.example.vibemusic.service;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;

import java.util.List;

public interface MusicService {
    MusicDTO readOne(Long bno);

    //music_no 기준 내림차순 정렬
    PageResponseDTO<MusicDTO> listWithPaging(PageRequestDTO pageRequestDTO);


    // rdate 정렬기준 (최신순)
    PageResponseDTO<MusicDTO> listWithNewMusic(PageRequestDTO pageRequestDTO);


    // 조회수 기능
    void increaseViewCount(Long no);

    List<MusicDTO> getAllMusic();



}
