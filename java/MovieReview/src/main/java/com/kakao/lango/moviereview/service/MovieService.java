package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.MovieImage;
import com.kakao.lango.moviereview.dto.MovieDTO;
import com.kakao.lango.moviereview.dto.MovieImageDTO;
import com.kakao.lango.moviereview.dto.PageRequestDTO;
import com.kakao.lango.moviereview.dto.PageResponseDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    // 데이터 삽입을 위한 메서드
    Long register(MovieDTO movieDTO);

    // 목록을 가져오는 메소드 - 목록보기
    PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    // 목록의 상세보기 메소드
    MovieDTO getMovie(Long mno);

    // DTO를 Entity로 변환하는 기본 메소드
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

    // Entity를 DTO로 변환해주는 기본 메소드 - 검색결과를 DTO로 변환한다.
    // Movie, List<MovieImageList>, 리뷰 평균 점수, 리뷰 개수를 리턴한다.
    default MovieDTO entityToDto(Movie movie, List<MovieImage> movieImageList, double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();
        List<MovieImageDTO> movieImageDTOList = movieImageList.stream().map(movieImage -> {
            return MovieImageDTO.builder()
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt);

        return movieDTO;
    }
}
