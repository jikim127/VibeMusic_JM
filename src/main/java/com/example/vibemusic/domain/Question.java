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
@ToString
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long qno;

    @Column(length = 500,nullable = false)
    private String qTitle;

    @Column (length = 5000,nullable = false)
    private String qContent;

    @Column (length = 5000)
    private String qImage;

    @Column (nullable = false)
    private String qWriter;

    @Column
    private int qViewCount;

    @Column
    private String qRegDate;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();


    public void change(String qTitle, String qContent){//QuestionServiceImple에 필요
        this.qTitle=qTitle;
        this.qContent=qContent;
    /*
    메소드 내부에서 this.***은 클래스의 인스턴스 변수인 "***"을 의미.
    따라서, 메소드가 받은 "***" 매개변수의 값을 클래스의 "***"에 할당하여 저장.
     */


    }



}

