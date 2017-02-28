<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.coolcuy.dao.QnADaoImpl" %>
<%@page import="com.coolcuy.dto.QnADto" %>

<% 
	int num= Integer.parseInt(request.getParameter("num"));
	String nowPage=request.getParameter("nowPage");
	QnADto dto= (QnADto)session.getAttribute("dto");
	String subject= dto.getSubject();
	String textArea=dto.getTextArea();
	String name= dto.getName();
%>
<title>Insert title here</title>

</head>
<body>
  <jsp:include page="/top.jsp" />  
<br/><br/>
수정하기
<form method="post" action="QnAupdateProc.do">
성명  <input type ="text" name="name" value="<%=name%>" size="30">
제목  <input type ="text" name="subject" value="<%=subject%>" size="30">
내용  <textarea name="textArea" rows="10" cols="50"><%=textArea%></textarea>
<br>
비밀번호 <input type="password" name="pass" size="15">
<br>
<br>
<br>
<input type="submit" value="수정완료" "> 
<%-- <%if(request.getParameter("passwordError")!=null){ --%>
<%-- 	%> --%>
<%-- 	<%=request.getParameter("passwordError") %> --%>
<%-- 	<%} %> --%>
<input type="reset" value="다시수정">
<input type="button" value="뒤로" onClick="history.go(-1)">

<input type="hidden" name="nowPage" value="<%=nowPage %>">
<input type="hidden" name="num" value="<%=num%>">
</form>

</body>
</html>