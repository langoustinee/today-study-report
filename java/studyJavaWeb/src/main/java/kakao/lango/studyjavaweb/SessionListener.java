package kakao.lango.studyjavaweb;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class SessionListener implements HttpSessionListener {
    // 접속자 수를 저장할 전역변수
    private static int count;
    
    // 외부로 현재 접속자 수를 전달할 메서드
    public static int getCount() {
        return count;
    }

    public SessionListener() {
    }

    // 새로운 접속이 생겨 세션이 만들어 질 때
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        count++;
        System.out.println("현재 접속자 수:" + count);
        HttpSession session = se.getSession();
        System.out.println("session id: " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }
}
