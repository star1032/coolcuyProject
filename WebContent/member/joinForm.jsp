<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/version0116/css/member.css">
	<title>Insert title here</title>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="/version0116/js/address.js"></script>

<script type="text/javascript">
function checkRadioValue(objCar, objMember, id1, id2, id3, id4, id5, id6) {
                
    var warning = "정보 제공을 동의해주세요"; 
    
    var rcobj = document.getElementsByName(objCar);
    var rmobj = document.getElementsByName(objMember);
    
    if((rcobj[0].checked == true) && (rmobj[0].checked == true)){
    	//alert("다음 단계로 이동");
		//다음 display block 시키기.
		changeDisplay(id1,id2,id3,id4,id5,id6)
    } else if((rcobj[1].checked == true) || (rmobj[1].checked == true)){
        alert(warning);
    } else{
    	alert("약관에 동의 해 주세요");
    }
}
function checkedPassword(objpass,objpassOk){
	var warning = "비밀번호가 서로 다름니다.";
	
	var firPass= document.getElementById(objpass);
	var secPass= document.getElementById(objpassOk);
	if(!(firPass.value === secPass.value)){
		return false;
	}else{
		return true;
	}
	
}

function checkAll(objpass,objpassOk,id1,id2,id3,id4,id5,id6){
	if(frm.name.value == ""){
		alert("이름이 비었따");
	}else if(frm.email.value == ""){
		alert("이메일 비었다.");
	}else if(frm.emailValue.value == ""){
		alert("이메일 뒤에꺼 비었다.");
	}else if(frm.password.value == ""){
		alert("비밀번호 비었다.");
	}else if(frm.checkPassword.value == ""){
		alert("비밀번호 확인 비었다.");
	}else if(frm.firPhone.value == ""){
		alert("휴대폰 앞에 비었다.");
	}else if(frm.secPhone.value == ""){
		alert("휴대폰 뒤에 비었다.");
	}else if(frm.zipAddr.value == ""){
		alert("우편번호 비었다.");
	}else if(frm.roadAddr.value == ""){
		alert("도로명주소 비었다.");
	}else if(patternPassword(frm.password.value)){
		alert("숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.");
	}else if(!checkedPassword(objpass,objpassOk)){
		alert("비빌번호가 일치하지 않습니다.")
	}else{
		changeDisplay(id1,id2,id3,id4,id5,id6);	
	}
}

function selectEmail(frm){
	frm.emailValue.value= frm.selectBox.options[frm.selectBox.selectedIndex].text;
}

function changeDisplay(id1,id2,id3,id4,id5,id6){
	var el1= document.getElementById(id1);
	var el2= document.getElementById(id2);
	
	changeBackgroundColor(id3,id4);
	changeFontColor(id5,id6)
	el1.style.display= "none";
	el2.style.display= "block";
	
}

function changeBackgroundColor(id1,id2){
	var el1= document.getElementById(id1);
	var el2= document.getElementById(id2);
 	el1.style.backgroundColor = "#eee";
 	el2.style.backgroundColor = "#02a5af";
}

function changeFontColor(id1,id2){
	var el1= document.getElementById(id1);
	var el2= document.getElementById(id2);
	el1.style.color="#666";
 	el2.style.color="#fff";
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
	      alert("숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.");
	   return true;
	   }
	   var checkNumber = objpass.search(/[0-9]/g);
	   var checkEnglish = objpass.search(/[a-z]/ig);
	   if(checkNumber <0 || checkEnglish <0){
	   alert("숫자와 영문자를 혼용하여야 합니다.");
	   return true;

	   }
	   return false;
}

function checkStep2(id1,id2,id3,id4,id5,id6){
	if(frm.licenseType.value == ""){
		alert("면허 타입을 선택해주세요");
	} else if(frm.licenseNumber1.value == "" ||
			  frm.licenseNumber2.value == "" ||
			  frm.licenseNumber3.value == "" ||
			  frm.licenseNumber4.value == ""){
		alert("운전면허 번호를 입력해주세요");
	} else if(frm.expiryDate1.value == "" ||
			  frm.expiryDate2.value == "" ||
			  frm.expiryDate3.value == ""){
		alert("갱신 만료일을 입력해주세요");
	} else if(frm.issuDate1.value == "" ||
			  frm.issuDate2.value == "" ||
			  frm.issuDate3.value == ""){
		alert("면허 발급일을 입력해주세요");
	} else if(frm.licenseBirth.value == ""){
		alert("면허  생년월일을 입력해주세요");
	} else if(frm.gender.value == ""){
		alert("성별을 선택해주세요");
	} else if(frm.cardType.value == ""){
		alert("카드종류를 선택해주세요");
	} else if(frm.cardNumber1.value == "" ||
			  frm.cardNumber2.value == "" ||
			  frm.cardNumber3.value == "" ||
			  frm.cardNumber4.value == ""){
		alert("카드번호를 입력해주세요");
	} else if(frm.cardExpiryDate1.value == "" ||
			  frm.cardExpiryDate2.value == ""){
		alert("유효기간을 입력해주세요");
	} else if(frm.birth.value == ""){
		alert("생년월일을 입력해주세요");
	}
	else if(frm.cardPassword.value == ""){
		alert("카드비밀번호를 입력해주세요");
	}
	else{
		document.frm.submit();
	}
	
}

function getFocus() {
	document.getElementById("nameText").focus();
}

function cardAdd(x, y) {
	if(y.length == x.maxLength){
		var next = x.tabIndex;
		next++;
		document.getElementById("CardNum"+next).focus();
	}
}


</script>

</head>
<body>
    <div id="wrap">
    
         <header id="header">
            <div id="top_line">
            </div>
            <div id="top_back">
                <div id="small_nav">
                    <a href="loginPage.do">로그인</a><span>|</span>
                </div>
            </div>

            <nav>
                <ul>
                    <li id="logo"><a href="/version0116/index.jsp">SAMPLE LOGO</a></li>
                    <li class="nav_text"><a href="#">서비스안내</a></li>
                    <li class="nav_text"><a href="#">요금안내</a></li>
                    <li class="nav_text"><a href="/version0116/list.jsp">고객센터</a></li>
                    <li class="nav_text"><a href="#">이벤트/쿠폰</a></li>
                    <li id="nav_text_color"><a href="#">실시간예약</a></li>
                </ul>
            </nav>
            <div class="clear"></div>
            <div id="nav_line"></div>
        </header>

        <section id="row1">
            <div id="row1_text01">회원가입</div>
            <div id="row1_text02">
                <ul>
                    <li>
                        <a href="#"><img src="/version0116/imag/member_img02.png"></a>
                    </li>
                    <li class="text02_up"><a href="#">Home</a></li>
                    <li class="text02_up"><a>></a></li>
                    <li class="text02_up"><a href="#">회원가입</a></li>
                </ul>
            </div>
        </section>
        <!--------------------------------------------------------------------->
        <section id="row2">
            <ul>
                <li id="row2_01"><a id="row2_011" href="#">약관 확인</a></li>
                <li id="row2_11" class="row2_02"><a id="row2_012" href="#">필수정보입력</a></li>
                <li id="row2_21" class="row2_02"><a id="row2_013" href="#">운전면허/결제정보 등록</a></li>
                <li id="row2_31" class="row2_02"><a id="row2_014" href="#">회원가입 완료</a></li>
            </ul>
			
            <!-------------------------------------------------------------------------->
			<div id="top_row2">
            <div class="row3">
                <div class="row3_title01">차량 대여를 위한 개인정보 수집/이용동의</div>
            </div>
            <div id="row3_01">
                <div class="row3_02">
                    <h4>제1조 (목적)</h4>
                    <p>이 약관은 </p>
                </div>
                <div class="row3_check">
                    <input type="radio" name="carAgree" checked="checked" />
                    <div class="row3_02_agg">동의합니다</div>
                    <input type="radio" name="carAgree"/>
                    <div class="row3_02_agg">동의하지않습니다</div>
                </div>
            </div>
            <div id="row3_03">
                <div class="row3_title01">개인정보 취급위탁에 대한 동의</div>
                <div class="row3_02">
                    <h4>제1조 (목적)</h4>
                    <p>이 약관은</p>
                </div>
                <div class="row3_check">
                    <input type="radio" name="memberAgree" checked="checked"/>
                    <div class="row3_02_agg">동의합니다</div>
                    <input type="radio" name="memberAgree"/>
                    <div class="row3_02_agg">동의하지않습니다</div>
                </div>
            </div>
            <div id="row3_bt">
            
                <a href="#" class="bt" onclick="checkRadioValue('carAgree', 'memberAgree','top_row2','row4','row2_01','row2_11','row2_011','row2_012'), getFocus();">다음단계</a>

            </div>   
            </div>         
        </section>
        <!---------------------------약관확인------------------------------->
        
<form name="frm" action="joinForm.do" method="post">
        <section id="row4">
            <div id="row4_01">
                <h2>회원 정보<span>필수 항목이므로 반드시 입력 해 주십시오</span></h2>
            </div>
            <div id="row4_02">
                <label class="row4_02_td">이름</label>
                <div class="row4_02_text"><input type="text" id="nameText" name="name" maxlength="4"></div>
                <div class="clear"></div>
                
                <label id="row4_02_email">E-Mail</label>
                <div id="row4_02_email_01">
                    <input type="text" name="email"  maxlength="14"><span id="row4_02_span">@</span>
                    <input type="text" name="emailValue"  maxlength="10">
                    <div id="row4_02_email_box">
                    <select class="email_sel" name="selectBox" onChange="selectEmail(frm);">
                        <option value="">직접입력</option>
                        <option value="naver.com">naver.com</option>
                        <option value="daum.net">daum.net</option>
                        <option value="gmail.com">gmail.com</option>
                    </select>
                    </div>
                    <a href="#"><div id="email_bt">중복확인</div></a>
                    <div id="email_text">등록한 이메일 주소를 아이디로 적용합니다.<br>중요 서비스 공지 및 결제내역 안내 메일 발송</div>
                </div>
                <label class="row4_02_td">비밀번호</label>
                <div class="row4_02_text"><input type="password" id="firpass" name="password"  maxlength="14"></div>
                <div id="row4_02_pas">숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.</div>
                <div class="clear"></div>
                <label class="row4_02_td">비밀번호확인</label>
                <div class="row4_02_text"><input type="password" id="secpass" name="checkPassword"  maxlength="14">
                
                </div>
                
                <label class="row4_02_td">휴대폰번호</label>
                <div id="row4_02_number">
                    <select id="number_sel" name="zeroPhone">
                        <option>010</option>
                        <option>011</option>
                        <option>012</option>
                        <option>018</option>
                        <option>019</option>
                    </select><span class="number_space">-</span>
                    <input type="text" name="firPhone" onkeydown="return showKeyCode(event)" maxlength="4">
                    <span class="number_space">-</span>
                    <input type="text" name="secPhone" onkeydown="return showKeyCode(event)"  maxlength="4">
                    <a href="#"><div id="number_bt">인증요청</div></a>
                </div>
                
                <label class="row4_02_td">인증번호</label>
                <div class="row4_02_text"><input type="text"></div>
                
                <label id="row4_02_add">주소</label>
                <div id="row4_02_add_in"><input type="text" id="postcode" name="zipAddr" ></div>
                <a href="#" onclick="execDaumPostcode()"><div id="add_bt">주소찾기</div></a>
                <div id="row4_02_add_long">
                	<input type="text" id="roadAddress" name="roadAddr" >
                	<input type="text" id="detailAdress" name="detailAddr" placeholder="상세주소를 입력하세요">
                </div>
                <div id="add_text">반드시 우편물 수취가 가능한 곳이여야 합니다.</div>
                <div class="clear"></div>
                
                <label class="row4_02_td">주 사용 지역</label>
                <div id="row4_02_check">
                    <input type="checkbox" name="primaryArea" value="서울"><span>서울</span>
                    <input type="checkbox" name="primaryArea" value="강원/경기"><span>강원/경기</span>
                    <input type="checkbox" name="primaryArea" value="대구/경북"><span>대구/경북</span>
                    <input type="checkbox" name="primaryArea" value="부산/경남"><span>부산/경남</span>
                    <input type="checkbox" name="primaryArea" value="광주/전라"><span>광주/전라</span>
                    <input type="checkbox" name="primaryArea" value="제주"><span>제주</span>
                </div>
            </div>
            <div id="next_bt">
                <a href="#" class="previous_bt" onclick="checkAll('row4','top_row2','row2_11','row2_01','row2_012','row2_011');">이전단계</a>
            </div>
            <div>
                <a href="#" class="next_bt" onclick="checkAll('firpass','secpass','row4','row5','row2_11','row2_21','row2_012','row2_013');">다음단계</a>
            </div>
        </section>
        
      <!--  -------------------------------회원정보---------------------------------->
        
        <section id="row5">
            <div id="row5_01">
                <h2>운전면허증 등록<span></span></h2>
            </div>
            
            <div id="row5_02">
                <div class="row5_02_td">면허종류</div>
                <div class="row5_02_back">
                    <input type="radio" name="licenseType" value="1종보통">
                    <span class="back_text">1종보통</span>
                    <input type="radio" name="licenseType" value="2종보통">
                    <span class="back_text">2종보통</span>
                    <input type="radio" name="licenseType" value="1종대형">
                    <span class="back_text">1종대형</span>
                </div>
                
                <div class="row5_02_td">면허번호</div>
                 <div class="row5_02_back">
                    <div class="row5_02_num01">
                    	<input id="num1" type="text" size="2" tabindex="1" maxlength="2"
                    		   name="licenseNumber1" 
                               onkeyup="licenseAdd(this, this.value)"
                               onkeydown="return showKeyCode(event)">
                    </div>
                    <div class="row5_02_num02">
                    	<input id="num2" type="text" size="2" tabindex="2" maxlength="2"
                    		   name="licenseNumber2" 
                    		   onkeyup="licenseAdd(this, this.value)"
                    		   onkeydown="return showKeyCode(event)">
                    </div>
                    <div class="row5_02_num03">
                    	<input id="num3" type="text" size="6" tabindex="3" maxlength="6"
                    		   name="licenseNumber3" 
                    	 	   onkeyup="licenseAdd(this, this.value)"
                    	 	   onkeydown="return showKeyCode(event)">
                    </div>
                    <div class="row5_02_num04">
                    	<input id="num4" type="text" size="2" tabindex="4" maxlength="2"
                    		   name="licenseNumber4" 	
                    		   onkeyup="licenseAdd(this, this.value)"
                    		   onkeydown="return showKeyCode(event)">
                    </div>
                </div>
                
                <div class="row5_02_td">갱신기간 만료일</div>
                <div class="row5_02_back">
                    <div class="row5_02_day01"><input type="text" maxlength="4" name="expiryDate1"></div>
                    <div class="row5_02_day02"><input type="text" maxlength="2" name="expiryDate2"></div>
                    <div class="row5_02_day02"><input type="text" maxlength="2" name="expiryDate3"></div>
                </div>
                
                <div class="row5_02_td">면허 발급일</div>
                <div class="row5_02_back">
                    <div class="row5_02_day01"><input type="text" maxlength="4" name="issuDate1"></div>
                    <div class="row5_02_day02"><input type="text" maxlength="2" name="issuDate2"></div>
                    <div class="row5_02_day02"><input type="text" maxlength="2" name="issuDate3"></div>
                </div>
                
                <div class="row5_02_td">생년월일</div>
                <div class="row5_02_back">
                    <div class="row5_02_birth"><input type="text" maxlength="6" name="licenseBirth">
                        <div id="birth_text">(6자리)</div>
                    </div>
                    <div id="birth_man"><input type="radio" name="gender" value="1"><span class="birth_sex">남성</span></div>
                    <div id="birth_woman"><input type="radio"  name="gender" value="2"><span class="birth_sex">여성</span></div>
                </div>
            </div>
            
            <div id="row5_03">
                <h2>결제카드 등록</h2>
            </div>
            
            <div id="row5_04">
            	<div class="row5_02_td">카드종류 </div>
                <div class="row5_02_back">
                        <select id="row5_sel" name="cardType">
                            <option>대구은행</option>
                            <option>국민은행</option>
                            <option>신한은행</option>
                            <option>기업은행</option>
                            <option>농협은행</option>
                        </select>
                </div> 
                
                <div class="row5_02_td">카드번호</div>
                <div class="row5_02_back">
                    <div id="row5_04_num01"><input type="text" name="cardNumber1" 
                    		   tabindex="1" maxlength="4"
                  			   id="CardNum1"
                  			   onkeyup="cardAdd(this, this.value)"
                   		       onkeydown="return showKeyCode(event)">
                   		       </div>
                    <span class="num_sl">-</span>
                    <div id="row5_04_num02"><input type="text" name="cardNumber2" tabindex="2" maxlength="4"
                    		   id="CardNum2"
                  			   onkeyup="cardAdd(this, this.value)"	
                    		   onkeydown="return showKeyCode(event)"></div>
                    <div class="num_sl_01">-</div>
                    <div id="row5_04_num03"><input type="text" name="cardNumber3" tabindex="3" maxlength="4"
                    		   id="CardNum3"
                  			   onkeyup="cardAdd(this, this.value)"
                    		   onkeydown="return showKeyCode(event)"></div>
                    <div id="num_sl_02">-</div>
                    <div id="row5_04_num04"><input type="text" name="cardNumber4" tabindex="4" maxlength="4"
                    		   id="CardNum4"
                  			   onkeyup="cardAdd(this, this.value)"
                    		   onkeydown="return showKeyCode(event)"></div>
                </div>
                
                <div class="row5_02_td">유효기간</div>
                <div class="row5_02_back">
                    <div id="row5_04_day"><input type="text"  name="cardExpiryDate1" maxlength="2"></div>
                    <div id="row5_04_day01"><input type="text" name="cardExpiryDate2" maxlength="2"></div>
                </div>
                
                <div class="row5_02_td">생년월일</div>
                <div class="row5_02_back">
                    <div class="row5_02_birth"><input type="text" name="birth" maxlength="6"></div>
                    <div id="birth_text">(6자리)</div>
                </div>
                
                <div class="row5_02_td">비밀번호</div>
                <div class="row5_02_back">
                    <div id="row5_04_pass"><input type="password" name="cardPassword" maxlength="4"></div>
                </div>
            </div>
            
            <div id="row5_next_bt">
                <a href="#" class="row5_previous_bt" onclick="changeDisplay('row5','row4','row2_21','row2_11','row2_013','row2_012');">이전단계</a>
            </div>
             <div>
                <a href="#" class="row5_next_bt" id="joinSubmit" 
                onclick="checkStep2('row5','row6','row2_21','row2_31','row2_013','row2_014');">회원가입</a>
            </div>
        </section>
	</form>
        <!-----------------------------------등록----------------------------------->
        <section id="row6">
            <div id="row6_img">
                <img src="../imag/member_03_img.png">
            </div>
            
            <div id="row6_02">
                <div id="row6_02_text01">축하드립니다.</div>
                <div id="row6_02_text02">회원가입이 완료되었습니다.</div>
            </div>
            
            <div id="row6_bt">
                <a href="#" class="row6_service_bt">서비스안내</a>
                <a href="#" class="row6_home_bt">홈으로</a>
            </div>
        </section> 
        <!---------------------------가입완료------------------------->
        
    </div>

</body>

</html>