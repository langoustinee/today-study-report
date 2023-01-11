package com.kakao.lango.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    // 현재 페이지 번호
    private int page;

    // 페이지 당 데이터 개수
    private int size;

    // 생성자에서 기본값을 설정한다.
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    // Repository에게 전달할 Pageable 객체를 만들어주는 메소드
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
