package com.kakao.lango.springsecurityclub.controller;

import com.kakao.lango.springsecurityclub.security.dto.ClubMemberSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/sample/")
@Controller
public class SampleController {

    @GetMapping("/")
    public void index() {
        log.info("[SampleController] index");
    }
    @GetMapping("/all")
    public void all() {
        log.info("[SampleController] allow all");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal ClubMemberSecurityDTO clubAuthMember){
        log.info("[SampleController] allow member");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("[SampleController] auth name: " + auth.getName());
        log.info("[SampleController] Login user-info: " + clubAuthMember);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin(){
        log.info("[SampleController] allow admin");
    }


}
