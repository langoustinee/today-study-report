<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 스프링 태그 라이브러리 설정하기 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 스프링 폼 라이브러리 설정하기 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.form.title" /></title>
</head>
<body>
	<%-- <form action="loginform" method="post">
		<p>
			<label for="email"><spring:message code="email" /></label>:
			<input type="text" name="email" id="email">
		</p>
		<p>
			<label for="password"><spring:message code="password" /></label>
			<input type="password" name="password" id="password">
		</p>
		<input type="submit" value="<spring:message code="login.form.login" />">
	</form> --%>
	<form:form modelAttribute="member">
		<p>
			<label for="email"><spring:message code="email" /></label>:
			<form:input path="email" />
			<form:errors path="email" />
		</p>
		<p>
			<label for="password"><spring:message code="password"/></label>:
			<form:password path="password" />
			<form:errors path="password" />
		</p>
		<p>
			<form:select path="loginType" items="${loginTypes}" />
		</p>
		<input type="submit" value="<spring:message code="login.form.login" />">
	</form:form>
	<ul>
		<li><spring:message code="login.form.help" /></li>
	</ul>
</body>
</html>