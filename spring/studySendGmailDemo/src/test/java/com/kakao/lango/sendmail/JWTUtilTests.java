package com.kakao.lango.sendmail;

import com.kakao.lango.sendmail.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTests {
    @Autowired
    private JWTUtil jwtUtil;
    @Test
    public void testGenerate() {
        Map<String, Object> claimMap = Map.of("memberName","ABCDE");
        String jwtStr = jwtUtil.generateToken(claimMap,1);
        System.out.println(jwtStr);
    }
    @Test
    public void testValidate(){
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzg3NjA1ODUsIm1lbWJlck5hbWUiOiJBQkNERSIsImlhdCI6MTY3ODc1OTk4NX0.OUFN5h13xOtyDpX1ql0UL91fucJhuJgQWAKYYgjLkM4";
        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        System.out.println(claim);
    }
}
