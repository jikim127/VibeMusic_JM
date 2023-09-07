package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.domain.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class AnswerRepositoryTests {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testInsert() {

        Long qno = 10L;
        IntStream.rangeClosed(1, 50).forEach(i -> {

            Question question = Question.builder().qno(qno).build();

            Answer answer = Answer.builder()
                    .question(question)
                    .answerText("답변2")
                    .answerer("답변자")
                    .build();

            answerRepository.save(answer);
            log.info("answer:" + answer);
        });
    }
    @Test
    public void readAll() {
       answerRepository.findAnswerByQuestion_qno(50L).forEach(answer -> log.info("answer : {}",answer.getAno()));
    }

    @Test
    public void testDelete() {
        answerRepository.deleteById(2L);
    }

    @Test
    public void testModify() {
        Optional<Answer> byId = answerRepository.findById(1L);
        Answer answer = byId.orElseThrow();
        answer.changeText("개추개추개추개추개추");
        answerRepository.save(answer);

        log.info("answer:"+answer);
    }

    @Test
    public void testQuestReplies() {
        Long qno = 1L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("ano").descending());

        Page<Answer> result = answerRepository.answerListOfQuestion(qno,pageable);

        result.getContent().forEach(answer -> log.info("answer : {}",answer));
    }

}