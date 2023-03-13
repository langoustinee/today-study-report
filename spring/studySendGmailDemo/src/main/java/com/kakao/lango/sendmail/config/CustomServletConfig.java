package com.kakao.lango.sendmail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CustomServletConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //files 로 요청하면 static 디렉토리에서 뷰를 찾아오는 설정
        registry.addResourceHandler("/files/**")
                .addResourceLocations("classpath:/static/");

    }

}
