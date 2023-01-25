package com.kakao.lango.moviereview.controller;

import com.kakao.lango.moviereview.dto.ReviewDTO;
import com.kakao.lango.moviereview.service.ReviewService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    // 영화번호에 해당하는 리뷰 목록을 요청하는 메소드
    @GetMapping("/{mno}/list")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("[ReviewController] getList mno: " + mno);
        List<ReviewDTO> reviewDTOList = reviewService.getList(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    // 리뷰 등록 요청 메소드
    // ReviewDTO 파라미터를 받을 때 사용한 @RequestBody 어노테이션은 JSON 데이터를 받아서 ReviewDTO로 변환하여 받아온다.
    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@PathVariable("mno") Long mno, @RequestBody ReviewDTO reviewDTO) {
        log.info("[ReviewController] addReview mno: " + mno);
        log.info("[ReviewController] addReview reviewDTO: " + reviewDTO);
        Long result = reviewService.register(reviewDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // 리뷰 수정 요청 메소드
    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable("mno") Long mno,
                                             @PathParam("reviewnum") Long reviewnum,
                                             @RequestBody ReviewDTO reviewDTO) {
        log.info("[ReviewController] modifyReview mno: " + mno);
        log.info("[ReviewController] modifyReview reviewnum: " + reviewnum);
        Long result = reviewService.modify(reviewDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //리뷰 삭제 요청 메소드
    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable("mno") Long mno, @PathVariable("reviewnum") Long reviewnum) {
        log.info("[ReviewController] removeReview mno: " + mno);
        log.info("[ReviewController] removeReview reviewnum: " + reviewnum);
        Long result = reviewService.remove(reviewnum);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
