package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "musics") // 출력에서 musics 필드를 제외
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plNo")
    private Long plNo;

    private String plName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Member member; // User 엔터티와의 관계

    // fk키
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_music", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "plNo"), // PlayList와 연결된 컬럼
            inverseJoinColumns = @JoinColumn(name = "no") // Music과 연결된 컬럼
    )
    private List<Music> musics; // Music 엔터티와의 관계//
    public List<Music> getMusics(){
        if (musics == null) {
            musics = new ArrayList<>();
        }
        return musics;
    }
    public void setPlName(String plName) {
    }

    public void removeMusic(Music music) {
        musics.remove(music);
    }

}