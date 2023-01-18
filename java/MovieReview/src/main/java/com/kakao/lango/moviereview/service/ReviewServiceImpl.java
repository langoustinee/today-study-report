package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.Review;
import com.kakao.lango.moviereview.dto.ReviewDTO;
import com.kakao.lango.moviereview.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    // 하나의 영화에 대한 리뷰 목록 가져오기
    @Override
    public List<ReviewDTO> getList(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        return result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
        return review.getReviewnum();
    }

    @Override
    public Long modify(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
        return review.getReviewnum();
    }

    @Override
    public Long remove(Long rnum) {
        reviewRepository.deleteById(rnum);
        return rnum;
    }

}

