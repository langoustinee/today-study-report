package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Member;
import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.Review;
import com.kakao.lango.moviereview.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    //영화에 해당하는 리뷰를 가져오기
    List<ReviewDTO> getList(Long mno);
    //리뷰 등록
    Long register(ReviewDTO reviewDTO);
    //리뷰 수정
    Long modify(ReviewDTO reviewDTO);
    //리뷰 삭제
    Long remove(Long rnum);

    // DTO를 Entity로 변환해주는 기본 메소드
    default Review dtoToEntity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewNum())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .member(Member.builder().mid(reviewDTO.getMid()).build())
                .build();
        return review;
    }

    // Entity를 DTO로 변환해주는 기본 메소드
    default ReviewDTO entityToDto(Review review) {
        ReviewDTO dto = ReviewDTO.builder()
                .reviewNum(review.getReviewnum())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getNickname())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();
        return dto;
    }
}
