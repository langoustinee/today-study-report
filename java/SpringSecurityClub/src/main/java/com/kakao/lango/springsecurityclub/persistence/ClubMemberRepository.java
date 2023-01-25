package com.kakao.lango.springsecurityclub.persistence;

import com.kakao.lango.springsecurityclub.model.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    // mid를 파라미터로 social의 값이 flase인 모든 데이터를 찾아오는 메소드
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from ClubMember m where m.mid=:mid and m.social=false")
    Optional<ClubMember> getWitRoles(String mid);

    @EntityGraph(attributePaths = "roleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m where m.email=:email")
    Optional<ClubMember> findByEmail(String email);
}
