package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.repository.search.MusicSearch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> , MusicSearch {

    // 장르 넣으면 찾아올 수 있음
    List<Music> findBymGenre(String genre);

    List<Music> findAll(Sort sort);
}
