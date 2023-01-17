package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.MovieImage;
import com.kakao.lango.moviereview.dto.MovieDTO;
import com.kakao.lango.moviereview.persistence.MovieImageRepository;
import com.kakao.lango.moviereview.persistence.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {
        // 넘어온 데이터 확인하기
        log.info("[MovieService] movieDTO: " + movieDTO);
        Map<String, Object> map = dtoToEntity(movieDTO);

        // 영화와 영화 이미지 정보를 찾아오기
        Movie movie = (Movie)map.get("movie");
        System.out.println("[MovieService] movie:" + movie);

        List<MovieImage> movieImageList = (List<MovieImage>) map.get("imgList");
        System.out.println("[MovieService] movieImageList:" + movieImageList);

        // save 메소드가 여러 번 호출되며, Movie와 MovieImage Entity에 각각 다른 데이터를 삽입한다.
        // @transactional을 통해서 트랜잭션 연결을 유지해야 여러번 save할 수 있다.
        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });
        return movie.getMno();
    }
}
