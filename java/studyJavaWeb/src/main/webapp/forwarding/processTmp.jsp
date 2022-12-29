<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-29
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 파라미터 읽어오기
    String first = request.getParameter("first");
    String second = request.getParameter("second");

    // 파라미터를 가지고 요청을 처리하기
    int result = Integer.parseInt(first) + Integer.parseInt(second);

    // 결과 저장하기
    request.setAttribute("result", result);
    session.setAttribute("result", result);
    application.setAttribute("result", result);

    System.out.println("처리 페이지");

    // 결과 페이지로 이동하기
    // 포워딩으로 이동하기
//    request.getRequestDispatcher("output.jsp").forward(request, response);

    // 리다이렉트로 이동하기
    response.sendRedirect("output.jsp");


%>
