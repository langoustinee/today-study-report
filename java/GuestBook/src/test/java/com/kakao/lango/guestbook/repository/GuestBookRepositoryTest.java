package com.kakao.lango.guestbook.repository;

import com.kakao.lango.guestbook.domain.GuestBook;
import com.kakao.lango.guestbook.domain.QGuestBook;
import com.kakao.lango.guestbook.persistence.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    GuestBookRepository repository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestbook = GuestBook.builder()
                    .title(i+" 번째 제목")
                    .content(i+ " 번째 내용")
                    .writer("사용자 " + (i % 10))
                    .build();
            System.out.println(repository.save(guestbook));
        });
    }

    @Test
    public void updateDummies() {
        GuestBook guestbook = GuestBook.builder()
                .gno(1L)
                .title("제목을 변경")
                .content("내용을 변경")
                .writer("lango")
                .build();
        System.out.println(repository.save(guestbook));
    }

    // title에 원하는 문자열이 포함된 Entity를 조회하는 테스트메소드
    @Test
    public void findByTitle() {
        // 10개씩 첫번째 페이지의 데이터를 조회하는데, modDate를 내림차순으로 정렬하여 가져오기
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());

        // QueryDSL 수행하기
        /*동적으로 처리하기 위해서 QDomain 클래스를 얻어오는데 QDomain 클래스를
        이용하면 Entity 클래스에 선언된 title, content 같은 필드들을 변수로 활용할 수 있음*/
        QGuestBook qguestBook = QGuestBook.guestBook;

        // title에 1이 포함된 조건을 생성하기
        String keyword = "1";

        // BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너이다.
        BooleanBuilder builder = new BooleanBuilder();

        // 원하는 조건은 필드 값과 결합하여 생성하는데, BooleanBuilder 안에 들어가는 값은 com.querydsl.core.types.Predicate 타입이다.
        BooleanExpression expression = qguestBook.title.contains(keyword);

        // 만들어진 조건은 where문에 and나 or같은 키워드와 결합한다.
        builder.and(expression);

        Page<GuestBook> result = repository.findAll(builder, pageable);
        for (GuestBook guestBook : result.getContent()) {
            System.out.println(guestBook);
        }
    }

    @Test
    public void findByTitleOrContent() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());

        // QueryDSL 수행을 위해 Q 클래스를 가져오기
        QGuestBook qgestBook = QGuestBook.guestBook;

        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();

        // title에 포함된 조건 생성하기
        BooleanExpression exTitle = qgestBook.title.contains(keyword);
        // content에 포함된 조건 생성하기
        BooleanExpression exContent = qgestBook.content.contains(keyword);

        // 위 2개의 조건 결합하기
        BooleanExpression exAll = exTitle.or(exContent);
        builder.and(exAll);
        builder.and(qgestBook.gno.lt(100L));

        Page<GuestBook> result = repository.findAll(builder, pageable);
        for (GuestBook guestBook : result.getContent()) {
            System.out.println(guestBook);
        }
    }
}
