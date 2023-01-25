package com.kakao.lango.springsecurityclub.service;


import com.kakao.lango.springsecurityclub.dto.ClubMemberJoinDTO;

public interface MemberService {

    // 회원이 존재하는 경우 발생시킬 예외 클래스
    static class MidExistException extends Exception {

    }


    void join(ClubMemberJoinDTO memberJoinDTO) throws MidExistException;
}
