package com.kakao.lango.sendmail.security.filter;

import com.kakao.lango.sendmail.security.AccessTokenException;
import com.kakao.lango.sendmail.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
        // 
        String headerStr = request.getHeader("Authorization");
        // 아래 조건을 만족하지 않는다면 예외 발생
        if(headerStr == null || headerStr.length() < 8){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }
        // Bearer 생략, 실제 토큰 가져오기
        String tokenType = headerStr.substring(0,6);
        String tokenStr = headerStr.substring(7);
        // 타입 검사하기
        if(tokenType.equalsIgnoreCase("Bearer") == false){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }
        // 토큰의 유효성 검사하기
        try{
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);
            return values;
        }catch(MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        }catch(SignatureException signatureException){
            log.error("SignatureException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        }catch(ExpiredJwtException expiredJwtException){
            log.error("ExpiredJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException
    {
        // 클라이언트의 URI 확인하기
        String path = request.getRequestURI();
        // api로 시작하지 않는 요청이라면 다음 필터로 넘긴다.
        // doFilterInternal는 리턴 타입이 void이기 때문에 리턴을 안해도 될 것 같지만, 리턴을 반드시 해야한다.
        // return을 만날 때까지 무조건 수행된다.
        if (!path.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Token Check Filter..........................");
        log.info("JWTUtil: " + jwtUtil);

        try {
            validateAccessToken(request);
            // 다음 필터에게 처리를 넘긴다.
            // AOP, Filter, Interceptor 등으로 넘길 수 있다.
            filterChain.doFilter(request, response);
        }catch(AccessTokenException accessTokenException){
            accessTokenException.sendResponseError(response);
        }
    }
}
