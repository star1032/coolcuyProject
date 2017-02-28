<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/version0116/css/mypage04.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header id="header">
            <div id="top_line">
            </div>
            <div id="top_back">
                <div id="small_nav">
                    <c:if test="${!empty authUser}">
					${authUser.name}님 login
					<a href="/version0116/logout.do">[로그아웃하기]</a>
					<a href="/version0116/myPage.do">[마이페이지]</a>
					</c:if>
					
				  <c:if test="${empty authUser}">	
                    <a href="/version0116/loginPage.do">로그인</a><span>|</span>
                    <a href="/version0116/join.do">회원가입</a><span>|</span>
                   </c:if>
                </div>
            </div>

            <nav>
                <ul>
                    <li id="logo"><a href="#">SAMPLE LOGO</a></li>
                    <li class="nav_text"><a href="#">서비스안내</a></li>
                    <li class="nav_text"><a href="#">요금안내</a></li>
                    <li class="nav_text"><a href="#">고객센터</a></li>
                    <li class="nav_text"><a href="#">이벤트/쿠폰</a></li>
                    <li id="nav_text_color"><a href="#">실시간예약</a></li>
                </ul>
            </nav>
            <div class="clear"></div>
            <div id="nav_line"></div>
        </header>
        
       <section id="row1">
            <div id="row1_text01">마이페이지</div>
            <div id="row1_text02">
                <ul>
                    <li>
                        <a href="#"><img src="/version0116/imag/member_img02.png"></a>
                    </li>
                    <li class="text02_up"><a href="#">Home</a></li>
                    <li class="text02_up"><a>></a></li>
                    <li class="text02_up"><a href="#">마이페이지</a></li>
                </ul>
            </div>
        </section>
       

</body>
</html>