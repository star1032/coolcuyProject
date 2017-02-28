<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/mypage04.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
       
            <jsp:include page="/top.jsp"/>
	
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
        <section id="row2">
            <jsp:include page="/mypageTable.jsp"/>
            <div class="row2_con">
                <h2>결제내역</h2>
                <div id="row2_con_text">문구문구문구문구</div>
                <div id="con_tb01">결제차량</div>
                <div id="con_tb02">렌트기간</div>
                <div class="con_tb03">
                    <div class="tb03_text">문구문구문구무눅문구</div>
                </div>
                <div class="con_tb04">
                    <div class="tb04_text">2016. 08. 12 - 2016. 08. 13</div>
                </div>
                <div class="con_tb03">
                    <div class="tb03_text">문구문굼누굼눙ㅁ누ㅏㅓㅗ낭</div>
                </div>
                <div class="con_tb04">
                    <div class="tb04_text">2016. 09. 11 - 2016. 09. 13</div>
                </div>
            </div>
        </section>
    </div>

</body>
</html>