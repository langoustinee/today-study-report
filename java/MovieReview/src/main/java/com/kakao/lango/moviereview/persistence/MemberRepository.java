package com.kakao.lango.moviereview.persistence;

import com.kakao.lango.moviereview.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
