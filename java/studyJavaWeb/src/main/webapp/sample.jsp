<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-29
  Time: 오전 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>sample.jsp!!!</h1>
<%
    int sum = 0;
    for (int i = 0; i < 10; i++) {
        sum += i;
    }
%>
    <h2>합계는 <%= sum %> 입니다.</h2>
    <h2><%= request.getRemoteAddr() %></h2>
</body>
</html>
