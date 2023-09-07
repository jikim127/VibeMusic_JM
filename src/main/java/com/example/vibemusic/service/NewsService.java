package com.example.vibemusic.service;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {

    /**
     Read 보기
     */
    NewsDTO read1news(Long nNo);

    Page<News> list(Pageable pageable);


    void increaseViewCount(Long nNo);
}
