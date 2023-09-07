package com.example.vibemusic.service;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.domain.Question;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.repository.AnswerRepository;
import com.example.vibemusic.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Log4j2
@Transactional

public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long answerRegister(AnswerDTO answerDTO) {
        Optional<Question> byId = questionRepository.findById(answerDTO.getQno());
        Question question = byId.orElseThrow();
        Answer answer = Answer.builder()
                .question(question)
                .answerer(answerDTO.getAnswerer())
                .answerText(answerDTO.getAnswerText())
                .build();

        Long ano = answerRepository.save(answer).getAno();
        return ano;
    }

    @Override
    public Page<Answer> answerListOfQuestion(Long qno, Pageable pageable) {
        return answerRepository.answerListOfQuestion(qno, pageable);
    }

    @Override
    public AnswerDTO answerRead(Long ano) {

        Optional<Answer> answerOptional = answerRepository.findById(ano);

        Answer answer = answerOptional.orElseThrow();

        return modelMapper.map(answer, AnswerDTO.class);
    }

//    @Override
//    public AnswerDTO answerRead(Long ano) {
//        Optional<Answer> answerOptional = answerRepository.findById(ano);
//        Answer answer = answerOptional.orElseThrow();
//
//        String username = null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            username = authentication.getName();
//        }
//
//        if (username != null && !answer.getAnswerer().equals(username)) {
//            // 작성자 아이디와 로그인한 사용자 아이디가 일치하지 않을 경우
//            AnswerDTO secretAnswer = new AnswerDTO();
//            secretAnswer.setAnswerText("비밀댓글입니다");
//            return secretAnswer;        }
//
//        return modelMapper.map(answer, AnswerDTO.class);
//    }

    @Override
    public void answerModify(AnswerDTO answerDTO) {

        Optional<Answer> answerOptional = answerRepository.findById(answerDTO.getAno());

        Answer answer = answerOptional.orElseThrow();

        answer.changeText(answerDTO.getAnswerText());

        answerRepository.save(answer);

    }

    @Override
    public void answerRemove(Long ano) {

        answerRepository.deleteById(ano);

    }

    @Override
    public PageResponseDTO<AnswerDTO> getListOfQuestion(Long qno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("ano").ascending());

        Page<Answer> result = answerRepository.answerListOfQuestion(qno, pageable);

        List<AnswerDTO> dtoList =
                result.getContent().stream().map(answer -> modelMapper.map(answer, AnswerDTO.class))
                        .collect(Collectors.toList());

        return PageResponseDTO.<AnswerDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
