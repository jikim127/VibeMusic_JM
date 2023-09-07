package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.repository.search.ReplySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplySearch {
    List<Reply> findReplyByMusic_No(Long no);

    @Query("select r from Reply  r where r.music.no = :no")
    Page<Reply> replyListOfMusic(Long no, Pageable pageable);
}
