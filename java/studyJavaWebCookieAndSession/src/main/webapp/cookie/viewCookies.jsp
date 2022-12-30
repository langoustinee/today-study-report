<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-30
  Time: 오전 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Cookies</title>
</head>
<body>
<h1>Cookie List ..</h1>
<%
    // 저장된 모든 쿠키 읽어오기
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        // 쿠키에서 한글을 읽어올 때는 디코딩을 해야 한다.
        String value = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
%>
<span>name:<%= cookie.getName() %> value:<%= value %></span><br>
<%
    }
%>
<script>
    // 모든 쿠키 읽어와서 price라는 name의 쿠키를 찾아오기
    let cookies = document.cookie;
    let start = cookies.indexOf("price");
    let cValue = "";

    // 찾고자 하는 쿠키가 있다면
    if (start !== -1) {
        start += "price".length;
        let end = cookies.indexOf(";", start);
        if (end === -1) end = cookies.length;
        let cookie = decodeURI(cookies.substring(start+1, end));
        console.log(cookie);
    }
    else console.log("존재하지 않는 쿠키입니다.");

</script>
</body>
</html>
