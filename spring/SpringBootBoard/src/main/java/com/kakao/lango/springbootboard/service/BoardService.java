package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.Member;
import com.kakao.lango.springbootboard.dto.BoardDTO;
import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    // 게시글 등록하는 메소드
    Long register(BoardDTO dto);

    // 게시물 목록보기 메소드
    PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 게시글 상세보기 메소드
    BoardDTO get(Long bno);

    // bno(글번호)를 가지고 댓글을 삭제하는 메소드
    void removeWithReplies(Long bno);

    // 게시글 수정하는 메소드
    Long modify(BoardDTO dto);

    // DTO를 Entity로 변환해주는 메소드
    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    // Entity를 DTO로 변환해주는 메소드
    default BoardDTO entityToDto(Board board, Member member, Long replyCount) {
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
        return dto;
    }
}
