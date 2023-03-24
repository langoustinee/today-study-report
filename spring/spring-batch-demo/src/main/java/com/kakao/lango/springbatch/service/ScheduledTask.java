package com.kakao.lango.springbatch.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ScheduledTask {
    // 스케줄링에 수행할 작업 - 1초마다 수행하기
    @Scheduled(fixedRate= 1000L)
    public void triggerEvent() {
        log.info("1초마다 배치 작업 수행");
    }
}
