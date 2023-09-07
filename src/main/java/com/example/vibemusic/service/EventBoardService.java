package com.example.vibemusic.service;

import com.example.vibemusic.dto.EventBoardDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;

public interface EventBoardService {

    Long register(EventBoardDTO eventBoardDTO);
    EventBoardDTO select(Long ebno);
//    EventBoardDTO selectAll(Long ebno);
    void modify(EventBoardDTO eventBoardDTO);
    void remove(Long ebno);
    PageResponseDTO<EventBoardDTO> list(PageRequestDTO pageRequestDTO);
//    Page<EventBoard> Elist(Pageable pageable);
    void eViewCount(Long ebno);

}
