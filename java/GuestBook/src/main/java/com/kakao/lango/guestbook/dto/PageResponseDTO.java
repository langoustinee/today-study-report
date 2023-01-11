package com.kakao.lango.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 다른 종류의 Entity에서도 사용할 수 있도록 제네릭을 사용한다.
// DTO는 결과를 출력할 때 사용할 데이터 클래스이며, EN은 변환할 때 사용할 메서드를 소유한 클래스이다.
@Data
public class PageResponseDTO<DTO, EN> {

    // 데이터 목록
    private List<DTO> dtoList;

    // 전체 페이지 개수
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 하나의 페이지에 출력할 데이터의 개수
    private int size;

    // 페이지번호별로 시작하는 페이지번호와 마지막 페이지 번호
    private int start, end;

    // 이전페이지와 다음페이지 여부
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    // Paging 결과를 가지고 추가한 항목들을 계산해주는 메소드
    private void makePageList(Pageable pageable) {
        // 현재 페이지 번호
        // JPA는 페이지 번호가 0부터 시작하기 때문에 +1 해준다.
        this.page = pageable.getPageNumber()+1;
        // 페이지 당 데이터 개수
        this.size = pageable.getPageSize();

        // 임시로 마지막 페이지 번호 생성하기
        // 페이지번호 목록 10개를 출력한다.
        // 10으로 나누어서 소수가 있으면 올림하고 10을 곱한다.
        int tempEnd = (int) (Math.ceil(page/10.0)) * 10;
        // 시작하는 번호
        start = tempEnd - 9;
        // 이전 페이지 여부
        prev = start > 1;
        // 마지막 페이지 번호 계산하기
        end = totalPage > tempEnd ? tempEnd : totalPage;
        // 다음 페이지 여부
        next = totalPage > tempEnd;

        // 페이지 번호 목록
        // start부터 end까지 스트링으로 만들어서 List로 변환한다.
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        
    }

    // fn 함수를 적용하여 Page를 List로 변환해주는 메소드
    // Page 단위의 Entity를 받아서 DTO들의 List로 변환한다.
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
        // EN(Entity)과 DTO(클래스타입)을 변환해주는 함수를 매개변수로 받아서 DTO 타입의 List로 변환하는 구문이다.
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        // 전체 페이지 개수 계산하기
        totalPage = result.getTotalPages();

        // 페이지 목록 메소드 호출하기
        makePageList(result.getPageable());
        
    }
}
