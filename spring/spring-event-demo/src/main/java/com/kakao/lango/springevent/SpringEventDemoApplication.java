package com.kakao.lango.springevent;

import com.kakao.lango.springevent.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringEventDemoApplication {

    public static void main(String[] args) {
        //        SpringApplication.run(SpringEventDemoApplication.class, args);
        SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(SpringEventDemoApplication.class);
        SpringApplication application = appBuilder.build();
        ConfigurableApplicationContext context = application.run(args);

        UserService userService = context.getBean(UserService.class);
        userService.createUser(777L, "lango@kakao.com");

    }

}
