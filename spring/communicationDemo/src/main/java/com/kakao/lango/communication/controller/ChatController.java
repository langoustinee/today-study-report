package com.kakao.lango.communication.controller;

import com.kakao.lango.communication.service.WebPushService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final WebPushService webPushService;

    @RequestMapping("/push")
    public void push(HttpServletRequest request, HttpServletResponse response) {
        webPushService.push(request, response);
    }

    @RequestMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chatting");
        return mv;
    }
}
