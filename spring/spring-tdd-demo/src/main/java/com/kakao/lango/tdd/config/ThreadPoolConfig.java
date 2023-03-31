package com.kakao.lango.tdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {
    // 빈이 소멸할 때 shutdown 메소드를 호출한다.
    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 스레드 풀에 만들어지는 스레드의 최대 개수
        // 이 개수가 코어의 개수보다 많으면 성능이 저하될 위험성이 있다.
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setThreadNamePrefix("AsyncExecutor-");
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }
}
