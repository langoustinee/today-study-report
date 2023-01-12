package com.kakao.lango.springbootboard.persistence;

import com.kakao.lango.springbootboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // Board 데이터를 가져올 때 Member의 Writer도 가져오는 메소드
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 게시글 번호를 받아서 Board 및 Reply 정보 가져오는 메소드
    // Board와 Reply는 1:N 관계이며, Board와 Reply를 결합한 형태로 리턴해야 한다.
    @Query("select b, r from Board b left join Reply r on b=r.board where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    // 게시글 목록과 작성자 정보, 댓글의 개수를 가져오는 메소드
    @Query("select b, w, count(r) from Board b " +
            "left join b.writer w " +
            "left join Reply r on b=r.board " +
            "group by b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    // 상세보기 - bno(글번호)를 통해 데이터를 찾아오는 메소드
    @Query("select b, w, count(r) from Board b " +
            "left join b.writer w " +
            "left outer join Reply r on b=r.board " +
            "where b.bno=:bno")
    Object getBoardByBno(@Param("bno") Long bno);

}
