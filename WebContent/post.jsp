<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="/top.jsp" />
<div align="center">
<br/><br/>
<table width="80%" cellspacing="0" cellpadding="3">
<tr>
	<td bgcolor= "84F399" height="25" align="center"> 글쓰기	</td>
</tr>
</table>
<br/>
<form method="post" action="postProc.do">
<table width="80%" cellspacing="0" cellpadding="3" align="center">
<tr>
	<td align=center>
	<table border="0" width="100%" align="center">
	<tr>
		<td width="10%">성명</td>
		<td width="90%">
		<input type="text" name="name" size="10" maxlength="8"></td>
		</tr>
		<tr>
			<td width="10%">제목</td>
			<td width="90%">
		<input type="text" name="subject" size="500" maxlength="30"></td>
		</tr>
		<tr>
			<td width="10%">내용</td>
			<td width="90%">
		<textarea  name="textArea" row="10" cols="50"></textarea></td>
		</tr>
		<tr>
			<td width="10%">비밀번호</td>
			<td width="90%">
		<input type="password" name="pass" size="15" maxlength="15"></td>
		</tr>
		<tr>
			<td width="10%">내용타입</td>
			<td> HTML<input type=radio name="contentType" value="HTTP">
			&nbsp;&nbsp;&nbsp;
			TEXT<input type=radio name="contentType" value="TEXT" checked>
			</td>
		</tr>
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
			<td colspan="2">
		<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
				<input type="submit" value="등록">
				<input type="reset" value="다시쓰기">
		<input type="button" value="리스트" onClick="javascript:location.href='list.jsp'">
		</td>
		</table>
		</td>
		</tr>
</table>



</form>
</div>
</body>
</html>