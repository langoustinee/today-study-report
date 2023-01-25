package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.Reply;
import com.kakao.lango.springbootboard.dto.ReplyDTO;
import com.kakao.lango.springbootboard.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    // 댓글 등록 메소드
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    // 댓글 목록 메소드
    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepository.findByBoardOrderByRno(Board.builder().bno(bno).build());

        // result를 수정한 시간의 내림차순으로 정렬하기 (JPA가 아닌 Java에서의 정렬)
        /*
        result.sort((new Comparator<Reply>() {
            @Override
            public int compare(Reply o1, Reply o2) {
                return o2.getUpdatedAt().compareTo(o1.getUpdatedAt());
            }
        }));
        */
        // 위 comparaotr 구문을 람다식으로 수정하기
        result.sort(((o1, o2) -> o2.getUpdatedAt().compareTo(o1.getUpdatedAt())));

        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    // 댓글 수정 메소드
    @Override
    public Long modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    // 댓글 삭제 메소드
    @Override
    public Long remove(Long rno) {
        replyRepository.deleteById(rno);
        return rno;
    }
}
