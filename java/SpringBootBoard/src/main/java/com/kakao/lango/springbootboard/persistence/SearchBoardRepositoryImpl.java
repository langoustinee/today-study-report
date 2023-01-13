package com.kakao.lango.springbootboard.persistence;

import com.kakao.lango.springbootboard.domain.Board;
import com.kakao.lango.springbootboard.domain.QBoard;
import com.kakao.lango.springbootboard.domain.QMember;
import com.kakao.lango.springbootboard.domain.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    // QuerydslRepositorySupport 클래스에 기본 생성자가 없기 때문에 생성자를 직접 생성하여 필요한 생성자를 호출해야 한다
    // 이 때, 검색에 사용할 Entity 클래스를 생성자에게 대입해준다.
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board searchOne() {
        log.info("[SearchBoardRepositoryImpl] searchOne method");

        // JPQL을 통해 동적 쿼리 실행하기
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        // 쿼리 작성하기
        JPQLQuery<Board> query = from(board);
        // Member를 Join하여 가져오는데 외래키는 board의 writer이다.
        query.leftJoin(member).on(board.writer.eq(member));
        // Reply를 Join하여 가져오는데 외래키는 reply의 board이다.
        query.leftJoin(reply).on(reply.board.eq(board));
        
        // bno가 103인 데이터를 조회하기
        // query.select(board).where(board.bno.eq(103L));

        /*
        // board와 member의 email, reply 갯수를 게시글번호로 그룹화하여 가져온다.
        query.select(board, member.email, reply.count()).groupBy(board);

        //JPQL을 실행하여 결과 가져오기
        List<Board> result = query.fetch();
        */

        // Tuple로 결과를 받아오기
        JPQLQuery<Tuple> tuple = query.select(board, member.email, reply.count());
        tuple.groupBy(board);
        List<Tuple> result = tuple.fetch();

        log.info("[SearchBoardRepositoryImpl] searchOne method result: " + result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("[SearchBoardRepositoryImpl] searchPage method ");
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(member).on(board.writer.eq(member));
        query.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = query.select(board, member, reply.count());

        // QueryDSL의 조건 생성하기
        /*
        * 검색 조건
        * 제목 검색 - t
        * 작성자 검색 - w
        * 내용 - c
        * 제목+내용 - tc
        * 제목+작성자 - tw
        * */
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // bno가 0보다 큰 데이터들만 가져오기
        BooleanExpression expression = board.bno.gt(0L);
        booleanBuilder.and(expression);

        // 타입에 따른 조건을 생성하기
        if(type != null){
            // 글자 단위로 쪼개기
            String[] typeArr = type.split("");
            // 배열을 순회하며 검색 조건을 작성하기
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for (String t : typeArr) {
                switch (t){
                    case "t": // 제목
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w": // 작성자
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c": // 내용
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        // 조건을 tuple에 적용하기
        tuple.where(booleanBuilder);

        // 게시글 번호(bno)를 내림차순으로 정렬 하도록 설정하기
        tuple.orderBy(board.bno.desc());
        // 게시글 번호별로 그룹화하기
        tuple.groupBy(board);
        
        // 페이징 처리하기
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());
        
        // 데이터 가져오기
        List<Tuple> result = tuple.fetch();
        log.info("[SearchBoardRepositoryImpl] searchPage method result: " + result);

        // tuple의 개수 구하기
        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }
}
