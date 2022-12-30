<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Cookie</title>
</head>
<body>
<%
  // 쿠리를 생성하고 저장하기
  // value가 한글이라면 별도로 인코딩을 해야한다.
  Cookie cookie = new Cookie("title", java.net.URLEncoder.encode("쿠키에 들어갈 값입니다.","UTF-8"));
  response.addCookie(cookie);
%>
<h1><a href="viewCookies.jsp">쿠키 출력하기</a></h1>
<script>
    // 쿠키의 유효시간 설정 - 하루
    let expire = new Date();
    expire.setDate(expire.getDate() + (60*60*24));

    // 쿠키 만들기
    let cookie = "price" + "=" + encodeURI("10000원") + "; path=/";
    // 유효기간 설정하기
    cookie += '; expires=' + expire.toGMTString() + ";";

    // 쿠키 저장하기
    document.cookie = cookie;
</script>
</body>
</html>
