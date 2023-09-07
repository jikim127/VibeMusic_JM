package com.example.vibemusic.repository;

import com.example.vibemusic.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {



}
