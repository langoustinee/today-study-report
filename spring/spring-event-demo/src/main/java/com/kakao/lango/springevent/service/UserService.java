package com.kakao.lango.springevent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserService {
    private final UserEventPublisher userEventPublisher;

    public Boolean createUser(Long userName, String email) {
        log.info("회원가입");
        // 메시지 게시
        userEventPublisher.publishUserCreated(userName, email);
        return true;
        
    }
}
