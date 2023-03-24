package com.kakao.lango.springbatch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@Configuration
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 스레드 풀의 스레드 개수를 CPU 코어 수로 설정한다.
        // 스레드의 개수를 코어의 수로 설정했을 떄 최대의 효과를 볼 수 있다.
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        // 스레드의 이름 앞에 태그를 설정한다.
        taskScheduler.setThreadNamePrefix("스케줄러-");
        // 초기화
        taskScheduler.initialize();
        // 등록
        taskRegistrar.setTaskScheduler(taskScheduler);

    }
}
