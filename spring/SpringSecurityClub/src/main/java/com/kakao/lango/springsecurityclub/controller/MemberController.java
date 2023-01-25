package com.kakao.lango.springsecurityclub.controller;

import com.kakao.lango.springsecurityclub.dto.ClubMemberJoinDTO;
import com.kakao.lango.springsecurityclub.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    // error는 로그인이 실패했을 경우의 파라미터
    // logout은 로그아웃한 후 로그인으로 이동했을 때 파라미터
    @GetMapping("/login")
    public void login(String error, String logout) {
        if (logout != null) {
            log.info("[MemberContoller] logout !");
        }
    }

    @GetMapping("/join")
    public void join() {
        log.info("[MemberContoller] join request");
    }

    @PostMapping("/join")
    public String joinMember(ClubMemberJoinDTO memberJoinDTO,
                             RedirectAttributes rattr) {
        log.info("[MemberController] join memberJoinDTO: " + memberJoinDTO);
        try {
            // 회원 가입 성공
            memberService.join(memberJoinDTO);
        } catch (Exception e) {
            // 회원 가입 도중 예외가 발생할 경우 처리하는 구문
            rattr.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }
        rattr.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }
}
