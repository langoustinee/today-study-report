package com.kakao.lango.springsecurityclub.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
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
        log.info("[SampleController] all");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void member(){
        log.info("[SampleController] member");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin(){
        log.info("[SampleController] admin");
    }


}
