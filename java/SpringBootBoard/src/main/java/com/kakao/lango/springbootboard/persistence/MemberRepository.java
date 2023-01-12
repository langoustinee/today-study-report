package com.kakao.lango.springbootboard.persistence;

import com.kakao.lango.springbootboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, String> {
}
