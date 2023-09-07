package com.example.vibemusic.service;

import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.ReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> replyListWithPaging(PageRequestDTO pageRequestDTO);


    Page<Reply> replyListOfMusic(Long no, Pageable pageable);

    PageResponseDTO getListOfMusic(Long no, PageRequestDTO pageRequestDTO);

}
