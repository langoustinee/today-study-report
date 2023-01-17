package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    /**
     * 영화 정보를 가지고 영화 이미지와 리뷰개수 및 리뷰 평점의 평규능ㄹ 구해주는 메소드
     * JPQL에서는 m이 0번
     * mi가 1번
     * avg(coalesce(r.grade, 0))가 2번
     * count(distinct r.reviewnum)가 3번 데이터이기에
     * Object[]의 길이는 4가 된다.
     * 또한 Object[]은 반드시 형 변환하여 사용해야 한다.
     */
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r.reviewnum) from Movie m " +
            "left outer join MovieImage  mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "group by m")
    Page<Object[]> getAllMovieList(Pageable pageable);

    // 특정 영화 정보를 가지고 영화 이미지, 리뷰평점의 평균,  리뷰의 개수를 구하는 메소드
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno = :mno " +
            "group by mi")
    List<Object[]> getOneMovieList(@Param("mno") Long mno);
}
