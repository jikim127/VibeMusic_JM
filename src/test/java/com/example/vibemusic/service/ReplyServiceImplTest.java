package com.example.vibemusic.service;

import com.example.vibemusic.domain.Reply;
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
class ReplyServiceImplTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testList() {
        Long no = 1L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").ascending());

        Page<Reply> result = replyService.replyListOfMusic(no,pageable);

        result.getContent().forEach(reply -> log.info("reply =============> : {}",reply.getR_replyText()));
    }
}