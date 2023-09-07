package com.example.vibemusic.service;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AnswerService {


    AnswerDTO answerRead(Long ano);

    Long answerRegister(AnswerDTO answerDTO);

    void answerModify(AnswerDTO answerDTO);

    void answerRemove(Long ano);

    Page<Answer> answerListOfQuestion(Long qno, Pageable pageable);

    PageResponseDTO<AnswerDTO> getListOfQuestion(Long qno, PageRequestDTO pageRequestDTO);




}
