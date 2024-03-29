package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.dto.BoardDTO;
import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.dto.PageResponseDTO;
import com.kakao.lango.springbootboard.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    // 필드로 BoardService 주입받기
    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

    @Test
    public void register() {
        BoardDTO dto = BoardDTO.builder()
                .title("게시글의 제목을 등록합니다.")
                .content("게시글의 내용을 등록합니다.")
                .writerEmail("user33@kakao.com")
                .build();
        Long bno = boardService.register(dto);
        System.out.println(bno);
    }

    @Test
    public void getList() {
        PageRequestDTO dto = new PageRequestDTO();
        PageResponseDTO<BoardDTO, Object[]> result = boardService.getList(dto);
        System.out.println(result);
    }

    @Test
    public void get() {
        Long bno = 33L;
        BoardDTO dto = boardService.get(bno);
        System.out.println(dto);
    }

    @Test
    public void deleteRepliesAndBoard() {
        boardService.removeWithReplies(100L);
    }

    @Test
    public void modify() {
        BoardDTO dto = BoardDTO.builder()
                .bno(50002L)
                .title("[수정] 오늘의 점심은?")
                .content("[수정] 짜장 대 짬뽕")
                .updatedAt(LocalDateTime.now())
                .build();
        System.out.println(boardService.modify(dto));
    }
    
    
    
    /*
    * 댓글 요청 처리 메소드들을 테스트하기
    *
    * */

    @Test
    public void getReplyList() {
        // 게시글번호(bno)를 이용해 댓글 가져오기
        List<ReplyDTO> list = replyService.getList(27L);
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void insertReply() {
        ReplyDTO dto = ReplyDTO.builder()
                .text("댓글을 새로 등록합니다~!")
                .replyer("user1@kakao.com")
                .bno(30L)
                .build();
        System.out.println(replyService.register(dto));
    }
    
    
}
