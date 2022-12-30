<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-12-30
  Time: 오전 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ID Save Page</title>
</head>
<body>
  <h1>Id to storage Save Page</h1>
  <form action="#" id="loginForm">
    <p>
      ID: <input type="text" id="id" name="id"required />
      <input type="checkbox" value="check" id="idSave" />아이디 저장
    </p>
    <p>
      <input type="submit" value="로그인" />
    </p>
  </form>
</body>
<script>
  // form과 아아디, 아이디 저장여부의 값을 가져오기
  let form = document.getElementById("loginForm");
  let ids = document.getElementById("id");
  let saveId = document.getElementById("idSave");

  // 최초 화면 로딩할 때 ids 존재 여부를 확인하여 처리하기
  window.addEventListener("load", (e) => {
    if (typeof localStorage.ids !== "undefined") {
      ids.value = localStorage.ids;
      saveId.checked = true;
    }
  });

  // form의 데이터 전송할 때 로컬 스토리지 삽입하기
  form.addEventListener("submit", (e) => {
    if (saveId.checked === true) localStorage.ids = ids.value;
    else localStorage.clear();
  });
</script>
</html>