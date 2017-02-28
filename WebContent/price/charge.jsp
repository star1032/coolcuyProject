
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/version0116/css/price/charge.css">
<link rel="stylesheet" href="/version0116/css/footer.css">

<%@page import="java.util.List"%>
<%@page import="com.coolcuy.service.PriceListService"%>
<%@page import="com.coolcuy.service.PriceListServiceImpl"%>

</head>
<body>

	<div id="wrap">
		 <jsp:include page="../top.jsp" />

		<section id="row1">
			<div id="row1_text01">요금안내</div>
			<div id="row1_text02">
				<ul>
					<li><a href="#"><img src="imag/member_img02.png"></a></li>
					<li class="text02_up"><a href="#">Home</a></li>
					<li class="text02_up"><a>></a></li>
					<li class="text02_up"><a href="#">요금안내 </a></li>
				</ul>
			</div>
		</section>

		<section id="row2">
			<div class="row2_text">연회비 완전무료, 시간대 별 대여요금</div>
			<div class="row2_td01">구분</div>
			<div class="row2_td02">상세내용</div>
			<div class="row2_td01" id="td01_01">연회비</div>
			<div class="row2_td02" id="td02_01">무료</div>
			<div class="row2_td01" id="td01_02">가입조건</div>
			<div class="row2_td02" id="td02_02">만 21세이상, 운전면허 취득 후 1년 이상 경과
			</div>
			<div class="row2_td01" id="td01_01">필수조건</div>
			<div class="row2_td02" id="td02_01">본인 명의 운전면허증 및 신용/체크카드 등록</div>
			<div class="row2_td01" id="td01_02">대여요금</div>
			<div class="row2_td02" id="td02_02">차종별 차등 할인</div>
			<div class="row2_td01" id="td01_01">가입혜택</div>
			<div class="row2_td02" id="td02_01">신규 가입 시 할인 쿠폰 지급</div>
			<div class="row2_td01" id="td01_02">이용혜택</div>
			<div class="row2_td02" id="td02_02">이용 횟수에 따라 다양한 혜택 지급</div>
			<div class="row2_text02">* 가입 및 이용 혜택은 당사 정책에 따라 변경될 수 있습니다</div>
		</section>

		<section id="row3">
			<div class="row3_text01">다양한 쿠폰혜택과 모바일에서만 만나볼 수 있는 득가상품까지 부담없이
				누리세요</div>

			<table>
				<thead>
					<tr>
						<th id="thead_th01" rowspan="2">차종</th>
						<th id="thead_th02" rowspan="2">모델</th>
						<th id="thead_th03" rowspan="2">기준대여요금</th>
						<th id="thead_th04" colspan="2">특별할인</th>
						<th id="thead_th05" rowspan="2">성수기 요금</th>
					</tr>
					<tr>
						<th id="th03_01">주중요금</th>
						<th id="th03_02">주말요금</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='경형A'}">
								<c:if test="${prices.get(i).getCarName()=='모닝'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='넥스트스파크'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='경형B'}">
								<td class="tbody_td01">${prices.get(i).getCarType()}</td>
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='소형'}">
								<c:if test="${prices.get(i).getCarName()=='엑센트'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='프라이드'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='준중형'}">
								<c:if test="${prices.get(i).getCarName()=='K3'}">
									<td class="tbody_td01" rowspan="3">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='아반떼'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='아반떼AD'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='중형'}">
								<c:if test="${prices.get(i).getCarName()=='K5'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='K5(LPG)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='대형'}">
								<c:if test="${prices.get(i).getCarName()=='그랜저HG'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='그랜저HG(LPG)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='SUV'}">
								<c:if test="${prices.get(i).getCarName()=='올란도(경유)'}">
									<td class="tbody_td01" rowspan="7">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='티볼리(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='QM3(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='QM5(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='투산(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='산타페(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='소렌토(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='승합차'}">
								<c:if test="${prices.get(i).getCarName()=='카니발(경유)'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='스타렉스(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarType()=='수입차'}">
								<c:if test="${prices.get(i).getCarName()=='BMW520D(경유)'}">
									<td class="tbody_td01" rowspan="2">${prices.get(i).getCarType()}</td>
									<td class="tbody_td02">${prices.get(i).getCarName()}</td>
									<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
									<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
									<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
									<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
								</c:if>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<c:forEach var="i" begin="0" end="${prices.size()-1}">
							<c:if test="${prices.get(i).getCarName()=='BMW X3(경유)'}">
								<td class="tbody_td02">${prices.get(i).getCarName()}</td>
								<td class="tbody_td03">${prices.get(i).getStandardCharge()}</td>
								<td class="tbody_td04">${prices.get(i).getWeekDayCharge()}</td>
								<td class="tbody_td05">${prices.get(i).getWeekEndCharge()}</td>
								<td class="tbody_td06">${prices.get(i).getPeakSeasonCharge()}</td>
							</c:if>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</section>
		
		<footer id="footer"> </footer>
	</div>



</body>
</html>