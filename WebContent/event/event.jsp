<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Event & Coupon</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/version0116/css/event/event.css">
<link rel="stylesheet" href="/version0116/css/event/global.css">

<script src="/version0116/js/jquery-1.4.4.min.js"></script>
<script src="/version0116/js/slides.jquery.js"></script>

<%@ page import="java.util.List" %>
<%@ page import="com.coolcuy.dto.EventDto" %>
<%@ page import="com.coolcuy.service.EventService" %>
<%@ page import="com.coolcuy.service.EventServiceImpl" %>

<%@ page import="com.coolcuy.dto.CouponDto" %>
<%@ page import="com.coolcuy.service.CouponService" %>
<%@ page import="com.coolcuy.service.CouponServiceImpl" %>

<script>
        $(function () {
            $('#slides').slides({
                preload: true,
                preloadImage: "/version0116/images/loading.gif",
                play: 3000,
                pause: 2500,
                hoverPause: true
            });
        });
</script>
</head>

<body>
	<div id="wrap">
		 <jsp:include page="../top.jsp" />

		<section id="row1">
			<div id="row1_text01">이벤트/쿠폰</div>
			<div id="row1_text02">
				<ul>
					<li><a href="#"><img src="/version0116/imag/member_img02.png"></a></li>
					<li class="text02_up"><a href="#">Home</a></li>
					<li class="text02_up"><a>></a></li>
					<li class="text02_up"><a href="#">이벤트/쿠폰 </a></li>
				</ul>
			</div>
		</section>

		<section id="row2">
			<h2>이벤트</h2>
			<div class="row2_eventback">
				<div id="container">
					<div id="example">
						<img src="/version0116/images/new-ribbon.png" width="112" height="112"
							alt="New Ribbon" id="ribbon">
						<div id="slides">
							<div class="slides_container">
								<div class="slides_event" id="event1">
								<ul>
								<c:forEach var="i" begin="0" end="${events.size()-1}">
								<c:if test="${events.get(i).getEventNumber()=='#EV00013'}">
									<li class="availableSpot">${events.get(i).getAvailableSpot()}</li>
									<li class="eventName">오픈10주년기념할인</li>
									<li class="saleName">40%SALE EVENT</li>
									<li class="eventDay">이벤트기간 :</li>
									<li class="eventStart">17.02.18</li>
									<li class="and">~</li>
									<li class="eventEnd">17.02.28</li>
								</c:if>	
								</c:forEach>
								</ul>	
								</div>

								<div class="slides_event" id="event2">
								<ul>
									<li class="availableSpot">부산 지점</li>
									<li class="eventName">오픈5주년기념할인</li>
									<li class="saleName">30%SALE EVENT</li>
									<li class="eventDay">이벤트기간 :</li>
									<li class="eventStart">17.02.18</li>
									<li class="and">~</li>
									<li class="eventEnd">17.02.28</li>
								</ul>	
								</div>

								<div class="slides_event" id="event3">
								<ul>
									<li class="availableSpot">대전 지점</li>
									<li class="eventName">오픈3주년기념할인</li>
									<li class="saleName">20%SALE EVENT</li>
									<li class="eventDay">이벤트기간 :</li>
									<li class="eventStart">17.02.18</li>
									<li class="and">~</li>
									<li class="eventEnd">17.02.28</li>
								</ul>	
								</div>

								<div class="slides_event" id="event4">
								<ul>
									<li class="availableSpot">서울 지점</li>
									<li class="eventName">오픈1주년기념할인</li>
									<li class="saleName">10%SALE EVENT</li>
									<li class="eventDay">이벤트기간 :</li>
									<li class="eventStart">17.02.18</li>
									<li class="and">~</li>
									<li class="eventEnd">17.02.28</li>
								</ul>	
								</div>
							</div>
							<a href="#" class="prev"><img src="/version0116/images/arrow-prev.png"
								width="24" height="43" alt="Arrow Prev"></a> <a href="#"
								class="next"><img src="/version0116/images/arrow-next.png" width="24"
								height="43" alt="Arrow Next"></a>
						</div>
						<img src="/version0116/images/example-frame.png" width="739" height="341"
							alt="Example Frame" id="frame">
					</div>
				</div>
			</div>
		</section>

		<section class="row3">
			<h2>쿠폰</h2>
			<div class="coupon_back">
				<div class="coupon_01">
					<img src="/version0116/imag/coupon01.png">
					<div class="coupon_textback01">
						<div class="coupon01_text01">쿠폰명</div>
						<div class="coupon01_text02">
							<전국지점> 5% 할인! </전국지점>
						</div>
						<div class="coupon01_text01">신청기간</div>
						<div class="coupon01_text02">2017.01.20 ~ 2017.02.20</div>
						<div class="coupon01_text01">사용기간</div>
						<div class="coupon01_text02">2017.02.15 ~ 2017.03.15</div>
						<div class="coupon01_text01">사용조건</div>
						<div class="coupon01_text02">6시간이상/차종제한</div>
						<div class="coupon01_02">
							<div>
								쿠폰구매 <a href="#"><img src="/version0116/imag/coupon_icon.png"
									id="cp_icon"></a>
							</div>
						</div>
					</div>
				</div>

				<div class="coupon_02">
					<img src="/version0116/imag/coupon02.png">
					<div class="coupon_textback02">
						<div class="coupon01_text01">쿠폰명</div>
						<div class="coupon01_text02">
							<전국지점> 5% 할인! </전국지점>
						</div>
						<div class="coupon01_text01">신청기간</div>
						<div class="coupon01_text02">2017.01.20 ~ 2017.02.20</div>
						<div class="coupon01_text01">사용기간</div>
						<div class="coupon01_text02">2017.02.15 ~ 2017.03.15</div>
						<div class="coupon01_text01">사용조건</div>
						<div class="coupon01_text02">6시간이상/차종제한</div>
						<div class="coupon02_02">
							<div>
								쿠폰구매 <a href="#"><img src="/version0116/imag/coupon_icon.png"
									id="cp_icon"></a>
							</div>
						</div>
					</div>
				</div>

				<div class="coupon_01">
					<img src="/version0116/imag/coupon03.png">
					<div class="coupon_textback01">
						<div class="coupon01_text01">쿠폰명</div>
						<div class="coupon01_text02">
							<전국지점> 5% 할인! </전국지점>
						</div>
						<div class="coupon01_text01">신청기간</div>
						<div class="coupon01_text02">2017.01.20 ~ 2017.02.20</div>
						<div class="coupon01_text01">사용기간</div>
						<div class="coupon01_text02">2017.02.15 ~ 2017.03.15</div>
						<div class="coupon01_text01">사용조건</div>
						<div class="coupon01_text02">6시간이상/차종제한</div>
						<div class="coupon01_02">
							<div>
								쿠폰구매 <a href="#"><img src="/version0116/imag/coupon_icon.png"
									id="cp_icon"></a>
							</div>
						</div>
					</div>
				</div>

				<div class="coupon_02">
					<img src="/version0116/imag/coupon04.png">
					<div class="coupon_textback02">
						<div class="coupon01_text01">쿠폰명</div>
						<div class="coupon01_text02">
							<전국지점> 5% 할인! </전국지점>
						</div>
						<div class="coupon01_text01">신청기간</div>
						<div class="coupon01_text02">2017.01.20 ~ 2017.02.20</div>
						<div class="coupon01_text01">사용기간</div>
						<div class="coupon01_text02">2017.02.15 ~ 2017.03.15</div>
						<div class="coupon01_text01">사용조건</div>
						<div class="coupon01_text02">6시간이상/차종제한</div>
						<div class="coupon02_02">
							<div>
								쿠폰구매 <a href="#"><img src="/version0116/imag/coupon_icon.png"
									id="cp_icon"></a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</section>
	</div>


</body>
</html>