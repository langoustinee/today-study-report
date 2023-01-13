package com.kakao.lango.springbootboard.repository;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.Member;
import com.kakao.lango.springbootboard.domain.Reply;
import com.kakao.lango.springbootboard.persistence.BoardRepository;
import com.kakao.lango.springbootboard.persistence.MemberRepository;
import com.kakao.lango.springbootboard.persistence.ReplyRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertMember() {
        for (int i = 0; i <= 100; i++) {
            Member member = Member.builder()
                    .email("user" + i + "@kakao.com")
                    .password("kakao" + i)
                    .name("MEMBER@" + i)
                    .build();
            memberRepository.save(member);
        }
    }

    @Test
    public void insertBoard() {
        for (int i = 0; i <= 100; i++) {
            Member member = Member.builder()
                    .email("user" + i + "@kakao.com")
                    .build();
            Board board = Board.builder()
                    .title("게시글의 " + i + "번째 제목입니다.")
                    .content("게시글의 " + i + "번째 내용입니다.")
                    .writer(member)
                    .build();
            boardRepository.save(board);
        }
    }

    @Test
    public void insertReply() {
        for (int i = 1; i <= 300; i++) {
            long bno = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();
            Reply reply = Reply.builder()
                    .text(i + "번째 댓글을 작성합니다.")
                    .board(board)
                    .replyer("guest@" + i)
                    .build();
            replyRepository.save(reply);
        }
    }

    // 게시글 1개를 가져오는 메소드
    @Test
    @Transactional
    public void getBoard() {
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    // 댓글 1개를 가져오는 메소드
    @Test
    @Transactional
    public void getReply() {
        Optional<Reply> result = replyRepository.findById(100L);
        Reply reply = result.get();
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }


    // Board 데이터를 가져올 때 Member의 Writer도 가져오는 메소드
    @Test
    public void joinBoard() {
        Object result = boardRepository.getBoardWithWriter(100L);
        System.out.println(result);

        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));

        Board board = (Board) arr[0];
        Member member = (Member) arr[1];

        System.out.println(board);
        System.out.println(member);
    }


    @Test
    public void joinReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void joinReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            Board board = (Board) arr[0];
            Member member = (Member) arr[1];
            Long replyCount = (Long) arr[2];

            System.out.println("board = " + board);
            System.out.println("member = " + member);
            System.out.println("replyCount = " + replyCount);
        });
    }

    @Test
    public void findByBno() {

    }

    @Test
    public void searchOne() {
        boardRepository.searchOne();
    }

    @Test
    public void searchPage() {
        // bno는 내림차순으로, title을 오름차순으로 검색한다.
        Pageable pageable = PageRequest.of(0,10,
                Sort.by("bno").descending()
                        .and(Sort.by("title").ascending()));

        // title에 1이라는 문자가 포함된 데이터만 검색하기
        Page<Object[]> result = boardRepository.searchPage("c", "초밥", pageable);
        for (Object[] res : result.getContent()) {
            System.out.println(Arrays.toString(res));
        }
    }

    @Test
    public void getListReply() {
        List<Reply> replyList = replyRepository.findByBoardOrderByRno(Board.builder().bno(100L).build());
        replyList.forEach(
                reply -> System.out.println(reply)
        );
    }
}
