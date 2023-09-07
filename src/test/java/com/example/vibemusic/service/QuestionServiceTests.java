package com.example.vibemusic.service;

import com.example.vibemusic.domain.Question;
import com.example.vibemusic.repository.QuestionRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.IntStream;
@SpringBootTest

@Log4j2
class QuestionServiceTests {
    @Autowired
    private QuestionRepository questionRepository;


    @MockBean
    private QuestionService questionService; // Mock the QuestionService dependency


    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Question question = Question.builder()
                    .qTitle("질문..." + i)
                    .qContent("질문내용..." + i)
                    .qWriter("질문자" + (i % 50))
                    .build();
            Question result = questionRepository.save(question);
            log.info("QNO : " + result.getQno());

        });

    }

    @Test
    public void testRead() {
        Optional<Question> byId = questionRepository.findById(1L);
        Question question = byId.orElseThrow();
  }


    @Test
    public void testUpdate(){

        Long qno = 50L;

        Optional<Question> result = questionRepository.findById(qno);

        Question question = result.orElseThrow();

        question.change("수정잘되는데요?", "근데왜 html은");

        questionRepository.save(question);//boardRepository로 board에 저장


    }

    }