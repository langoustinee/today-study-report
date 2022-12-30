<%@ page import="kakao.lango.studylocallogin.dto.UserDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Local Login</title>
    </head>
    <body>
        <h1><%= "Local Login!" %></h1>
        <H2>로컬 로그인 구현하기</H2>

        <%
            Object loginInfo = session.getAttribute("loginInfo");
            // 로그인이 되었을 경우
            if (loginInfo == null) {
        %>
        <a href="login">로그인</a>
        <%
        } else {
            UserDTO dto = (UserDTO) loginInfo;
        %>
        <%= dto.getNickname() %> 님 환영합니다. <br>
        <a href="logout">로그아웃</a>
        <%
            }
        %>
    </body>
</html>