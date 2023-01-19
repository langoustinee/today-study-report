package com.kakao.lango.springsecurityclub.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 아이디를 입력하고 로그인 요청을 하게되면 아이디에 해당하는 데이터를 찾아오는 메소드
    // 로그인 처리를 해야 한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[CustomUserDetailService] loadUserByUsername: " + username);

        // 로그인에 성공했을 경우
        // 실제로는 데이터베이스에서 읽어서 설정해야 한다.
        UserDetails userDetails = User.builder()
                .username("kakao")
                .password(passwordEncoder.encode("kakao"))
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }
}
