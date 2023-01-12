package com.kakao.lango.springbootboard.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<DTO, EN> {

    // 데이터 목록
    private List<DTO> dtoList;
    // 페이지번호 관련 속성
    // 전체 페이지 개수
    private int totalPage;
    // 현재 페이지 번호
    private int page;
    // 페이지당 데이터 출력 개수
    private int size;
    // 페이지 시작번호와 끝번호
    private int start, end;
    // 이전과 다음 출력 여부
    private boolean prev, next;
    // 페이지 번호 목록
    private List<Integer> pageList;

    // 검색 결과(Page<Board>)를 가지고 데이터를 생성해주는 메소드
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
        // 검색 결과 목록을 DTO의 List로 변환하기
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        // 전체 데이터 개수 구하기
        totalPage = result.getTotalPages();
        // 페이지 번호 목록 관련 속성을 결정하는 메소드 호출하기
        makePageList(result.getPageable());
        
    }

    // 페이지 번호 목록 관련 속성을 결정하는 메소드
    private void makePageList(Pageable pageable) {
        // 현재 페이지 번호
        this.page = pageable.getPageNumber() + 1;
        // 데이터 개수
        this.size = pageable.getPageSize();

        // 임시로 마지막 페이지 번호를 계산하기
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        // 시작 페이지 번호
        start = tempEnd - 9;
        // 이전 페이지 여부
        prev = start > 1;
        // 마지막 페이지 번호
        end = totalPage > tempEnd ? tempEnd: totalPage;
        // 다음 페이지 여부
        next = totalPage > tempEnd;
        // 페이지 번호 목록 만들기
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
