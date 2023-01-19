package com.kakao.lango.springsecurityclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringSecurityClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityClubApplication.class, args);
    }

}
