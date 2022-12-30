package kakao.lango.studylocallogin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import kakao.lango.studylocallogin.dto.UserDTO;
import kakao.lango.studylocallogin.service.UserService;
import kakao.lango.studylocallogin.service.UserServiceImpl;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    // 컨트롤러는 항상 서비스를 주입받아야 한다.
    private UserService userService;

    public LoginController() {
        super();
        // UserService의 구현체인 UserServiceImpl 주입하기
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request login ...");
        // 페이지 이동 수행하기
        // 로그인 요청이 오면 webapp/user/login.jsp로 이동시킨다.
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 실제 로그인을 처리하기

        // 파라미터 가져오기
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        // 자동 로그인 여부 읽어오기
        // checkbox는 value가 없을 때 체크하면 on이고 아니면 null이다.
        String auto = request.getParameter("auto");

        String uuid;
        if (auto == null) {
            uuid = null;
        } else {
            uuid = UUID.randomUUID().toString();
        }

        // 서비스 메서드 호출하기
        UserDTO dto = userService.login(userid, password, uuid);

        // 서비스 메서드의 결과에 대한 분기
        HttpSession session = request.getSession();

        if (dto == null) {
            session.invalidate();
            // 로그인 페이지로 되돌아가기
            response.sendRedirect("login?error=error");
        } else {
            // 세션에 로그인정보 저장하기
            session.setAttribute("loginInfo", dto);
            if (uuid != null) {
                // 쿠키를 생성하여 저장하기
                Cookie rememverCookie = new Cookie("remember-me", uuid);
                // 유효기간 설정하기
                rememverCookie.setMaxAge(60 * 60 * 24 * 2);
                rememverCookie.setPath("/");
                response.addCookie(rememverCookie);
            }
            // 메인 페이지로 리다이렉트하기
            response.sendRedirect("./");
        } 
    }
}
