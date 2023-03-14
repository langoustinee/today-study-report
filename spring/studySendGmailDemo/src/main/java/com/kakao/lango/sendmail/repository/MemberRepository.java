package com.kakao.lango.sendmail.repository;

import com.kakao.lango.sendmail.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberName(String username);
}
