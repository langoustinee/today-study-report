package com.kakao.lango.sendmail.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JWTUtil {
    @Value("${com.kakao.lango.secret}")
    private String key;

    // 토큰 생성 메소드
    public String generateToken(Map<String, Object> valueMap, int days){
        log.info("generate Token: " + key);

        // 헤더 부분
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        // payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);

        // 유효 시간(테스트 시에는 짧게 설정)
//        int time = (1) * days; //테스트는 분 단위로 나중에 60*24 (일)단위변경

        // 이후 10분 단위로 조정
        int time = (10) * days; //테스트는 분단위로 나중에 60*24 (일)단위변경

        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()
                ))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
        return jwtStr;
    }

    // 토큰의 유효성 검사 메소드
    public Map<String, Object> validateToken(String token) throws JwtException {
        Map<String, Object> claim = null;
        claim = Jwts.parser()
                .setSigningKey(key.getBytes()) // Set Key
                .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                .getBody();
        return claim;
    }
}