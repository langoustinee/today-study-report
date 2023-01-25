package com.kakao.lango.springbootboard.controller;

import com.kakao.lango.springbootboard.dto.ReplyDTO;
import com.kakao.lango.springbootboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies") // 공통 URL 설정하기
@RestController
public class ReplyController {

    private final ReplyService replyService;

    // 게시글 번호(bno)를 가지고 댓글을 리턴하는 메소드
    @GetMapping(value = "/board/{bno}")
    public ResponseEntity<List<ReplyDTO>> getReplyList(@PathVariable("bno") Long bno) {
        // 파라미터 확인하기
        log.info("[ReplyController] /replies/board/{bno} bno: " + bno);
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    // 댓글 등록 메소드
    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    // 댓글 삭제 메소드
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("[ReplyController] /{rno} ㅍrno:" + rno );
        replyService.remove(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 댓글 수정 메소드
    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
        log.info("[ReplyController] /{rno} modify replyDTO:" + replyDTO );
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
