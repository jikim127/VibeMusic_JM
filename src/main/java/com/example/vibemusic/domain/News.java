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
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nNo;

    @Column (length = 500)
    private String nTitle;

    @Column (length = 5000)
    private String nContent;

    @Column (length = 5000)
    private String nImage;

    @Column (length = 5000)
    private String nNewsLinks;

    @Column (length = 100000)
    private String nFullNews;

    @Column
    private int nViewCount;

    @Column
    private String nRegDate;


    public String getNNewsLinks() {
        return nNewsLinks;
    }
}
