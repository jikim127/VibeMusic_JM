package com.example.vibemusic.repository.search;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.QMusic;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MusicSearchImpl extends QuerydslRepositorySupport implements MusicSearch {

    public MusicSearchImpl() {
        super(Music.class);
    }

    @Override
    public Page<Music> searchOne(Pageable pageable) {
        QMusic music = QMusic.music;
        JPQLQuery<Music> query = from(music);
        query.where(music.m_title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Music> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Music> searchAll(String[] types, String keyword, Pageable pageable) {
        QMusic music = QMusic.music;
        JPQLQuery<Music> query = from(music);

        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(music.m_title.contains(keyword));
                        break;
                    case "a" :
                        booleanBuilder.or(music.m_artist.contains(keyword));
                        break;
                    case "g" :
                        booleanBuilder.or(music.mGenre.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        System.out.println("query ============================>"+ query);

        query.where(music.no.gt(0L));
        System.out.println("query ============================>"+ query);

        this.getQuerydsl().applyPagination(pageable,query);

        List<Music> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}
