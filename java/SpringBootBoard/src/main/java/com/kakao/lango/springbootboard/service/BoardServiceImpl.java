package com.kakao.lango.springbootboard.service;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.Member;
import com.kakao.lango.springbootboard.dto.BoardDTO;
import com.kakao.lango.springbootboard.dto.PageRequestDTO;
import com.kakao.lango.springbootboard.dto.PageResponseDTO;
import com.kakao.lango.springbootboard.persistence.BoardRepository;
import com.kakao.lango.springbootboard.persistence.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
// @RequiredArgsConstructor 어노테이션은 final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복의 어노테이션이다.
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    // BoardRepository의 의존성을 주입하기
    private final BoardRepository boardRepository;

    //ReplyRepository의 의존성을 주입하기
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info("[BoardService] BoardDTO: " + dto);
        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info("[BoardService] pageRequestDTO: " + pageRequestDTO);
        // Entity를 DTO로 변환하는 람다 인스턴스 생성하기
        // Join을 하여 결과를 가져오기 때문에 Object 배열로 받아온다.
        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable((Sort.by("bno").descending())));
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    // 댓글과 게시글이 연속적으로 2개가 삭제되기에 트랜잭션을 적용해야 한다.
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        // 댓글을 먼저 삭제하고 게시글 삭제하기
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Transactional
    @Override
    public Long modify(BoardDTO dto) {
        log.info("[BoardService] modify: " + dto);
        // JPA에서는 삽입과 수정 메소드가 동일하다.
        // upsert(있으면 수정 없다면 삽입)를 할 경우엔 그냥 save를 호출하면 되지만
        // update를 하고자 할때는 데이터가 있는지 확인한 후 save를 수행해야 한다.

        if (dto.getBno() == null) return 0L;
        // 수정할 데이터 가져오기
        Optional<Board> board = boardRepository.findById(dto.getBno());
        // 수정할 데이터가 존재한다면 수정 작업 진행하기
        if (board.isPresent()) {
            board.get().changeTitle(dto.getTitle());
            board.get().changeContent(dto.getContent());
            boardRepository.save(board.get());
            return board.get().getBno();
        } else {
            return 0L;
        }
    }

}
