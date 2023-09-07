package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ano")
    private Long ano;

    //question's fk
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qno")
    private Question question;

    private String answerText;

    private String answerer;

    public void changeText(String text){
        this.answerText = text;
    }
}