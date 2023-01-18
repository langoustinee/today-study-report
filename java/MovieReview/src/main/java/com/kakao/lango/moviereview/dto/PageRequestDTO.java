package com.kakao.lango.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    // 페이지 번호
    private int page;

    // 페이지당 데이터개수
    private int size;

    // 검색항목
    private String type;

    // 검색내용
    private String keyword;

    public PageRequestDTO() {
        page = 1;
        size = 10;
    }

    // page와 size를 가지고 Pageable 객체를 생성해주는 메소드
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size);
    }
}
