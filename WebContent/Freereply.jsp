<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.coolcuy.dto.FreeBoardDto"%>
<%@page import="com.coolcuy.dao.FreeBoardDaoImpl" %>
<%
	FreeBoardDto dto=new FreeBoardDto();
	FreeBoardDaoImpl dao=new FreeBoardDaoImpl();
	String nowPage= request.getParameter("nowPage");
	int num= Integer.parseInt(request.getParameter("num"));
	dto=dao.get(num);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="center">
<br><br>
	<table width="500" cellspacing="0" cellpadding="3">
		<tr>
			<td height="21" align="center">답변하기</td>
		</tr>
</table>

<form name="post" method="post" action="FreeReplyProc.do">
	<table width="550" cellspacing="0" cellpadding="7">
	<tr>
		<td align="center">
		<table border="0">
		<tr>
			<td width="20%">성 명</td>
			<td width="80%">
				<input type="text" name="name" size="30" maxlength="20">
			</td>
		</tr>
		<tr>
			<td width="20%">제목</td>
			<td width="80%">
			<input type="text" name="subject" size="50" value="   「답변」 : <%=dto.getSubject()%>" maxlength="50">
			</td>
		</tr>
		<tr>
			<td width="20%">내용</td>
			<td width="80%">
			<textarea name="textArea"rows="12" cols="50">
			<%=dto.getTextArea()%> <%=num %>
			
        ===========답변글을 쓰세여============
        
			</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%">비밀번호</td>
			<td width="80%">
			<input type="password" name="pass" size="15" maxlength="15">
			</td>
		</tr>
		<tr>
			<td colspan="2" height="5"><hr/></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="답변등록">
				<input type="reset" value="다시쓰기">
				<input type="button" value="뒤로" onclick="history.back()">
			</td>
		</tr>
		</table>
		</td>
	</tr>
	</table>
	<input type="hidden" name="nowPate" value="<%=nowPage%>"> 
	<input type="hidden" name="ref" value="<%=dto.getRef()%>"> 
	<input type="hidden" name="pos" value="<%=dto.getPos()%>"> 
	<input type="hidden" name="depth" value="<%=dto.getDepth()%>"> 


</form>

</div>

</body>
</html>