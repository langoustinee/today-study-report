package com.kakao.lango.springbootboard.persistence;

import com.kakao.lango.springbootboard.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 삽입, 수정, 삭제의 경우 @Modifying 어노테이션을 추가해야 한다.
    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(@Param("bno") Long bno);

}
