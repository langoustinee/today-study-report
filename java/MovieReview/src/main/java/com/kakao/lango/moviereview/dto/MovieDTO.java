package com.kakao.lango.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {
    private Long mno;
    private String title;

    // @Builder.Default는 특정 값으로 초기화하고자 할 때 사용하는 어노테이션이다.
    // 즉, builder() 라는 메서드를 이용하여 생성할 때 기본 값으로 사용한다는 의미이다.
    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
