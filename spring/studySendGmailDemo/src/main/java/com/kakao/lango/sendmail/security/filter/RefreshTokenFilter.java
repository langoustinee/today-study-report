package com.kakao.lango.sendmail.security.filter;

import com.google.gson.Gson;
import com.kakao.lango.sendmail.security.RefreshTokenException;
import com.kakao.lango.sendmail.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {
    // 눈에 보이지 않지만 아래와 같은 생성자가 만들어진다.
    // RefreshTokenFilter(String refreshPath, JWTUtil jwtUtil) {
    //       this.refreshPath = refreshPath;
    //       this.jwtUtil = jwtUtil;
    // }
    private final String refreshPath;
    private final JWTUtil jwtUtil;

    private Map<String,String> parseRequestJSON(HttpServletRequest request) {
    //JSON 데이터를 분석해서 mid, mpw 전달 값을 Map으로 처리
        try(Reader reader = new InputStreamReader(request.getInputStream())){
            Gson gson = new Gson();
            return gson.fromJson(reader, Map.class);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    // 엑세스 토큰을 검증하는 메서드
    private void checkAccessToken(String accessToken)throws RefreshTokenException {
        try{
            jwtUtil.validateToken(accessToken);
        } catch (ExpiredJwtException expiredJwtException){
            log.info("Access Token has expired");
        } catch(Exception exception){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_ACCESS);
        }
    }

    // 리프레쉬 토큰을 검증하는 메서드
    private Map<String, Object> checkRefreshToken(String refreshToken) throws RefreshTokenException{
        try {
            Map<String, Object> values = jwtUtil.validateToken(refreshToken);
            return values;
        } catch (ExpiredJwtException expiredJwtException){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
        } catch (MalformedJwtException malformedJwtException){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        } catch (Exception exception){
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        }
    }

    // 토큰을 전송하는 메소드
    private void sendTokens(String accessTokenValue, String refreshTokenValue, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("accessToken", accessTokenValue, "refreshToken", refreshTokenValue));
        try {
            response.getWriter().println(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 클라이언트의 요청 URL
        String path = request.getRequestURI();
        // URL이 다르면 다음 필터로 넘기도록 한다.
        if (!path.equals(refreshPath)) {
            log.info("skip refresh token filter.....");
            // 다음 필터로 진행하고 현재 필터의 동작을 중지시키기 위해서는 반드시 return을 해야 한다.
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Refresh Token Filter run!");

        //전송된 JSON에서 accessToken과 refreshToken을 얻어온다.
        Map<String, String> tokens = parseRequestJSON(request);
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");
        log.info("accessToken: " + accessToken);
        log.info("refreshToken: " + refreshToken);

        try{
            checkAccessToken(accessToken);
        }catch(RefreshTokenException refreshTokenException){
            refreshTokenException.sendResponseError(response);
            return;
        }
        
        Map<String, Object> refreshClaims = null;
        try {
            refreshClaims = checkRefreshToken(refreshToken);
            log.info(refreshClaims);
        }catch(RefreshTokenException refreshTokenException){
            refreshTokenException.sendResponseError(response);
            return;
        }

        // 토큰을 생성해서 전송하는 로직은 아래와 같다.
        // Refresh Token의 유효시간이 얼마 남지 않은 경우
        Integer exp = (Integer) refreshClaims.get("exp");
        Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli() * 1000);
        Date current = new Date(System.currentTimeMillis());

        // 만료 시간과 현재 시간의 간격 계산
        // 만일 3일 미만인 경우에는 Refresh Token도 다시 생성
        long gapTime = (expTime.getTime() - current.getTime());
        log.info("-----------------------------------------");
        log.info("현재시간(current): " + current);
        log.info("만료시간(expTime): " + expTime);
        log.info("남은시간(gap): " + gapTime );

        // 토큰을 만들 때 사용한 id를 찾아오기
        String mid = (String)refreshClaims.get("mid");
        // 무조건 AccessToken을 새로 발급하기
        String accessTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);

        // RefrshToken이 3일도 안남은 경우 재발급하기
        String refreshTokenValue = tokens.get("refreshToken");
        if(gapTime < (1000 * 60 * 3 ) ){
        // if(gapTime < (1000 * 60 * 60 * 24 * 3 ) ){
            log.info("new Refresh Token required... ");
            refreshTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 30);
        }
        log.info("Refresh Token result....................");
        log.info("accessToken: " + accessTokenValue);
        log.info("refreshToken: " + refreshTokenValue);
        sendTokens(accessTokenValue, refreshTokenValue, response);
    }
}
