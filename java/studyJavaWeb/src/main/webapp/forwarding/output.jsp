<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-29
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>request 객체: <%= request.getAttribute("result") %></h1>
    <h1>session 객체: <%= session.getAttribute("result") %></h1>
    <h1>application 객체: <%= application.getAttribute("result") %></h1>
</body>
</html>
