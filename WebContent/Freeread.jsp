<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.coolcuy.dto.FreeBoardDto" %>
<%@page import="com.coolcuy.dao.FreeBoardDaoImpl" %>
<%@page import="java.util.List" %>
<link rel="stylesheet" href="/version0116/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/version0116/js/test.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	FreeBoardDaoImpl dao=new FreeBoardDaoImpl();
	List<FreeBoardDto> vlist =null;
	int num= Integer.parseInt(request.getParameter("num"));
	String nowPage= request.getParameter("nowPage");
	String keyField= request.getParameter("keyField");
	String keyWord= request.getParameter("keyWord");
	
	dao.upCount(num);
	FreeBoardDto freeBoardDto= dao.get(num);
	int listSize=0;	
	String name= freeBoardDto.getName();
	String subject= freeBoardDto.getSubject();
	String regdate= freeBoardDto.getRegdate();
	String textArea= freeBoardDto.getTextArea();
	String userId=freeBoardDto.getUserId();
	int cnt= freeBoardDto.getCnt();
	int ref= freeBoardDto.getRef();
	session.setAttribute("freeBoardDto", freeBoardDto);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel= "stylesheet" type="text/css">
<script type="text/javascript">
 function list(){
	 document.listFrm.action="FreeBoard.jsp";
	 document.listFrm.submit();
 }
 
</script>

</head>
<body>
<div id="wrap">
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
		<td align="center" bgcolor="#DDDDDD" width="10%">제목 </td>
		<td bgcolor="#FFFFE8"><%=subject%></td>
		<td align="center" bgcolor="#DDDDDD" width="10%">작성자 </td>
		<td bgcolor="#FFFFE8"><%=userId%></td>
</tr>
<tr>
		<td colspan="4"><br/><pre><%=textArea%></pre><br/><br/></td>
</tr>
<tr>
		<td colspan="4" align="right">
		</td>
</tr>
</table>
</td>
</tr>
<tr>
	<td align= "center" colspan="2">
	<hr/>
	[ <a href= "javascript:list()" >리스트</a> |
	<a href="Freeupdate.jsp?nowPage=<%=nowPage%>&num=<%=num%>"> 수정</a> |
	<a href="Freedelete.jsp?nowPage=<%=nowPage%>&num=<%=num%>"> 삭제</a>	] <br>
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
<br>
<br>
<form name="post" method="post" action="FreeReplyProc.do">
<div align="center">
		댓글
			<input type="text" name="textArea"  size="120" style="padding: 20px 0px">
			<input type="submit" value="댓글등록">
			<input type="hidden" name="userId" value="${authUser.id}">
</div>

	<input type="hidden" name="num" value="<%=num%>"> 
	<input type="hidden" name="nowPage" value="<%=nowPage%>"> 
	<input type="hidden" name="ref" value="<%=freeBoardDto.getRef()%>"> 
	<input type="hidden" name="pos" value="<%=freeBoardDto.getPos()%>"> 
	<input type="hidden" name="depth" value="<%=freeBoardDto.getDepth()%>">
</form>
<br><br>

 <%	
 
	vlist =dao.getRepleByRef(ref);
	listSize = vlist.size();
	
	if(vlist.isEmpty()){
		out.println("등록된 게시물이 없습니다.");
	}else{
		for(int i =0; i<listSize; i++){
			if(i==listSize) break;
			FreeBoardDto freeDto=vlist.get(i);
			int repleNum =freeDto.getNum();
			String repleName= freeDto.getName();
			String repleSubject = freeDto.getSubject();
			String repleRegdate =freeDto.getRegdate();
			String repleTextarea=freeDto.getTextArea();
			String reUserId=freeDto.getUserId();
			int repleDepth= freeDto.getDepth();
			int repleCnt= freeDto.getCnt();
			int replePos= freeDto.getPos();
			int repleRef= freeDto.getRef();
%>
<div>					
<form action="replyDelete.do" method="post">
  <%
								if(repleDepth>0){
									for(int j=0;j<repleDepth;j++){
										out.println("&nbsp;&nbsp");
									}
								}
							%>
		<input type="text" value="<%=repleTextarea%>" disabled="disabled" size="90" style="margin-left:150px ; padding: 5px 0px">
		
		<input type="button" value="댓글달기" class="reply_btn" >
		
		<input type="text" value="<%=reUserId%>" disabled="disabled" size="20" style="padding: 5px 5px">

		<c:set var="id" value="${authUser.id}" />
		<c:set var="user" value="<%=reUserId%>" />

		<c:if test="${id eq user}">
			<input type="hidden" name="repleNum" value="<%=repleNum%>">
			<input type="hidden" name="num" value="<%=num%>">
			<input type="hidden" name="nowPage" value="<%=nowPage%>">
			<input type="submit" value="삭제">
		</c:if>
</form>

<form action="FreeReReplyProc.do" method="post" >

			<input type="hidden" name="repleNum" value="<%=repleNum%>">
			<input type="hidden" name="num" value="<%=num%>">
			<input type="hidden" name="ref" value="<%=repleRef%>">
			<input type="hidden" name="pos" value="<%=replePos%>">
			<input type="hidden" name="depth" value="<%=repleDepth%>">
			<input type="hidden" name="name" value="<%=repleName%>">
			<input type="hidden" name="subject" value="<%=repleSubject%>">
			<input type="hidden" name="userId" value="${authUser.id}">
			
			<input type="hidden" name="nowPage" value="<%=nowPage%>">
		    <!-- <input type="submit"  class="what" value="확인"> -->



</form>
</div>


	<%} %>
<%} %>
</div>
</body>


</html>