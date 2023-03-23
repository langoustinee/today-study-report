package com.kakao.lango.springevent.service;

import com.kakao.lango.springevent.dto.UserEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    // 게시하는 메소드
    public void publishUserCreated(Long userId, String emailAddress) {
        // 메시지 생성하기
        UserEvent userEvent = UserEvent.created(this, userId, emailAddress);
        log.info("[게시] Publish user created event.");
        // 이벤트 게시하기
        applicationEventPublisher.publishEvent(userEvent);
    }
}
