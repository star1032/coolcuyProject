<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="/version0116/css/cardAddModal.css">
<link rel="stylesheet" href="/version0116/css/mypage01.css">

<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/version0116/js/address.js"></script>

<%@page import="com.coolcuy.dto.MemberDto" %>
<%@page import="com.coolcuy.dto.LicenseDto" %>
<%@page import="com.coolcuy.dto.CardDto" %>
<%@page import="com.coolcuy.service.CardService" %>
<%@page import="com.coolcuy.service.CardServiceImpl" %>
<%@page import="java.util.List" %>

	
 <script type="text/javascript">
 
 function checkedPassword(){
		if(!(frm.changePassword.value === frm.checkChangePassword.value)){
			return false;
		}else{
			return true;
		}
		
	}

 function getFocus() {
		document.getElementById("nameText").focus();
	}
 
 function licenseAdd(x, y) {
	    if (y.length == x.maxLength) {
	    	
	        var next = x.tabIndex;
	        next++;
	        document.getElementById("num"+next).focus();
	    }
	}
 
 function showKeyCode(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) 
										  || keyID == 8
										  || keyID == 46
										  || keyID == 37
										  || keyID == 39
										  || keyID == 9 )
		{
			return;
		}
		else
		{
			return false;
		}
	}
 
 function patternPassword(objpass){

	   if(!/^[a-zA-Z0-9]{10,15}$/.test(objpass)){
	   return true;
	   }
	   var checkNumber = objpass.search(/[0-9]/g);
	   var checkEnglish = objpass.search(/[a-z]/ig);
	   if(checkNumber <0 || checkEnglish <0){
	   return true;
	   }
	   return false;
}

 function cardCheck(){
	 //alert($('#check').is(":checked"));

  	 if(cardfrm.card1.value == "" ||cardfrm.card2.value == "" ||cardfrm.card3.value == "" ||cardfrm.card4.value == ""){
  		alert("카드번호가 비어있습니다.");
  	}else if(cardfrm.cardBirth.value == ""){
  		alert("생년월일이 비어있습니다.");
  	}else if(cardfrm.cardPassword.value == ""){
  		alert("비밀번호를 입력해주세요.");
  	}else if(!$('#check').is(":checked")){
  		alert("약관에 동의 해 주세요");
  	}else{
	 document.cardfrm.submit();
  	} 
 }
 
 </script>
 
<%
 MemberDto member=(MemberDto)request.getAttribute("member");
 String phoneNumber=member.getPhoneNumber();
 phoneNumber.trim();
 String [] phoneArray = phoneNumber.split("-");
 
 String zipCode=member.getZipCode();
 String detailAddr=member.getDetailAddr();
 String roadAddr=member.getRoadAddr();
 
 LicenseDto license=member.getLicenseDto();
 String licenseNumber = license.getLicenseNumber();
 String [] licenseArray= licenseNumber.split("-");
 String issuDate = license.getIssuDate();
 String [] issuDateArray= issuDate.split("-");
 String expiryDate = license.getExpiryDate();
 String [] expiryDateArray= expiryDate.split("-");
%>

</head>
<body>
<div id="wrap1">
<form name="cardfrm" action="cardUpdate.do" method="post">
        <div class="title1">
            <h2>결제카드 등록</h2>
        </div>
        <div class="row1_td01">
            <div id="td01_text">카드종류</div>
        </div>
        <div class="row1_td02">
            <select id="td02_sel" name="bank">
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
                <input type="text" id="card1" name="card1" onkeydown="return showKeyCode(event)" maxlength="4">
                <input type="text" id="card2" name="card2" onkeydown="return showKeyCode(event)" maxlength="4">
                <input type="text" id="card3" name="card3" onkeydown="return showKeyCode(event)" maxlength="4">
                <input type="text" id="card4" name="card4" onkeydown="return showKeyCode(event)" maxlength="4">
            </div>
        </div>
        <div class="row1_td01">
            <div id="td01_text">유효기간 </div>
        </div>
        <div class="row1_td02">
            <select id="td02_sel" name="month">
                <c:forEach var="i" begin="1" end="12">
					<option value="${i}">${i}월 </option>
	                <br>
	                </c:forEach>
            </select>
            <select id="td02_sel" name="year">
                <option value="2017">2017년 </option>
                <option value="2016">2016년 </option>
                <option value="2015">2015년 </option>
                <option value="2014">2014년 </option>
            </select>
        </div>
        <div class="row1_td01">
            <div id="td01_text">생년월일 </div>
        </div>
        <div class="row1_td02">
            <div class="td02_box02">
                <input type="text" name="cardBirth" onkeydown="return showKeyCode(event)" maxlength="6">
            </div>
            <div class="clear"></div>
            <div id="box02_text">6자리입력 </div>
        </div>
        <div class="row1_td01">
            <div id="td01_text">비밀번호 </div>
        </div>
        <div class="row1_td02">
            <div class="td02_box02">
                <input type="text" name="cardPassword" onkeydown="return showKeyCode(event)" maxlength="4">
            </div>
            <div class="clear"></div>
            <div id="box02_text">4자리입력 </div>
        </div>

        <div id="row1_line"></div>
        <div id="checkbox">
            <input type="checkbox" id="check" name="check" value="ok" >
        </div>
        <div id="checkbox_text">아래 약관 내용에 모두 동의합니다.</div>
        <div id="agg_text"><a href="#">정기과금 이용약관</a></div>
        <div id="bt_back">
            <a href="#card01" id="bt_text" onclick="cardCheck();">
                <div id="bt">결제카드 등록완료 </div>
            </a>
        </div>
        <input type="hidden" name="cardEmail" value="${member.getEmail()}">
         <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
        </form>
    </div>
    
    
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
	    <form name="frm" action="update.do" method="post">
	        <div class="row2_con">
	            <h2>기본정보</h2>
	            <div class="row2_con_01">이름</div>
	            <div class="row2_con_back">
	                <div class="back_text">${member.getName()}</div>
	            </div>
	            <div class="row2_con_01">E-mail</div>
	            <div class="row2_con_back">
	                <div class="back_text">${member.getEmail()}</div>
	            </div>
	            <div class="row2_con_01">변경 비밀번호</div>
	            <div class="row2_con_back">
	                <div class="row2_con_in01">
	                    <input type="password" name="changePassword" id="firpass"  maxlength="14">
	                </div>
	            </div>
	            <div class="row2_con_01">변경 비밀번호확인</div>
	            <div class="row2_con_back">
	                <div class="row2_con_in01">
	                    <input type="password" name="checkChangePassword" id="secpass" maxlength="14" >
	                </div>
	            </div>
	            <div class="row2_con_01">휴대폰번호</div>
	            <div class="row2_con_back">
	                <div id="row2_con_phon">
	                    <input type="text" name="zeroPhone"  onkeydown="return showKeyCode(event)" maxlength="3" value="<%=phoneArray[0]%>">
	                    <div class="phon_tx1">-</div>
	                    <input type="text" name="firPhone" onkeydown="return showKeyCode(event)" maxlength="4" value="<%=phoneArray[1]%>">
	                    <div class="phon_tx1">-</div>
	                    <input type="text" name="secPhone" onkeydown="return showKeyCode(event)"  maxlength="4" value="<%=phoneArray[2]%>">
	                </div>
	            </div>
	            <div id="row2_con_02">주소</div>
	            <div id="row2_con_addback">
	                <div id="row2_con_add">
	                    <input type="text" id="postcode" name="zipAddr" value="<%=zipCode%>">
	                    <div class="add_tex1"> </div>
	                    <input type="text" id="roadAddress" name="roadAddr" value="<%=roadAddr%>">
	                   <a href="#" onclick="execDaumPostcode()"><div id="add_bt">주소찾기</div></a>
	                </div>
	                <div class="clear"></div>
	                <div id="row2_con_add2">
	                    <input type="text" id="detailAdress" name="detailAddr" value="<%=detailAddr%>">
	                </div>
	            </div>
	            <div class="clear"></div>
	            <div class="row2_con_01">주이용지역</div>
	            <div class="row2_con_back">
	                    <input type="checkbox" name="primaryArea" value="서울" <%if(member.getPrimaryArea().equals("서울")){%>checked<%} %>><span class="con_area">서울</sapn>
	                    <input type="checkbox" name="primaryArea" value="강원/경기"<%if(member.getPrimaryArea().equals("강원/경기")){%>checked<%} %>><span class="con_area" id="area01">인천/경기</span>
	                    <input type="checkbox" name="primaryArea" value="대구/경북"<%if(member.getPrimaryArea().equals("대구/경북")){%>checked<%} %>><span class="con_area">대구/경북</span>
	                    <input type="checkbox" name="primaryArea" value="부산/경남"<%if(member.getPrimaryArea().equals("부산/경남")){%>checked<%} %>><span class="con_area">부산/경남</span>
	                    <input type="checkbox" name="primaryArea" value="광주/전라"<%if(member.getPrimaryArea().equals("광주/전라")){%>checked<%} %>><span class="con_area">광주/전라</span>
	                    <input type="checkbox" name="primaryArea" value="제주"<%if(member.getPrimaryArea().equals("제주")){%>checked<%} %>><span class="con_area">제주</span>
	            </div>
	        </div>
	        <div class="clear"></div>
	        <div class="row2_con" id="con02">
	            <h2>운전면허 정보</h2>
	            <div class="row2_con_01">면허종류</div>
	            <div class="row2_con_back">
	                <input type="checkbox" name="licenseType" value="1종보통" <%if(license.getLicenseType().equals("1종보통")){%>checked<%} %>><span class="con_area">1종보통</span>
	                <input type="checkbox" name="licenseType" value="2종보통" <%if(license.getLicenseType().equals("2종보통")){%>checked<%} %>><span class="con_area">2종보통</span>
	                <input type="checkbox" name="licenseType" value="1종대형" <%if(license.getLicenseType().equals("1종대형")){%>checked<%} %>><span class="con_area">1종대형</span>
	            </div>
	            <div class="row2_con_01">면허번호</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num">
	                    <input type="text" size="2" tabindex="1" maxlength="2"
                    		   name="licenseNumber1" onkeydown="return showKeyCode(event)" value="<%=licenseArray[0]%>">
	                    <input type="text" size="2" tabindex="2" maxlength="2"
                    		   name="licenseNumber2" onkeydown="return showKeyCode(event)" value="<%=licenseArray[1]%>">
	                    <input type="text"   size="6" tabindex="3" maxlength="6"
                    		   name="licenseNumber3" onkeydown="return showKeyCode(event)" value="<%=licenseArray[2]%>">
	                    <input type="text" size="2" tabindex="4" maxlength="2"
                    		   name="licenseNumber4" onkeydown="return showKeyCode(event)" value="<%=licenseArray[3]%>">
	                </div>
	            </div>
	            <div class="row2_con_01">적성검사만료</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num02">
	                    <input type="text" name="expiryDate1" value="<%=expiryDateArray[0]%>">
	                    <input type="text" name="expiryDate2" value="<%=expiryDateArray[1]%>">
	                    <input type="text" name="expiryDate3" value="<%=expiryDateArray[2]%>">
	                </div>
	            </div>
	            <div class="row2_con_01">면허발급일</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num03">
	                    <input type="text" name="issuDate1" value="<%=issuDateArray[0]%>">
	                    <input type="text" name="issuDate2" value="<%=issuDateArray[1]%>">
	                    <input type="text" name="issuDate3" value="<%=issuDateArray[2]%>">
	                </div>
	            </div>
	        </div>
	        <div class="row2_con" id="con03">
	            <h2>결제카드</h2>
	            <div id="row2_con04">
	                <a href="#"><div class="con04_card" id="card01">
	                
	                <c:if test="${cards.size() > 0 }">
	                <c:forEach var="i" begin="0" end="${cards.size()-1}">
	                ${cards.get(i).getCardType()} 
	                ${cards.get(i).getCardNumber()}<br>
	                </c:forEach>
	                </c:if>
	                
	                </div></a>
	            </div>
 <div id="dialog-form" title="내정보 변경">
 
  <form>
    <fieldset>
      <label for="password">현재 패스워드를 입력하세요</label>
      <input type="password" name="truePassword" id="truePassword" class="text ui-widget-content ui-corner-all" maxlength="14">
      <input type="hidden" name="dialogPassword" id="dialogPassword" value="${member.getPassword()}" class="text ui-widget-content ui-corner-all" maxlength="14">
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>
	            <a href="#truePassword" id="create-user"><div id="ok_bt">확인</div></a>
	            <a href="#card01" id="addCard"><div id="card_bt">결제카드 추가</div></a>
	            <a href="#"><div id="ex_bt">탈퇴하기</div></a>
	        </div>
	        </form>
	    </section>
	</div>
	
</body>
<script type="text/javascript">
$( function() {
    var dialog, form,
      truePassword = $( "#truePassword" ),
      dialogPassword = $( "#dialogPassword" );
 
    function alerted(){
    	if(dialogPassword.val() != truePassword.val()){
    	alert("같지않다");
    	}else{
    	alert("같다");
    	document.frm.submit();
    	}
    }
    function checkAll(){
    	
     	if(frm.zeroPhone.value == ""){
     		alert("오해봉 휴대폰 앞에 비었다ㅋㅋㅋㅋㅋ.");
     	}else if(frm.firPhone.value == ""){
     		alert("휴대폰 중간에 비었다.");
     	}else if(frm.secPhone.value == ""){
     		alert("휴대폰 뒤에 비었다.");
     	}else if(frm.zipAddr.value == ""){
     		alert("우편번호 비었다.");
     	}else if(frm.roadAddr.value == ""){
     		alert("도로명주소 비었다.");
     	}else if(frm.licenseNumber1.value=="" ||frm.licenseNumber2.value=="" || frm.licenseNumber4.value=="" || frm.licenseNumber3.value==""){
     		alert("면허번호를 모두 입력하세요");
     	}else if(frm.expiryDate1.value=="" || frm.expiryDate2.value=="" || frm.expiryDate3.value==""){
     		alert("면허유효 날짜를 모두 입력하세요");
     	}else if(frm.issuDate1.value=="" || frm.issuDate2.value=="" || frm.issuDate3.value==""){
     		alert("면허 발급 날짜를 모두 입력하세요");
     	}else if(!frm.changePassword.value == "" && patternPassword(frm.changePassword.value)){
     		alert("숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.");
     	}else if(!checkedPassword()){
     		alert("비빌번호가 일치하지 않습니다.")
     	}else{
     		  dialog.dialog( "open" );
     	}
     	
     }
    
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 200,
      width: 350,
      modal: true,
      buttons: {
        "확인": alerted,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
    });
 
    $( "#create-user" ).on( "click", function() {
    	checkAll();
    
    });
  } );
 </script> 
 <script type="text/javascript">
$( function() {
    var dialog, form,
 
    
    dialog = $( "#wrap1" ).dialog({
      autoOpen: false,
      height: 600,
      width: 500,
      modal: true,
      
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
    });
 
    $( "#addCard" ).on( "click", function() {
    	 dialog.dialog( "open" );
    });
  } );
 </script> 
</html>