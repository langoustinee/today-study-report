package com.kakao.lango.moviereview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        log.info("[HomeController] home");
        return "redirect:/movie/list";
    }

    @GetMapping("/user")
    public String user() {
        log.info("[HomeController] user");
        return "user";
    }

    @GetMapping("/user/info")
    public String userInfo() {
        log.info("[HomeController] userInfo");
        return "userInfo";
    }

}
