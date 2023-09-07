package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Question;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.QuestionDTO;
import com.example.vibemusic.service.AnswerService;
import com.example.vibemusic.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionMod")
@Log4j2
@RequiredArgsConstructor
public class QuestModController {
    private final QuestionService questionService;


    @ApiOperation(value = "Read questions", notes = "GET 방식으로 특정 게시글 조회")
    @GetMapping("/{qno}")
    public QuestionDTO getQuestionDTO(@PathVariable("qno") Long qno) {

        QuestionDTO questionDTO = questionService.read1Quest(qno);
        return questionDTO;
    }
}


