package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
