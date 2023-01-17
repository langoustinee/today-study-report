package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.Member;
import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    /**
     * 페이징을 이용하여 테이블의 전체 데이터 가져오는 메소드
     * 기본 키로 하나의 데이터 가져오는 메소드
     * 데이터 삽입과 수정하는 메소드
     * 기본 키를 통해 삭제하는 메소드
     */

    /*
     이름을 기반으로 생성할 수 있는 메소드
    */

    // 영화 정보를 찾아오는 메소드
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    // 회원 정보를 가지고 데이터를 삭제하는 메소드
    // Member에서 deleteById 등의 기본 메소드로 삭제를 하지 않고
    // 회원을 탈퇴시킬 때 리뷰 데이터도 삭제하면 안되기 때문에 Review 데이터는 남기고 회원만 탈퇴하기 위함이다.
    void deleteByMember(Member member);

    // 회원을 수정하는 메소드
    // JPQL을 이용한 쿼리 작성 가능 - JOIN
    @Modifying // JPQL에서 수정을 하고 싶을 때 사용하는 어노테이션
    @Query("update Review r " +
            "set r.member.mid = null " +
            "where r.member.mid = :mid")
    void updateByMember(@Param("mid") Long mid);

    /*
        JPQL을 이용한 쿼리를 작성할 수 있다. - JOIN
    */

    /*
        Querydsl을 이용한 쿼리를 작성할 수 있다. - 동적 쿼리
    */

}
