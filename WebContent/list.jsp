<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.coolcuy.dto.BoardDto" %>
<%@page import="com.coolcuy.dao.BoardDao" %>
<%@page import="com.coolcuy.dao.BoardDaoImpl"%>
    
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/version0116/css/board.css">
<%@page import="java.util.List" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

	BoardDao dao= new BoardDaoImpl();
	
	int totalRecord=0;	//전체레코드 수
	int numPerPage=10;	//페이지 당 레코드 수
	int pagePerBlock=15;//블럭 당 페이지 수
	
	
	int totalPage=0;	//전체 페이지 수
	int totalBlock=0;	//전체 블록 수
	
	int nowPage=1;		//현제 페이지
	int nowBlock=1;		//현제 블럭
	
	int start=0;		//DB의 select 시작 번호
	int end=10;			//시작번호로 부터 가져올 select 개수
	
	int listSize=0;		//현재 읽어온 게시물의 수
	
	String keyWord= "", keyField="";
	
	List<BoardDto> vlist =null;
	
	if(request.getParameter("keyWord") !=null){
		keyWord= request.getParameter("keyWord");
		keyField= request.getParameter("keyField");
	}
	
	if(request.getParameter("reload") !=null){
		if(request.getParameter("reload").equals("true")){
			keyWord="";
			keyField="";
		}
	}
	if(request.getParameter("nowPage") !=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	}
	start=(nowPage * numPerPage)-numPerPage;
//	end=numPerPage;
	end=start+10;
	totalRecord = dao.getTotalCount(keyField, keyWord);
	totalPage=(int)Math.ceil((double)totalRecord/numPerPage);
	nowBlock= (int)Math.ceil((double)nowPage/pagePerBlock);
	totalBlock= (int)Math.ceil((double)totalPage/pagePerBlock);
%>
<title>Insert title here</title>
<script type="text/javascript">
 function list(){
	 document.listFrm.action = "list.jsp";
	 document.listFrm.submit();
 }
 
 function pageing(page){
	 document.readFrm.nowPage.value= page;	 
	 document.readFrm.submit();
 }
 

 function block(value){
	 document.readFrm.nowPage.value=<%=pagePerBlock%>*(value-1)+1;
	 document.readFrm.submit();
 }
 
 function read(num){
	 document.readFrm.num.value=num;
	 document.readFrm.action="read.jsp";
	 document.readFrm.submit();
 }
 
</script>
</head>
<body>
<div id="wrap">
		<jsp:include page="/top.jsp" />

		<section id="row11">
		<div id="row1_text01">고객센터</div>
		<div id="row1_text02">
			<ul>
				<li><a href="#"><img
						src="/version0116/imag/member_img02.png"></a></li>
				<li class="text02_up"><a href="#">Home</a></li>
				<li class="text02_up"><a>></a></li>
				<li class="text02_up"><a href="#">고객센터</a></li>
			</ul>
		</div>
		</section>
		<section id="row2"> 
		
			<aside>
	           
	            <div class="row2_hd"><a href="#">고객센터</a></div>
	            <ul>
	                <li><a href="/version0116/list.jsp">공지사항</a></li>
	                <li><a href="/version0116/QnA.jsp">QnA</a></li>
	                <li><a href="/version0116/FreeBoard.jsp">자유게시판 </a></li>
	            </ul>
	        </aside>
	        
		<div class="row2_con">
			<div>
<br/>
<h2>공지사항</h2>
<br/>
<table align="center" border="0" width="100%">
				<tr>
					<td>Total : <%=totalRecord%>Articles(<font color="red"><%=nowPage%>/<%=totalPage%>Pages</font>)</td>
				</tr>					
</table>
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="3">
				<tr>
					<td align="center" colspan="2">
					<%
					vlist =dao.getBoardList(keyField, keyWord, start, end);
					listSize = vlist.size();
					if(vlist.isEmpty()){
						out.println("등록된 게시물이 없습니다.");
					}else{
					%>
					<table border="0" width="100%" cellpadding="2" cellspacing="1">
					<tr align="center"  height="120%">
							<td  bgcolor="#ddddbb">번호</td>	
							<td>제목</td>	
							<td>이름</td>	
							<td bgcolor="#ddddbb">날짜</td>	
							<td>조회수</td>
							</tr>
							<%
							for(int i =0; i<numPerPage; i++){
								if(i==listSize) break;
								BoardDto dto=vlist.get(i);
								int num =dto.getNum();
								String name= dto.getName();
								String subject = dto.getSubject();
								String regdate =dto.getRegdate();
								int cnt= dto.getCnt();
							%>
									<tr>
										<td align="center"  bgcolor="#ddddbb">
								<%=totalRecord-((nowPage-1)*numPerPage)-i%>
								</td>
								<td>
							
							<a href="javascript:read('<%=num%>')"><%=subject%></a>
							</td>
							<td align="center"><%=name%></td>
							<td align="center" bgcolor="#ddddbb"><%=regdate%></td>
							<td align="center"><%=cnt%></td>
							</tr>
								<%}//for %>

</table>
					<% } //if %>

</td>
</tr>
<tr>
	<td colspan="2"><br /><br /></td>
</tr>
<tr>
	<td>
	<!-- 페이징 및 블럭처리 start -->
	<%
	int pageStart= (nowBlock -1)*pagePerBlock +1;
	int pageEnd= ((pageStart + pagePerBlock) <totalPage) ? (pageStart +pagePerBlock): totalPage+1;
	
	if(totalPage !=0){
		if(nowBlock > 1){%>
		<a href="javascript:block('<%=nowBlock-1 %>')"> ←- </a><%}%>&nbsp;
		<%for (; pageStart < pageEnd; pageStart++){%>
		<a href="javascript:pageing( '<%=pageStart %>')">
		<%if(pageStart==nowPage) {%><font color="blue"><%}%>
			[<%=pageStart%>]
			<%if(pageStart==nowPage) { %></font> <%}%></a>
	<%}//for%>&nbsp;
	<%if (totalBlock >nowBlock) {%>
	<a href="javascript:block('<%=nowBlock+1 %>')">>></a>
	<%}%>&nbsp;
	<%}%>
	<!-- 페이징 및 블럭 처리 end -->
	</td>
	<td align="right">
	<a href="post.jsp">[글쓰기]</a>
	<a href="javascript:list()">[처음으로]</a>
	</td>
	</tr>
</table>
<hr width="80%" />
<form name ="searchFrm" method="post" action="list.jsp">

</form>
<form name="listFrm" method="post">
	<input type="hidden" name="reload" value="true">
	<input type="hidden" name="nowPage" value="1">	
</form>
<form name="readFrm" method="get">
	<input type="hidden" name="num">
	<input type="hidden" name="nowPage" value="<%=nowPage%>">	
	<input type="hidden" name="keyField" value="<%=keyField%>">	
	<input type="hidden" name="keyWord" value="<%=keyWord%>">	
</form>
</div>
		</div>


		</section>
	</div>

</body>
</html>