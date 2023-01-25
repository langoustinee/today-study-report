package com.kakao.lango.springbootjpa.repository;

import com.kakao.lango.springbootjpa.domain.Memo;
import com.kakao.lango.springbootjpa.persistence.MemoRepository;
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
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    // 데이터 삽입 테스트
    @Test
    public void insert() {
        // 100개의 Memo를 삽입하기
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText(i + "번째 메모입니다.")
                    .build();
            memoRepository.save(memo);
        });
    }
    
    // 전체 데이터 조회 메소드
    @Test
    public void all() {
        List<Memo> list = memoRepository.findAll();
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    // 하나의 데이터 조회 메소드
    @Test
    public void getById() {
        // 기본 키를 통해 조회한다면 없거나 1개의 데이터를 리턴하는데, 이 때는 Optional을 사용해야 한다.
        Optional<Memo> result = memoRepository.findById(100L);
        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("데이터가 존재하지 않습니다.");
        }
    }

    @Test
    public void update() {
        Memo memo = Memo.builder()
                .mno(100L)
                .memoText("메모 수정")
                .build();
        memoRepository.save(memo);
    }

    @Test
    public void delete() {
        // 먼저 존재여부를 확인한 후 삭제 작업을 해야 한다.
        // 없는 데이터를 삭제할 경우 에러가 발생한다.
        // 기본 키를 가지고 삭제
        memoRepository.deleteById(100L);
        // Memo Entity 객체를 가지고 삭제
        memoRepository.delete(Memo.builder().mno(99L).build());
    }

    @Test
    public void Paging() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Memo> result = memoRepository.findAll();
        System.out.println(result);
    }

    @Test
    public void PagingSecond() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        // 전체 페이지 개수
        System.out.println(result.getTotalPages());
        // 조회된 데이터 순회하기
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void sort() {
        // mno 컬럼을 기준으로 내림차순 정렬하기
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void sortConcate() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").descending();
        // 2개를 결합하여 sort1의 값이 같으면 sort2로 정렬한다.
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void findOrder() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(30L, 40L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void findPaging() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<Memo> result = memoRepository.findByMnoBetween(0L, 50L, pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    // 특정 작업에서는 트랜잭션을 적용하지 않는다면 에러가 발생한다.
    @Transactional
    // 트랜잭션이 적용되면 자동 Commit되지 않기에 Commit을 호출해야 실제 작업이 완료된다.
//    @Commit
    public void deleteByMno() {
        // 10L 이하의 mno를 가진 데이터들은 전부 삭제된다.
        memoRepository.deleteByMnoLessThan(20L);

        List<Memo> list = memoRepository.findAll();
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void updateQuery() {
        System.out.println(memoRepository.updateMemoText(10L, "메모 변경1"));
        System.out.println(memoRepository.updateMemoText(Memo.builder().mno(11L).memoText("메모 변경2").build()));
    }

    @Test
    public void selectQuery() {
        // 0번 페이지에서 mno가 내림차순인 10개의 데이터를 가져오는 pageable 객체를 선언한다.
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.getListWithQuery(50L, pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void objectQuery() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Object[]> result = memoRepository.getObjectWithQuery(50L, pageable);
        for (Object[] memo : result.getContent()) {
            System.out.println(Arrays.toString(memo));
        }
    }

}
