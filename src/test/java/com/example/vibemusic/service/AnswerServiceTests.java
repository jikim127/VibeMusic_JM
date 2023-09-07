package com.example.vibemusic.service;

import com.example.vibemusic.domain.Answer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {
    @Autowired
    private AnswerService answerService;

    @Test
    public void testAnswerList() {
        Long ano = 50L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("ano").ascending());

        Page<Answer> result = answerService.answerListOfQuestion(ano,pageable);

        result.getContent().forEach(answer -> log.info("answer =============> : {}",answer.getAnswerText()));
    }
}
