package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m " +
            "from Member m " +
            "where m.nickname = :nickname")
    Member findMemberByNickname(String nickname);

}
