package com.kakao.lango.sendmail.security.handler;

import com.google.gson.Gson;
import com.kakao.lango.sendmail.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    // final로 설정된 데이터를 생성자에서 대입받아서 주입한다.
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("Login Success Handler------------------------");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info("login user: " + authentication.getName());

        // 토큰 정보 및 정보 생성하기
        Map<String, Object> claim = Map.of("mid", authentication.getName());
        //Access Token 유효기간 1일
        String accessToken = jwtUtil.generateToken(claim, 1);
        //Refresh Token 유효기간 30일
        String refreshToken = jwtUtil.generateToken(claim, 10);

        // 생성한 토큰을 문자열로 변경
        Gson gson = new Gson();
        Map<String,String> keyMap = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken);
        String jsonStr = gson.toJson(keyMap);
        // 클라이언트에게 전송
        response.getWriter().println(jsonStr);
    }
}
