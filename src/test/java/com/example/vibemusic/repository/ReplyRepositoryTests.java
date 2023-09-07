package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.Reply;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
@Slf4j
class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Test
    public void testInsert() {

        Long no = 1L;

        Music music = Music.builder().no(no).build();

        Reply reply = Reply.builder()
                .music(music)
                .r_replyText("노래가 너무 좋아요!")
                .rreplyer("sam")
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void readAll() {
       replyRepository.findReplyByMusic_No(1L).forEach(reply -> log.info("reply : {}",reply.getRno()));
    }

    @Test
    public void testDelete() {
        replyRepository.deleteById(2L);
    }

    @Test
    public void testModify() {
        Optional<Reply> byId = replyRepository.findById(1L);
        Reply reply = byId.orElseThrow();
        reply.change("개추개추개추개추개추");
        replyRepository.save(reply);
    }

    @Test
    public void testBoardReplies() {
        Long no = 1L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.replyListOfMusic(no,pageable);

        result.getContent().forEach(reply -> log.info("reply : {}",reply));
    }

}