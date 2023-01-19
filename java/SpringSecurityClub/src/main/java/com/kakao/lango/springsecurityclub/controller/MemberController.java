package com.kakao.lango.springsecurityclub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    @GetMapping("/login")
    public void login(String error, String logout) {
        // error는 로그인이 실패했을 경우의 파라미터
        log.info("error: " + error);
        // logout은 로그아웃한 후 로그인으로 이동했을 때 파라미터
        log.info("logout: " + logout);
        if (error != null) {

        }

        if (logout != null) {

        }

    }

}
