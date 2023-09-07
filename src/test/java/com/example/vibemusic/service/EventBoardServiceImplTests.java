package com.example.vibemusic.service;

import com.example.vibemusic.dto.EventBoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class EventBoardServiceImplTests {

    @Autowired
    private EventBoardService eventBoardService;

    @Test
    public void testRegister() {

        log.info(eventBoardService.getClass().getName());

        EventBoardDTO eventBoardDTO = EventBoardDTO.builder()
                .title("Title이당~")
                .content("Content이당~")
                .writer("Writer이당~")
                .build();

        Long ebno = eventBoardService.register(eventBoardDTO);

        log.info("eventBoardDTO========>"+eventBoardDTO);
    }

    @Test
    public void testSelect() {

        Long ebno = 4L;

        EventBoardDTO eventBoardDTO = eventBoardService.select(ebno);

        log.info("eventBoardDTO ============> "+eventBoardDTO);
    }

    @Test
    public void testModify() {

        EventBoardDTO eventBoardDTO = EventBoardDTO.builder()
                .ebno(4L)
                .title("맘마모꼬띠뽀")
                .content("맘마사됴잉")
                .writer("또히")
                .build();

        eventBoardService.modify(eventBoardDTO);

        log.info("eventBoardDTO ============> "+eventBoardDTO);
    }

    @Test
    public void testRemove() {

        Long ebno = 4L;

        eventBoardService.remove(ebno);
    }

}