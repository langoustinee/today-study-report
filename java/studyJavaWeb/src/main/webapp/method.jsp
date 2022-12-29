<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-29
  Time: 오후 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>request sample</title>
</head>
<body>
<a href="result.jsp?author=lango"></a>
<form method="POST" action="result.jsp">
    제목: <input type="text" name="title">
    <input type="submit" value="전송" />
</form>

<%
    // 모든 헤더의 정보를 가져오기
    // 브라우저나 운영체제 정보, 쿠키등을 확인할 수 있다.
    java.util.Enumeration<String> header = request.getHeaderNames();

    while (header.hasMoreElements()) {
        String name = header.nextElement();
        String value = request.getHeader(name);


%>
<%= name %>:<%= value %> <br>
<%
    }
%>
</body>
</html>
