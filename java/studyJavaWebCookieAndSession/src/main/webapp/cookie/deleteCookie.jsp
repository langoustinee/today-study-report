<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-30
  Time: 오전 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Cookie</title>
</head>
<body>
<h1>Cookie Delete..</h1>
  <%
    // title 쿠키 삭제
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("title")) {
        // title이라는 쿠키를 삭제하려면 유효기간을 과거로 돌려야 한다.
        Cookie c = new Cookie("title", "");
        c.setMaxAge(0);
        response.addCookie(c);
      }
    }
  %>
</body>
</html>
