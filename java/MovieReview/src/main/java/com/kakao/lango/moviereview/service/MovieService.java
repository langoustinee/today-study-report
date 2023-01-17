package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.MovieImage;
import com.kakao.lango.moviereview.dto.MovieDTO;
import com.kakao.lango.moviereview.dto.MovieImageDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    // 데이터 삽입을 위한 메서드
    Long register(MovieDTO movieDTO);

    // DTO를 Entity로 변환하는 메소드
    // 하나의 Entity가 아닌 Movie와 MovieImage 총 2개의 Entity로 변환해야 하기 때문에 Map으로 리턴한다.
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
        Map<String, Object> map = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();
        map.put("movie", movie);

        // MovieImageDTO의 List
        List<MovieImageDTO> imageList = movieDTO.getImageDTOList();

        // MovieImageDTO의 List를 MovieImage Entity의 List로 변환하기
        if (imageList != null && imageList.size() > 0) {
            List<MovieImage> movieImageList = imageList.stream().map(movieImageDTO -> {
                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());
            map.put("imgList", movieImageList);
        }
        return map;
    }
}
