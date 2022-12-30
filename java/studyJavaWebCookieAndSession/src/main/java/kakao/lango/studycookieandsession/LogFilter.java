package kakao.lango.studycookieandsession;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "*.jsp")
public class LogFilter implements Filter {

    public LogFilter() {
        super();
    }

    // 메모리 할당 후 처음 사용될 때 호출되는 메서드
    public void init(FilterConfig config) throws ServletException {
    }

    // 필터가 소멸될 때 호출되는 메서드
    public void destroy() {
    }

    // URL에 해당하는 요청이 왔을 떄 호출되는 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 여기에 작성하면 Controller가 처리하기 전에 수행된다.
        System.out.println("before Controller ... ");
        chain.doFilter(request, response);

        // 여기에 작성하면 Controoler가 처리한 후에 수행한다.
        System.out.println("after Controller ... ");
    }
}
