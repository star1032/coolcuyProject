<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/mypage02.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">

		<jsp:include page="/top.jsp" />

		<section id="row1">
		<div id="row1_text01">마이페이지</div>
		<div id="row1_text02">
			<ul>
				<li><a href="#">
				<img src="/version0116/imag/member_img02.png"></a></li>
				<li class="text02_up"><a href="#">Home</a></li>
				<li class="text02_up"><a>></a></li>
				<li class="text02_up"><a href="#">마이페이지</a></li>
			</ul>
		</div>
		</section>
		<section id="row2"> <jsp:include page="/mypageTable.jsp" />

		<div class="row2_con">
			<h2>예약내역 건</h2>
			<div id="row2_con_text">문구문구문구문구</div>
			<div id="row2_con_tb01">선택</div>
			<div id="row2_con_tb02">차종</div>
			<div id="row2_con_tb03">예약시간</div>
			<div id="row2_check">
				<input type="checkbox">
			</div>
			<div class="row2_con02">
				<div id="img_box">
					<img src="../imag/car_01.png">
				</div>
				<div id="img_text">자동차이름 및 설명?</div>
			</div>
			<div id="row2_con02_time">
				<div id="time_text">2016. 04. 06</div>
			</div>

			<div id="row2_check02">
				<input type="checkbox">
			</div>
			<div class="row2_con02">
				<div id="img_box">
					<img src="../imag/car_01.png">
				</div>
				<div id="img_text">자동차 이름 및 설명?</div>
			</div>
			<div id="row2_con02_time">
				<div id="time_text">2016. 07. 18</div>
			</div>
		</div>

		<div id="row2_con_bt">
			<a href="#" class="can_bt">예약취소</a>
		</div>
		</section>
	</div>

</body>
</html>