package kakao.lango.studyjavaweb;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "PageController", value = "/processTmp.jsp")
public class PageController extends HttpServlet {

    // 서비스에 대한 참조 변수 선언하기
    private PageService pageService;

    public PageController() {
        // 생성자에서 서비스 생성하기
        // 나중에는 주입을 받아 사용한다.
        pageService = new PageServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터 읽어오기
        String a = request.getParameter("a");
        String b = request.getParameter("b");

        // 서비스 메서드 호출하기
        // 파라미터의 자료형 변환은 서비스에서 수행해도 무방하다.
        // Spring은 일반적으로 컨트롤러에서 형 변환을 한다.
        int result = pageService.getSum(Integer.parseInt(a), Integer.parseInt(b));

        // 결과를 저장하기
        request.setAttribute("result", result);
        // 세션의 경우는 만들어서 사용해야 한다.
        request.getSession().setAttribute("result", result);

        // 뷰 페이지를 결정하고 데이터를 전달하기
        response.sendRedirect("output.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
