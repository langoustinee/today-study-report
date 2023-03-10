package com.kakao.lango.communication.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Random;
@Service
public class WebPushService {
    public void push(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            // 랜덤한 숫자를 클라이언트로 보내기
            Random r = new Random();
            writer.write("data: " + (r.nextInt(46)+1) + "\n\n");
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        writer.close();
    }
}