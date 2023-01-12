package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.dto.BoardDTO;
import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.dto.PageResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    // 필드로 BoardService 주입받기
    @Autowired
    private BoardService boardService;

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
                .bno(153L)
                .title("제목을 수정합니다.")
                .content("내용을 수정합니다.")
                .build();
        System.out.println(boardService.modify(dto));
    }
    
}
