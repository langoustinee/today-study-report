package com.kakao.lango.springsecurityclub.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void encode() {
        String password = "kakao123";

        String encodedPwd = passwordEncoder.encode(password);
        System.out.println("encodedPwd: " + encodedPwd);

        encodedPwd = passwordEncoder.encode(password);
        System.out.println("encodedPwd: " + encodedPwd);

        boolean matchResult = passwordEncoder.matches(password, encodedPwd);
        System.out.println("matchResult: " + matchResult);
    }

}
