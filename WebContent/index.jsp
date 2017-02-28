<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/version0116/css/style.css">
</head>

<body>
  <div id="wrap">
  
       <jsp:include page="/top.jsp" />
        <section id="row1">
        </section>
        
        <section id="row2">
            <div class="circle" id="circle1">
                <div id="circle1_icon"><a href="#" class="over"><img src="/version0116/imag/icon_1.png"><img src="/version0116/imag/icon_1_over.png" class="imgover"></a></div>
                <div id="circle1_text"><a href="#">이용방법</a></div>
            </div>
            <div class="circle" id="circle2">
                <div id="circle2_icon"><a href="#"><img src="/version0116/imag/icon_2.png"></a></div>
                <div id="circle2_text"><a href="#">법인혜택</a></div>
            </div>
            <div class="circle" id="circle3">
                <div id="circle3_icon"><a href="#"><img src="/version0116/imag/icon_3.png"></a></div>
                <div id="circle3_text"><a href="#">이벤트</a></div>
            </div>
            <div class="circle" id="circle4">
                <div id="circle4_icon"><a href="#"><img src="/version0116/imag/icon_5.png"></a></div>
                <div id="circle4_text"><a href="#">쿠폰</a></div>
            </div>
            <div class="circle" id="circle5">
                <div id="circle5_icon"><a href="#"><img src="/version0116/imag/icon_4.png"></a></div>
                <div id="circle5_text"><a href="#">지점찾기</a></div>
            </div>
        </section>
        <div class="clear"></div>
        
        <section id="row3">
            <div id="row3_01"></div>
            <div id="row3_02"></div>
            <div id="row3_03">
                <div id="row3_03_notice"><a href="#">공지사항</a></div>
                <div>
                    <a href="#" id="row3_03_img"><img src="/version0116/imag/row3_03_icon01.png"></a>
                </div>
            </div>
            <div id="row3_04">
                <div>
                    <a href="#">
                        <img src="/version0116/imag/row3_04_img02.png">
                    </a>
                </div>
            </div>
            <div class="clear"></div>
        </section>
        
        <footer id="footer">
        </footer>
    </div>

</body>

</html>