package com.example.vibemusic.repository;

import com.example.vibemusic.domain.EventBoard;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class EventBoardRepositoryTests {

    @Autowired
    private EventBoardRepository eventBoardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,20).forEach(i -> {
            EventBoard eventBoard = EventBoard.builder()
                .title("안녕안녕나는또히야"+i)
                .content("헬륨가스 먹었더니 집가고싶지"+i)
                .writer("또히"+i)
                .build();

            EventBoard io = eventBoardRepository.save(eventBoard);

            log.info("io================>"+io);
        });
    }

    @Test
    public void testRead() {
        Long ebno = 121L;
        Optional<EventBoard> ro = eventBoardRepository.findById(ebno);

        log.info("ro=======>"+ro);
    }

    @Test
    public void testUpdate() {
        Long ebno = 125L;

        Optional<EventBoard> uo = eventBoardRepository.findById(ebno);

        EventBoard eventBoard = uo.orElseThrow();

        eventBoard.change("베이비", "그건바로 나");

        eventBoardRepository.save(eventBoard);

        log.info("eventBoard=======>"+eventBoard);
    }

    @Test
    public void testDelete() {
        Long ebno = 124L;

        eventBoardRepository.deleteById(ebno);

        log.info("ebno==========>"+ebno);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("ebno").descending());

        Page<EventBoard> p = eventBoardRepository.findAll(pageable);

        log.info("total count : "+p.getTotalElements());
        log.info("total pages : "+p.getTotalPages());
        log.info("page number : "+p.getNumber());
        log.info("page size : "+p.getSize());

        List<EventBoard> todoList = p.getContent();

        todoList.forEach(eventBoard -> log.info(eventBoard));
    }

    @Test
    public void testSearchOne() {

        Pageable pageable = PageRequest.of(1,10,
                Sort.by("ebno").descending());

        eventBoardRepository.searchOne(pageable);
    }

    @Test
    public void testSearchAll() {

        String[] types = {"t","c","w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0,10,Sort.by("ebno").descending());

        Page<EventBoard> p = eventBoardRepository.searchAll(types, keyword, pageable);

        log.info("total pages ========> "+p.getTotalPages());
        log.info("page size ========> "+p.getSize());
        log.info("page number ========> "+p.getNumber());
        log.info(p.hasPrevious() +"======>"+p.hasNext());

        p.getContent().forEach(eventBoard -> log.info(eventBoard));
    }

    @Test
    public void testInputEvents() {
        IntStream.rangeClosed(1,200).forEach(i -> {
            EventBoard eventBoard = EventBoard.builder()
                    .title("8월 이용권 세일 안내")
                    .writer("관리자"+(i%10)+1)
                    .content("8월 01일 부터 8워 31일까지 Vibe Music 모든 회원에게 50% 할인 된 금액으로 이용권 구매 가능 혜택을 드립니다.")
                    .build();
            eventBoardRepository.save(eventBoard);
        });


    }


}