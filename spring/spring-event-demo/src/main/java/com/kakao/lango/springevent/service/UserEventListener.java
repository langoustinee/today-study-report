package com.kakao.lango.springevent.service;

import com.kakao.lango.springevent.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class UserEventListener implements ApplicationListener<UserEvent> {
    private final UserService userService;

    // 메시지 이벤트가 게시되었을 때 호출될 메서드
    // 메시지를 받았을 때 호출한다.
    @Override
    public void onApplicationEvent(UserEvent event) {
        if (UserEvent.Type.REGISTER == event.getType()) {
            log.info("Listen REGISTER event");
        } else if (UserEvent.Type.UNREGISTER == event.getType()) {
            log.info("Listen UNREGISTER event");
        } else {
            log.error("Unsupported event type. {}", event.getType());
        }
    }
}
