package com.kakao.lango.sendmail.security;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class RefreshTokenException  extends RuntimeException {
    private ErrorCase errorCase;
    public enum ErrorCase {
        NO_ACCESS, NO_REFRESH, OLD_REFRESH
    }
    // 생성자
    public RefreshTokenException(ErrorCase errorCase){
        super(errorCase.name());
        this.errorCase = errorCase;
    }
    // 에러메시지를 만드는 메소드
    public void sendResponseError(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // DTO 객체와 JSON 문자열 간 변환을 수행해주는 객체이다.
        Gson gson = new Gson();
        String responseStr = gson.toJson(Map.of("msg", errorCase.name(), "time", new Date()));
        // 클라이언트에게 메시지 전송하기
        try {
            response.getWriter().println(responseStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
