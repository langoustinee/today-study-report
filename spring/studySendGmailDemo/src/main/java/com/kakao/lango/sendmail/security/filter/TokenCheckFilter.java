package com.kakao.lango.sendmail.security.filter;

import com.kakao.lango.sendmail.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
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
        filterChain.doFilter(request, response);
    }
}
