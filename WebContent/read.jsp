<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.coolcuy.dto.BoardDto" %>
<%@page import="com.coolcuy.dao.BoardDao" %>
<%@page import="com.coolcuy.dao.BoardDaoImpl" %>
<%
	BoardDao dao=new BoardDaoImpl();

	int num= Integer.parseInt(request.getParameter("num"));
	System.out.println(num);
	String nowPage= request.getParameter("nowPage");
	String keyField= request.getParameter("keyField");
	String keyWord= request.getParameter("keyWord");
	
	dao.upCount(num);
	BoardDto dto= dao.get(num);
	
	String name= dto.getName();
	String subject= dto.getSubject();
	String regdate= dto.getRegdate();
	String textArea= dto.getTextArea();
	String ip= dto.getIp();
	int cnt= dto.getCnt();
	session.setAttribute("dto", dto);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel= "stylesheet" type="text/css">
<script type="text/javascript">
 function list(){
	 document.listFrm.action="list.jsp";
	 document.listFrm.submit();
 }
</script>
</head>
<body>
 <jsp:include page="/top.jsp" />
<br/><br/>
<table align="center" width="70%" border=0 collspacing="3" cellpadding="0">
	<tr>
		<td bgcolor="#9CA2EE" height="25" align="center">글읽기</td>
	</tr>
	<td colspan="2">
	<table border="0" cellpadding="3" cellspacing="0" width=100%>
	<tr>
		<td align="center" bgcolor="#DDDDDD" width="10%">이름 </td>
		<td bgcolor="#FFFFE8"><%=name%></td>
		<td align="center" bgcolor="#DDDDDD" width="10%">등록날짜 </td>
		<td bgcolor="#FFFFE8"><%=regdate%></td>
</tr>
<tr>
		<td align="center" bgcolor="#DDDDDD">제목 </td>
		<td bgcolor="#FFFFE8" colspan="3"><%=subject%></td>
</tr>
<tr>
		<td colspan="4"><br/><pre><%=textArea%></pre><br/><br/></td>
</tr>
<tr>
		<td colspan="4" align="right">
		<%=ip%>로 부터 글을 남기셨습니다. / 조회수 <%=cnt%>
		</td>
</tr>
</table>
</td>
</tr>
<tr>
	<td align= "center" colspan="2">
	<hr/>
	[ <a href= "javascript:list()" >리스트</a> |
	<a href="update.jsp?nowPage=<%=nowPage%>&num=<%=num%>"> 수정</a> |
	<a href="delete.jsp?nowPage=<%=nowPage%>&num=<%=num%>"> 삭제</a>	] <br>
	</td>
	</tr>
</table>

<form name="listFrm" method="post">
<input type="hidden" name="num" value="<%=num %>">
<input type="hidden" name="nowPage" value="<%=nowPage %>">
<%if(!(keyWord==null || keyWord.equals(""))){ %>
<input type="hidden" name="keyField" value="<%=keyField %>">
<input type="hidden" name="keyWord" value="<%=keyWord %>">
<%}%>


</form>

</body>
</html>