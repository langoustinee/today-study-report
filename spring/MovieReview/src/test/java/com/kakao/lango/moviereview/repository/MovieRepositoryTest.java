package com.kakao.lango.moviereview.repository;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.MovieImage;
import com.kakao.lango.moviereview.persistence.MovieImageRepository;
import com.kakao.lango.moviereview.persistence.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void insertMovie() {
        // 영화 100개 데이터를 삽입한다.
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title(i + "번째 출시된 영화").build();
            movieRepository.save(movie);

            // 1부터 5까지의 난수 설정
            int count = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                MovieImage image = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("sample-" + j + ".jpg")
                        .build();
                movieImageRepository.save(image);
            }
        });
    }

    @Test
    public void join() {
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC, "mno");
        Page<Object[]> result = movieRepository.getAllMovieList(pageable);
        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void getOneMovieList() {
        List<Object[]> list = movieRepository.getOneMovieList(27L);
        for(Object[] movieList : list) {
            System.out.println(Arrays.toString(movieList));
        }
    }

}
