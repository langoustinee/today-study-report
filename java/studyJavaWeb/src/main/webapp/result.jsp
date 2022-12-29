<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-29
  Time: 오후 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>result sample</title>
</head>
<body>
  <%
    // POST 방식의 파라미터 인코딩 설정
    request.setCharacterEncoding("UTF-8");
    String title = request.getParameter("title");
    String author = request.getParameter("author");
  %>
  <h2>제목은 <%= title %> 입니다.</h2>
  <h2>작성자는 <%= author %> 입니다.</h2>
</body>
</html>
