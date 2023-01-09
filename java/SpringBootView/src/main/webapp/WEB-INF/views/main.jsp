<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Spring Boot - JSP</title>
    <meta charset="UTF-8" />
</head>
<body>
<h2>Message: <%=request.getAttribute("message")%></h2>
</body>
</html>