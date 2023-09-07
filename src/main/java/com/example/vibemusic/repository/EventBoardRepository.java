package com.example.vibemusic.repository;

import com.example.vibemusic.domain.EventBoard;
import com.example.vibemusic.repository.search.EventBoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventBoardRepository extends JpaRepository<EventBoard, Long>, EventBoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}
