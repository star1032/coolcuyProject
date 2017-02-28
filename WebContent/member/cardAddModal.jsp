<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/version0116/css/cardAddModal.css">
	<title>Insert title here</title>
</head>

<body>
    <div id="wrap">
        <div class="title">
            <h2>결제카드 등록</h2>
        </div>
        <div class="row1_td01">
            <div id="td01_text">카드종류</div>
        </div>
        <div class="row1_td02">
            <select id="td02_sel">
                <option>대구은행 </option>
                <option>국민은행 </option>
                <option>기업은행 </option>
                <option>농협은행 </option>
                <option>신협은행 </option>
                <option>하나은행 </option>
            </select>
        </div>
        <div class="row1_td01">
            <div id="td01_text">카드번호 </div>
        </div>
        <div class="row1_td02">
            <div class="td02_box01">
                <input type="text">
                <input type="text">
                <input type="text">
                <input type="text">
            </div>
        </div>
        <div class="row1_td01">
            <div id="td01_text">유효기간 </div>
        </div>
        <div class="row1_td02">
            <select id="td02_sel">
                <option>01월 </option>
                <option>02월 </option>
                <option>03월 </option>
                <option>04월 </option>
            </select>
            <select id="td02_sel">
                <option>2017년 </option>
                <option>2016년 </option>
                <option>2015년 </option>
                <option>2014년 </option>
            </select>
        </div>
        <div class="row1_td01">
            <div id="td01_text">생년월일 </div>
        </div>
        <div class="row1_td02">
            <div class="td02_box02">
                <input type="text">
            </div>
            <div class="clear"></div>
            <div id="box02_text">6자리입력 </div>
        </div>
        <div class="row1_td01">
            <div id="td01_text">비밀번호 </div>
        </div>
        <div class="row1_td02">
            <div class="td02_box02">
                <input type="text">
            </div>
            <div class="clear"></div>
            <div id="box02_text">4자리입력 </div>
        </div>

        <div id="row1_line"></div>
        <div id="checkbox">
            <input type="checkbox">
        </div>
        <div id="checkbox_text">아래 약관 내용에 모두 동의합니다.</div>
        <div id="agg_text"><a href="#">정기과금 이용약관</a></div>
        <div id="bt_back">
            <a href="#" id="bt_text">
                <div id="bt">결제카드 등록완료 </div>
            </a>
        </div>
    </div>

</body>

</html>