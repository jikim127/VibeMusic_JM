package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MusicSearch {

    Page<Music> searchOne(Pageable pageable);

    Page<Music> searchAll(String[] types, String keyword, Pageable pageable);

}
