package com.kakao.lango.springsecurityclub.service;

import com.kakao.lango.springsecurityclub.dto.ClubMemberJoinDTO;
import com.kakao.lango.springsecurityclub.model.ClubMember;
import com.kakao.lango.springsecurityclub.model.ClubMemberRole;
import com.kakao.lango.springsecurityclub.persistence.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(ClubMemberJoinDTO memberJoinDTO) throws MidExistException {
        // 아이디 중복 여부 확인하기
        String mid = memberJoinDTO.getMid();
        boolean exist = clubMemberRepository.existsById(mid);
        if (exist) {
            throw new MidExistException();
        }

        // 회원 가입을 위해 입력정보를 통해 ClubMember Entity 생성하기
        ClubMember member = ClubMember.builder()
                .mid(memberJoinDTO.getMid())
                .mpw(memberJoinDTO.getMpw())
                .email(memberJoinDTO.getEmail())
                .name(memberJoinDTO.getName())
                .social(memberJoinDTO.isSocial())
                .del(memberJoinDTO.isDel())
                .build();

        // 비밀번호 암호화하기
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        // 권한 설정하기
        member.addRole(ClubMemberRole.USER);
        log.info("[MemberServiceImpl] Club member-info: " + member);
        log.info("[MemberServiceImpl] Club member-role-set: " + member.getRoleSet());

        clubMemberRepository.save(member);
    }
}
