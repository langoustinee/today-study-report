package com.kakao.lango.springbootjpa.persistence;

import com.kakao.lango.springbootjpa.domain.Memo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // mno의 값이 from부터 to 사이인 데이터를 조회하는 메소드
    List<Memo> findByMnoBetween(Long from, Long to);

    // mno의 값이 from부터 to 사이인 데이터를 내림차순으로 조회하는 메소드
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // 페이징을 적용하여 from부터 to 사이의 데이터를 조회하는 메소드
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    // mno보다 작은 데이터들을 모두 삭제하는 메소드
    void deleteByMnoLessThan(Long mno);

    // JPQL을 사용하여 mno와 memoText를 매개변수로 받아 수정하는 메서드
    @Transactional
    @Modifying
    // Native SQL이 아니기 때문에 Table 이름을 적는 것이 아니라 Entity의 이름을 작성해야 한다.
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
    public int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

    // JPQL을 이용해 Memo Entity를 받아서 수정하는 메서드
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText}" + " where m.mno = :#{#param.mno}")
    public int updateMemoText(@Param("param") Memo memo);


    @Query("select m from Memo m where m.mno > :mno")
    Page<Memo> getListWithQuery(@Param("mno") Long mno, Pageable pageable);

    @Query("select m.mno, m.memoText, CURRENT DATE " + "from Memo m where m.mno > :mno")
    Page<Object[]> getObjectWithQuery(@Param("mno") Long mno, Pageable pageable);
}
