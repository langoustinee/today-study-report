<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-30
  Time: 오후 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page ..</h1>
        <%
            String error = request.getParameter("error");
            System.out.println(error);
            if (error != null) {

        %>
            <div>아이디나 비밀번호가 틀렸습니다. 다시 입력해주세요.</div>
        <%
            }
        %>
        <form method="POST">
            <p>
                Id: <input type="text" id="userid" name="userid" />
            </p>
            <p>
                Password: <input type="password" id="password" name="password">
            </p>
            자동로그인 <input type="checkbox" name="auto" /><br>
            <input type="submit" value="로그인">
        </form>
    </body>
</html>