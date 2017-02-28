<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="com.coolcuy.dto.FreeBoardDto" %>
<%@page import="com.coolcuy.dao.FreeBoardDaoImpl" %>
<% 
	FreeBoardDaoImpl dao= new FreeBoardDaoImpl();
	String nowPage= request.getParameter("nowPage");
	int num= Integer.parseInt(request.getParameter("num"));
	int ref=dao.get(num).getRef();
	if(request.getParameter("pass") !=null){
		String inPass= request.getParameter("pass");
		FreeBoardDto dto= (FreeBoardDto) session.getAttribute("freeBoardDto");
		String dbPass= dto.getPass();
		if(inPass.equals(dbPass)){
			dao.delete(ref);
			String url = "FreeBoard.jsp?nowPage=" +nowPage;
			response.sendRedirect(url);
			
		}else{
%>
	<script type="text/javascript">
	alert("입력하신 비밀번호가 아닙니다.");
	history.back();
	</script>
	<%}
	}else{
	%>	
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function check(){
	if(document.delFrm.pass.value==""){
		alert("패스워드를 입력하세요.");
		document.delFrm.pass.focus();
		return false;
	}
	document.delFrm.submit();
}
</script>
</head>
<body>
 <jsp:include page="/top.jsp" />
<div align="center">
<br/><br/>
<table width="50%" cellspacing="0" cellpadding="3">
		<tr>
				<td bgcolor=#dddddd height="21" align="center">
				사용자의 비밀번호를 입력해주세요
				</td>
		</tr>
</table>
<form name="delFrm" method="post" action="Freedelete.jsp">
	<table width="70%" cellspacing="0" cellpadding="2">
	<tr>
		<td align="center">
			<table align="center" border="0" width=91%>
				<tr>
				<td align= "center">
				<input type="password" name="pass" size="17" maxlength="15">
				</td>
				</tr>
				<tr>
				<td><hr size="1" color="#eeeeee"></td>
				</tr>
				<tr>
				<td align="center">
				<input type="button" value="삭제완료" onClick="check()">
				<input type="reset" value="다시쓰기">
				<input type="button" value="뒤로" onClick="history.go(-1)">
				</td>
				</tr>
			</table>
</td>
</tr>
</table>
<input type="hidden" name="nowPage" value="<%=nowPage%>">
<input type="hidden" name="num" value="<%=num%>">
</form>
</div>
<%}%>

</body>
</html>