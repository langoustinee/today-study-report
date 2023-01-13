package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.Reply;
import com.kakao.lango.springbootboard.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    // 댓글 등록하는 메소드
    Long register(ReplyDTO replyDTO);

    // 댓글 목록을 가져오는 메소드
    List<ReplyDTO> getList(Long bno);

    // 댓글 수정하는 메소드
    Long modify(ReplyDTO replyDTO);

    // 댓글 삭제하는 메소드
    Long remove(Long rno);

    // DTO to ENtity
    default Reply dtoToEntity(ReplyDTO dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        Reply reply = Reply.builder().rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
        return reply;
    }

    // Entity to DTO
    // entity를 dto로 만들 때는 자동으로 만들어주는 속성들까지 모두 대입해야 한다.
    default ReplyDTO entityToDto(Reply entity) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
        return dto;
    }
}
