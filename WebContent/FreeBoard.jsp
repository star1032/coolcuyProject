<%@page import="com.coolcuy.dto.FreeBoardDto"%>
<%@page import="com.coolcuy.dao.FreeBoardDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/center01.css">
    <%

    FreeBoardDaoImpl dao= new FreeBoardDaoImpl();
	
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
	
	List<FreeBoardDto> vlist =null;
	
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
	//end=numPerPage;
	end=start+10;
	totalRecord = dao.getTotalCount(keyField, keyWord);
	totalPage=(int)Math.ceil((double)totalRecord/numPerPage);
	nowBlock= (int)Math.ceil((double)nowPage/pagePerBlock);
	totalBlock= (int)Math.ceil((double)totalPage/pagePerBlock);
%>
<script type="text/javascript">
 function list(){
	 document.listFrm.action = "FreeBoard.jsp";
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
	 document.readFrm.action="Freeread.jsp";
	 document.readFrm.submit();
 }
 
</script>
</head>
<body>
    <div id="wrap">
   	<jsp:include page="/top.jsp" />
        
        <section id="row1">
            <div id="row1_text01">고객센터 </div>
            <div id="row1_text02">
                <ul>
                    <li>
                        <a href="#"><img src="imag/member_img02.png"></a>
                    </li>
                    <li class="text02_up"><a href="#">Home</a></li>
                    <li class="text02_up"><a>></a></li>
                    <li class="text02_up"><a href="#">고객센터 </a></li>
                </ul>
            </div>
        </section>
        
        <section id="row2">
            <aside>
                <div class="row2_hd"><a href="#">고객센터 </a></div>
                <ul>
                    <li><a href="/version0116/list.jsp">공지사항 </a></li>
                    <li><a href="/version0116/QnA.jsp">QnA </a></li>
                    <li><a href="/version0116/FreeBoard.jsp">자유게시판 </a></li>
                </ul>
            </aside>
            <div class="row2_con">
                <h2>자유게시판 </h2>
                <div id="row2_search">
                    <input type="hidden" >
                    <a href="#" class="bt_text"><div id="search_bt">데헷 </div></a>
                </div>
                <%
					vlist =dao.getFreeBoardList(keyField, keyWord, start, end);
					listSize = vlist.size();
					if(vlist.isEmpty()){
						out.println("등록된 게시물이 없습니다.");
					}else{
					%>
                <div id="con_tb01">번호 </div>
                <div id="con_tb02">제목 </div>
                <div id="con_tb03">작성일 </div>
                <%
							for(int i =0; i<numPerPage; i++){
								if(i==listSize) break;
								FreeBoardDto dto=vlist.get(i);
								int num =dto.getNum();
								String name= dto.getName();
								String subject = dto.getSubject();
								String regdate =dto.getRegdate();
								int depth= dto.getDepth();
								int cnt= dto.getCnt();
								int pos= dto.getPos();
							%>
				<c:set var="pos" value="<%=pos%>"/>
                <div class="tb01"> <%=totalRecord-((nowPage-1)*numPerPage)-i%> </div>
                <div class="tb02"><a href="javascript:read('<%=num%>')"><%=subject%></a> </div>
                <div class="tb03"><%=regdate%> </div>
                
               
               
                <%
								if(depth>0){
									for(int j=0;j<depth;j++){
										out.println("&nbsp;&nbsp");
									}
								}
							%>
							
							<%}//for %>
                <% } //if %>
                
                
                <div class="con_num">
                    <ul>
                    <%
	int pageStart= (nowBlock -1)*pagePerBlock +1;
	int pageEnd= ((pageStart + pagePerBlock) <totalPage) ? (pageStart +pagePerBlock): totalPage+1;
	
	if(totalPage !=0){
		if(nowBlock > 1){%>
                        <li><a href="javascript:block('<%=nowBlock-1 %>')">이전</a><%}%>&nbsp;</li>
                        <%for (; pageStart < pageEnd; pageStart++){%>
                        <li><a href="javascript:pageing('<%=pageStart %>')">
                        <%if(pageStart==nowPage) {%><font color="blue"><%}%>
						<%=pageStart%>
						<%if(pageStart==nowPage) { %></font> <%}%>
                        </a>
                        </li>
                        <%}//for%>&nbsp;
                      	<%if (totalBlock >nowBlock) {%>
                        <li><a href="javascript:block('<%=nowBlock+1 %>')">다음 </a>
                        <%}%>&nbsp;
						<%}%>
                        </li>
                        
                        
                        <li><a href="Freepost.jsp">                    [글쓰기]</a></li>
                        <li><a href="javascript:list()">    [처음으로]</a></li>
                    </ul>
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
