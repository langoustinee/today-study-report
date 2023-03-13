package com.kakao.lango.sendmail.controller;

import com.kakao.lango.sendmail.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MailService mailService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("textmail")
    public void textmail(){ }

    @PostMapping("/textmail")
    public String textmail(HttpServletRequest request) throws Exception {
        mailService.sendMail(request);
        return "redirect:/";
    }

}
