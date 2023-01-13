package com.kakao.lango.springbootboard.persistence;

import com.kakao.lango.springbootboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface SearchBoardRepository {
    Board searchOne();

    // 검색을 위한 메소드
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);


}
