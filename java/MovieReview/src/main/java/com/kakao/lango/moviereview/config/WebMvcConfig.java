package com.kakao.lango.moviereview.config;

import com.kakao.lango.moviereview.aop.MeasuringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 웹 설정 클래스
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 읹터셉터 설정 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns에 적용한 URL 경로에 접근하기 전에 인터셉터가 가로챈다.
        registry.addInterceptor(new MeasuringInterceptor()).addPathPatterns("/user/*");
    }
}
