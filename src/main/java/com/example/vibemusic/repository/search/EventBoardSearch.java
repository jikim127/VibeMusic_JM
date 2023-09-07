package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.EventBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventBoardSearch {

    Page<EventBoard> searchOne(Pageable pageable);

    Page<EventBoard> searchAll(String[] types, String keyword, Pageable pageable);

}
