package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Movie;
import com.kakao.lango.moviereview.domain.MovieImage;
import com.kakao.lango.moviereview.dto.MovieDTO;
import com.kakao.lango.moviereview.dto.PageRequestDTO;
import com.kakao.lango.moviereview.dto.PageResponseDTO;
import com.kakao.lango.moviereview.persistence.MovieImageRepository;
import com.kakao.lango.moviereview.persistence.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Override
    public PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());
        // 데이터베이스에 요청하기
        Page<Object[]> result = movieRepository.getAllMovieList(pageable);
        // Object[] 배열을 MovieDTO 타입으로 변경하기 위해서 Function을 생성한다.
        // 첫번째 데이터는 Movie, 두번째는 List<MovieImageList>, 세번째는 리뷰 평균점수, 네번째는 리뷰 개수로 받아온다.
        Function<Object[], MovieDTO> fn = (arr -> entityToDto(
                (Movie)arr[0] ,
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getOneMovieList(mno);
        // Movie 객체는 0번째 인덱스에 받아오기 때문에 제일 앞 인덱스를 받아온다.
        Movie movie = (Movie) result.get(0)[0];
        // 영화에 등록된 이미지 개수만큼 MovieImage 객체가 필요하다.
        List<MovieImage> movideImageList = new ArrayList<>();

        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movideImageList.add(movieImage);
        });

        // 리뷰의 평균 점수
        double avg = (double) result.get(0)[2];
        // 리뷰 개수
        Long reviewCnt = (Long) result.get(0)[3];

        return entityToDto(movie, movideImageList, avg, reviewCnt);
    }


}
