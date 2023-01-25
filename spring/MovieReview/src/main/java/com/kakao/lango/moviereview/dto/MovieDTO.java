package com.kakao.lango.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {
    private Long mno;
    private String title;
    //영화의 평균 평점
    private double avg;
    //리뷰 수 jpa의 count()
    private Long reviewCnt;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    // @Builder.Default는 특정 값으로 초기화하고자 할 때 사용하는 어노테이션이다.
    // 즉, builder() 라는 메서드를 이용하여 생성할 때 기본 값으로 사용한다는 의미이다.
    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
