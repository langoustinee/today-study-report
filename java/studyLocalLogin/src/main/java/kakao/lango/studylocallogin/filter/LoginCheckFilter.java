package kakao.lango.studylocallogin.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kakao.lango.studylocallogin.dto.UserDTO;
import kakao.lango.studylocallogin.service.UserService;
import kakao.lango.studylocallogin.service.UserServiceImpl;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    private UserService userService;

    public LoginCheckFilter() {
        super();
        userService = UserServiceImpl.getInstance();
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // request와 response를 형 변환해야 한다.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 로그인 요청
        if (req.getRequestURI().equals("/studyLocalLogin/login")) {
            // 모듡 쿠키를 읽어오기
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                // 쿠키에 uuid가 있다면 uuid값으로 로그인하기
                if (cookie.getName().equals("remember-me")) {
                    String uuid = cookie.getValue();
                    UserDTO dto = userService.login(uuid);
                    req.getSession().setAttribute("loginInfo", dto);
                    // 메인 페이지로 리다이렉트 하기
                    res.sendRedirect("./");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
