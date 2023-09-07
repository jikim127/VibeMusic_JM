package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplySearch {

    Page<Reply> searchAll(String[] types, String keyword, Pageable pageable);
}
