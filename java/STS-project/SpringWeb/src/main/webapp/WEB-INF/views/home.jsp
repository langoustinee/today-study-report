<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<!-- JSTL 사용을 위한 태그 라이브러리 설정(JSP에서만 사용할 수 있다.) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<!-- link는 파일의 위치기준이 아니라 URL 기준이다. -->
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>

	<div align="center" class="body">
		<H2>상품 목록</H2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">상품 번호</th>
				<th align="center" width="320">상품 이름</th>
				<th align="center" width="100">가격</th>
			</tr>
			<c:forEach var="item" items="${list}">
				<tr class="record">
					<td align="center" width="80">${item.itemid}</th>
					<td align="center" width="80">${item.itemname}</th>
					<td align="center" width="80">${item.price}</th>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- <h1><a href="hello">Hello Spring MVC</a></h1>
	<br>
	<h1><a href="message/detail/3">URL In Parameter</a></h1>
	<br>
	<h1><a href="redirect">Redirects</a></h1>
	
		form의 action은 생략하면 현재 요청
		method는 기본값이 GET이다. 
		enctype은 파일이 있는 경우 multipart/form-data로 설정한다. 그외는 생략해도 된다.
		id는 스크립트에서 form에 접근하기 위해 사용한다.
	
	<fieldset>
		<legend>paramOne</legend>
		<form action="paramOne">
		<p>
			이름: <input type="text" name="name" />
		</p>
		<p>
			비밀번호: <input type="password" name="password" />
		</p>
		<input type="submit" value="전송" />	
	</form>
	</fieldset>
	<fieldset>
		<legend>paramSecond</legend>
		<form action="paramSecond" method="POST">
			<p>
				이름: <input type="text" name="name" />
			</p>
			<p>
				비밀번호: <input type="password" name="password" />
			</p>
			<input type="submit" value="전송" />	
		</form>
	</fieldset>
	<fieldset>
		<legend>paramThird</legend>
		<form action="paramThird" method="POST" enctype="multipart/form-data">
		<form action="paramThird" method="POST">
			<p>
				이름: <input type="text" name="name" />
			</p>
			<p>
				비밀번호: <input type="password" name="password" />
			</p>
			<p>
				파일: <input type="file" name="file" />
			</p>
			<input type="submit" value="전송" />	
		</form>
	</fieldset> -->
</body>
</html>