package com.example.vibemusic.repository.search;

import com.example.vibemusic.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MusicSearchImplTest {

    @Autowired
    private MusicSearch musicSearch;

    @Autowired
    private MusicService musicService;

    @Test
    void searchAll() {

//        musicSearch.searchAll(null,null,)
    }
}