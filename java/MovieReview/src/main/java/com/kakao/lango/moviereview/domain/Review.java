package com.kakao.lango.moviereview.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
// 지연로딩을 위해 toString시 movie와 member는 제외하고 호출하도록 설정한다.
@ToString(exclude = {"movie", "member"})
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade;
    private String text;
}
