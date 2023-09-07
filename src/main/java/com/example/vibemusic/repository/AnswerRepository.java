package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswerByQuestion_qno(Long qno);

    @Query("select a from Answer a where a.question.qno = :qno")
    Page<Answer> answerListOfQuestion(Long qno, Pageable pageable);
}