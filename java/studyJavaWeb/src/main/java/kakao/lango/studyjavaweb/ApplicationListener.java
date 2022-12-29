package kakao.lango.studyjavaweb;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class ApplicationListener implements ServletContextListener {

    public ApplicationListener() {
    }

    // 웹 서버가 구동될 때 호출되는 메서드
    @Override
    public void contextInitialized(ServletContextEvent event) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("web server staet!");
    }

    // 웹 서버가 종료될 때 호출되는 메서드
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println("web server destroy!");
    }
}
