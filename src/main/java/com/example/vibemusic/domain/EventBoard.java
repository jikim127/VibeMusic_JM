package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ebno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(nullable = false)
    private int eViewCount; // 조회수 초기값은 0으로 설정

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
