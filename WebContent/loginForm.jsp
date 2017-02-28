<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	request.setCharacterEncoding("UTF-8");%>
<link rel="stylesheet" href="/version0116/css/style.css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>

<jsp:include page="/top.jsp"/>

<form action="login.do" method="post">
<c:if test="${errors.idOrPwNotMatch}">
아이디와 암호가 일치하지 않습니다.
</c:if>

<p>
	<c:if test="${errors.email}">ID를 입력하세요</c:if>
</p>
<p>
	아이디:<br/><input type="text" name="email" value="${param.email}">
	<c:if test="${errors.email}">ID를 입력하세요</c:if>
</p>
<p>
	암호:<br/><input type="password" name="password"  maxlength="14">
	<c:if test="${errors.password}">암호를 입력하세요</c:if>
</p>	
<input type="submit" value="로그인">
</form>
</body>
</html>