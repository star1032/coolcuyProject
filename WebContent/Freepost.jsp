<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/version0116/css/style.css">

</head>
<body>

<div id="wrap">

 <jsp:include page="/top.jsp" />
 
 <div align="center">
<br/><br/>
<table width="80%" cellspacing="0" cellpadding="3">
<tr>
	<td bgcolor= "84F399" height="25" align="center"> 글쓰기	</td>
</tr>
</table>
<br/>
<form method="post" action="FreepostProc.do">
<table width="80%" cellspacing="0" cellpadding="3" align="center">
<tr>
	<td align=center>
	<table border="0" width="100%" align="center">
	<tr>
		<td width="20%">성명</td>
		<td width="80%">
		<input type="text" name="name" size="10" maxlength="8"></td>
		</tr>
		<tr>
			<td width="20%">제목</td>
			<td width="80%">
		<input type="text" name="subject" size="50" maxlength="30"></td>
		</tr>
		<tr>
			<td width="20%">내용</td>
			<td width="80%">
		<textarea  name="textArea" row="100" cols="50"></textarea></td>
		</tr>
		<tr>
			<td width="20%">비밀번호</td>
			<td width="80%">
		<input type="password" name="pass" size="15" maxlength="15"></td>
		</tr>
		
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="다시쓰기">
		<input type="button" value="리스트" onClick="javascript:location.href='FreeBoard.jsp'">
		</td>
		</tr>
		</table>
		</td>
		</tr>
</table>

<input type="hidden" name="userId" value="${authUser.id}">



</form>
</div>
 
 </div>
</body>
</html>