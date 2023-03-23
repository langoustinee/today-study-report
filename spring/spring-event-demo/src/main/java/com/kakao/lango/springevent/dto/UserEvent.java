package com.kakao.lango.springevent.dto;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserEvent extends ApplicationEvent {
    // 회원의 탈퇴여부를 구분하기 위한 속성이다.
    private Type type;
    private Long userId;
    private String email;

    private UserEvent(Object sourve, Type type, Long userId, String email) {
        super(sourve);
        this.type = type;
        this.userId = userId;
        this.email = email;
    }

    // 생성자를 이용하지 않고 객체를 생성해주는 static 메소드
    // 싱글톤 패턴을 적용할 때나 생성하는 코드가 복잡할 때 주로 이용하는 패턴이다.
    public static UserEvent created(Object source, Long userId, String emailAddress) {
        return new UserEvent(source, Type.REGISTER, userId, emailAddress);
    }

    public enum Type {
        REGISTER, UNREGISTER
    }

}
