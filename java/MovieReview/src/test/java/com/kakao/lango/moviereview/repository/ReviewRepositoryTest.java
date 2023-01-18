package com.kakao.lango.moviereview.repository;

import com.kakao.lango.moviereview.domain.Member;
import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.Review;
import com.kakao.lango.moviereview.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReview() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            // 영화 번호 난수로 설정하기
            Long mno = (long)(Math.random() * 100) + 1;
            
            // 회원 번호 난수로 설정하기
            Long mid = (long)(Math.random() * 50) + 1;

            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            // 영화에 리뷰 등록하기
            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text(i + "번 째 영화는 재미있었습니다.")
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    //@Transactional
    public void getReviews() {
        Movie movie = Movie.builder().mno(2L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            // review 안에서 member나 movie를 가져오려고 하면 지연로딩이 적용되어 있기 때문에 에러가 발생한다.
            System.out.println(review.getMember().getEmail());
        });
    }

    @Test
    @Transactional
    @Commit
    public void deleteMember() {
        Member member = Member.builder().mid(33L).build();
        reviewRepository.deleteByMember(member);
    }

    @Test
    @Transactional
    @Commit
    public void updateByMember() {
        reviewRepository.updateByMember(1L);
    }
}
