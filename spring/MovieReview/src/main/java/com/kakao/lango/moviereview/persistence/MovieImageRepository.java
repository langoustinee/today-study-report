package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
