<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <c:if test="${!empty authUser}"> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/version0116/css/reserve01.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<title>실시간 예약</title>

<style>
	.v_detail{display: none;}
	.cancel_line{text-decoration:line-through; text-decoration-color: red;}
</style>

<script>
// Calendar UI (datepicker)
$.datepicker.setDefaults({
    dateFormat: 'yy.mm.dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
  });

// 오늘날짜 리턴(날짜에 '0' 을 붙이기 위한 객체)
var date = new Date();

var today = {
	getDay : function(){
				var day = date.getDate();
				if(day < 10)
					return '0' + day;
				else 
					return day;
				},
	getMonth : function(){
				var month = date.getMonth();
				if(month+1 < 10)
					return '0' + (month + 1);
				else
					return getMonth;
				},
				
	getYears : function(){
					return date.getFullYear();
				},
				
	getHours : function(){
				var hours = date.getHours();
				if(hours< 10)
					return '0' + hours;
				else
					return hours;
				},
				
	getMinute : function(){
				var min = date.getMinutes();
				if(min < 10)
					return '0' + min;
				else
					return min;
				},
	getDate : function(){
				return this.getYears() +'.'+ this.getMonth() +'.'+ this.getDay();
				}
				
};


$(document).ready(function(){	
	var date = new Date();
	var start = $('#startDate');
	var end = $('#endDate');
	
	var curHours = today.getHours() + 1;
	var curMinute = Math.ceil(today.getMinute() / 10);
	
	// 날짜 초기화
	start.val(today.getDate());
	end.val(today.getDate());
	
	$("#startHours option:eq(" + curHours + ")").attr("selected", "selected");
	$("#endHours option:eq("+ curHours +")").attr("selected", "selected");
	
	$("#startMinute option:eq("+ curMinute +")").attr("selected", "selected");
	$("#endMinute option:eq("+ curMinute +")").attr("selected", "selected");
	
	// 달력
	start.datepicker();
	end.datepicker();
	
	// 현재 날짜보다 이전 날짜이면 현재 날짜로 되돌린다.
	start.change(function(){
		if(start.val() < today.getDate()){
			start.val(today.getDate());
		}
	});
	end.change(function(){
		if(end.val() < today.getDate()){
			end.val(today.getDate());
		}
	});
	
	var spotArea = new Array();
	var spotName = new Array();	
	var getData = '';
	
	$.ajax({
			url: "getSpotName.data",
			method: "POST",
			dataType: "json",
			success : function(data){				
				var getArea = new Array();
				
				// 지역 정렬
				for(i=0; i<data.length; i++){
					getArea[i] = data[i].spotArea;
				}
				getArea.sort();
				
				// 지역 중복제거 And 지역 selectBox 출력
				var cnt = 1;
				spotArea[0] = getArea[0];
				$('#startArea').append('<option value=' + spotArea[0] + '>' + spotArea[0] + '</option>');
 				$('#endArea').append('<option value=' + spotArea[0] + '>' + spotArea[0] + '</option>');
				
 				for(i=1; i<getArea.length; i++){
					if(spotArea[cnt-1] != getArea[i]){
						spotArea[cnt] = getArea[i];
						$('#startArea').append('<option value=' + spotArea[cnt] + '>' + spotArea[cnt] + '</option>');
		 				$('#endArea').append('<option value=' + spotArea[cnt] + '>' + spotArea[cnt] + '</option>');		
		 				cnt++;
					}
				}
 				getData = data;
			}	// success
		});	// ajax
		
	// 대여 지점 출력
	$('#startArea').change(function(){
		$('#startName').html('');
		$('#endName').html('');
		for(i=0; i<getData.length; i++){
			if($(this).val() == getData[i].spotArea){					
				$('#startName').append('<option value=' + getData[i].spotName + '>' + getData[i].spotName + '</option>');
				$('#endName').append('<option value=' + getData[i].spotName + '>' + getData[i].spotName + '</option>');
				$('#endArea option:contains(' + $(this).val() + ')').attr("selected", "selected");
			}
		}
		
		setCarType();
	});
	
	$('#startName').change(function(){
		var index = $(this).prop('selectedIndex');
		$('#endName option:eq('+ index +')').attr('selected', 'selected');
		setCarType();
	});
	
	function setCarType(){
		$.ajax({
			url: "getCarType.data",
			method: "POST",
			data:{startName : $('#startName').val()},
			dataType: "json",
			success : function(data){
				$('#carType').html('');
				$('#carType').append('<option>자동차 유형을 선택하세요.</option>');
				for(i=0; i<data.length; i++){
					$('#carType').append('<option value=' + data[i] + '>' + data[i] + '</option>');
				}
			}
		});
	}
			
	// 반납 지점 출력
	$('#endArea').change(function(){
		$('#endName').html('');
		for(i=0; i<getData.length; i++){
				if($(this).val() == getData[i].spotArea){						
					$('#endName').append('<option value=' + getData[i].spotName + '>' + getData[i].spotName + '</option>');
				}
			}
	});
	
	function removeDot(element){
		var list = element.split('.');
		var ret = '';
		
		for(i=0; i<list.length; i++)
			ret += list[i];
		
		return ret;
	}
	
	// 자동차 타입(유형) 선택
	$('#carType').change(function(){
		var r_startDate = $('#startDate').val();
		var r_endDate = $('#endDate').val();
		var startDate = removeDot($('#startDate').val()) + $('#startHours').val() + $('#startMinute').val();
		var endDate = removeDot($('#endDate').val()) + $('#endHours').val() + $('#endMinute').val();
		var startName = $('#startName').val();
		var endName = $('#endName').val();
		var carType = $('#carType').val();
		
		$.ajax({
			url: "getCar.data",
			method: "POST",
			data:{'startDate' : startDate,
				'endDate' : endDate,
				'startName' : startName,
				'endName' : endName,
				'carType' : carType },
			dataType: "json",
			success : function(data){
				$('.row3').html('');
				var eventApp = '';
				if(data.length > 0){
					for(i=0; i<data.length; i++){
						$('.row3').append('<form action ="rent.do" method="post">'
								+'<input type="hidden" name="startDate" value="'+startDate+'"/>'
								+'<input type="hidden" name="endDate" value="'+endDate+'"/>'
								+'<input type="hidden" name="startName" value="'+startName+'"/>'
								+'<input type="hidden" name="endName" value="'+endName+'"/>'
								+'<input type="hidden" name="carNumber" value="'+data[i].carNumber+'"/>'
								+'<div class="row3_td01">지점</div>'
				                + '<div class="row3_td02">차량</div>'
				                + '<div class="row3_td03">대여요금</div>'
				                + '<div class="row3_td04">시간당요금</div>'
				                + '<div class="row3_td01" id="td01">'
				                + '	<div class="td01_01 startSpotInfo">대여: <span>'+ startName +'</span> </div>'
				                + '	<div class="td01_time startDateInfo">'+ r_startDate +'</div>'
				                + '	<div class="td01_01 endSpotInfo">반납: <span>'+ endName +'</span></div>'
				                + '	<div class="td01_time endDateInfo">'+ r_endDate +'</div>'
				                + '</div>'
				                + '<div class="row3_td02" id="td02">'
				                + '	<div class="td02_carimg">'
				                + '	<img src="carImg/k5.jpg">'
				                + ' </div>'
				                + ' <div class="td02_text">'
				                + ' 	<div class="td02_text01">'+data[i].carName +'</div>'
				                + '     <div class="td02_text02">유종 : '+data[i].oilType+'</div>'
				                + '     <div class="td02_text03">옵션 : '+data[i].options+'</div>'
				                + ' </div>'
				                + '</div>'
				                + '<div class="row3_td03 totalMoney" id="td03">'
				                + ' <div class="td03_text" name="totalMoney">'+data[i].totalMoney+'원</div>'
				                + '</div>'
				                + '<div class="row3_td04" id="td04">'
				                + '  <div class="td04_text" name="timeMoney">'+data[i].timeMoney+'원</div>'
				                + '</div>'
				                
				            	+ '<div class="row3_atext01">'
				                + '		<a href="javascript:;" target="_self" class="v_btn">자세히 </a>'
				                + '</div>'
				                + '<div class="clear"></div>'
				                + '<div class="v_detail">'
				                + '		<div class="row3_text01">할인&#8226;보험&#8226;옵션 선택</div>'
				                + '		<div class="row3_select02">'
				                + '    		<select name="babySeat">'
				                + '				<option value="0">베이비 시트 비 적용</option>'
				                + '				<option value="1">베이비 시트 적용</option>'
				                + '			</select>'
				                + '		</div>'
				                + '		<div class="row3_select02" id="select02_02">'
				                + '   	 	<select class="insurance" name="insurance">'
				                + '       	 	<option value="0">비 보험</option>'
				                + '       	 	<option value="1">보험(20,000원)</option>'
				                + '    		</select>'
				                + '		</div>'
				                + '		<div class="bt">'
				                + '      	  <button class="row3_bt">예약하기</button>'
				                + '		</div>'			           
				                + '</div>' 
				                + '	</form>');
						
						if(data[i].eventAppMoney > 0){
							$('.td03_text').html('<p class="cancel_line">금액 :' + data[i].totalMoney + '원</p>');						
							$('.td03_text').append('<p>할인 금액: '+data[i].eventAppMoney+'원</p>');
						}
						// 보험 select Box에 클래스 명으로 순번을 정한다.
	// 					$('.insurance').each(function(index){
	// 						$('.insurance').addClass('insurace_'+ index);
	// 					});
					}	// for
				
					viewDtail();
					appInsurance(data);
				}else{	// if
					$('.row3').append('<div class="row3_td01">렌트 가능 차량이 없습니다.</div>');
				}
			} // success
		});	// ajax
	});	// function
	
	function appInsurance(data){		
		$('.insurance').change(function(){
			var insurance = $(this);
			var selector = insurance.parent().parent().prev().prev().prev().prev().children();
			var index = $(".insurance").index(this);		// 이거 해결하는데 2시간 결렸다 ㅠㅠ $(this).index(); 는 형제 노드가 몇개인지 찾는 거고 와 $('.insurance').index(this); 전제 document에서 .insurance 클래스를 모두 select한 뒤 this 가 몇 번째 인덱스인지 찾는거다.
			var flag = $(this).children('option:selected').index();
			
			if(flag == 1){
				selector.append('<p>+20,000원(보험)</p>');
			}	
		});
	}
	
	function viewDtail(){
		var v_btn = $('.v_btn');
		
		$('.v_btn').click(function(){
			var v_detail = $(this).parent().next().next();
			v_detail.toggle(function(){
				$('.row3_bt').focus();
			});
		});
	}
});

</script>
</head>
<body>
	<div id="wrap">
        <jsp:include page="../top.jsp" />
        
        <section id="title_box">
            <div id="row1_text01">실시간예약</div>
            <div id="row1_text02">
                <ul>
                    <li>
                        <a href="#"><img src="imag/member_img02.png"></a>
                    </li>
                    <li class="text02_up"><a href="#">Home</a></li>
                    <li class="text02_up"><a>></a></li>
                    <li class="text02_up"><a href="#">실시간예약 </a></li>
                </ul>
            </div>
        </section>
        
	        <section id="row2">
	            <div class="row2_title">
	                <h2>예약항목선택 </h2>
	            </div>
	            <div class="row2_tab">
	                <div class="row2_td01">대여기간 </div>
	                <div class="row2_td02">
	                    <div class="td02_sel">
	                    	<input type="text" id="startDate" name="startDate"/>
	                    </div>
	                    <div class="td02_time01">
	                        <select id="startHours" name="startHours">
	                        	<c:forEach var="i" begin="0" end="23">
	                        		<c:if test="${i < 10}">
	                            		<option value="0${i}">0${i}시</option>
	                            	</c:if>
	                            	<c:if test="${i > 10}">
	                            		<option value="${i}">${i}시</option>
	                            	</c:if>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="td02_time02">
	                        <select id="startMinute" name="startMinute">
	                            <option value="00">00분</option>
	                            <option value="10">10분</option>
	                            <option value="20">20분</option>
	                            <option value="30">30분</option>
	                            <option value="40">40분</option>
	                            <option value="50">50분</option>
	                        </select>
	                    </div>
	                </div>
	
	                <div class="row2_td01">반납기간 </div>
	                <div class="row2_td02">
	                    <div class="td02_sel">
	                        <input type="text" id="endDate" name="endDate"/>
	                    </div>
	                    <div class="td02_time01">
	                        <select id="endHours" name="endHours">
	                           <c:forEach var="i" begin="0" end="23">
	                        		<c:if test="${i < 10}">
	                            		<option value="0${i}">0${i}시</option>
	                            	</c:if>
	                            	<c:if test="${i > 10}">
	                            		<option value="${i}">${i}시</option>
	                            	</c:if>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="td02_time02">
	                        <select id="endMinute" name="endMinute">
	                            <option value="00">00분</option>
	                            <option value="10">10분</option>
	                            <option value="20">20분</option>
	                            <option value="30">30분</option>
	                            <option value="40">40분</option>
	                            <option value="50">50분</option>                           
	                        </select>
	                    </div>
	                </div>
	
	                <div class="row2_td01">대여지점 </div>
	                <div class="row2_td02">
	                    <div class="td02_area01">
	                        <select id="startArea">
	                           <option>대여 지역 선택</option>
	                        </select>
	                    </div>
	                    <div class="td02_area02">
	                        <select id="startName"  name="startName">
								<option>대여 지점 선택</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="clear"></div>
	
	                <div class="row2_td01">반납지점 </div>
	                <div class="row2_td02">
	                    <div class="td02_area03">
	                        <select id="endArea">
		                    	<option>반납지역 선택</option>
	                        </select>
	                    </div>
	                    <div class="td02_area04">
	                        <select id="endName" name="endName">
		                    	<option>반납 지점 선택</option>
	                        </select>
	                    </div>
	                </div>
	
	                <div class="row2_td01">자동차유형 </div>
	                <div class="row2_td02">
	                    <div class="td02_car">
	                        <select id="carType">
	                            <option>자동차 유형을 선택하세요</option>
	                        </select>
	                    </div>
	                </div>
	            </div>
	
	            <div class="row3">
	<!--                 <div class="row3_td01">지점</div> -->
	<!--                 <div class="row3_td02">차량</div> -->
	<!--                 <div class="row3_td03">대여요금</div> -->
	<!--                 <div class="row3_td04">시간당요금</div> -->
	<!--                 <div class="row3_td01" id="td01"> -->
	<!--                     <div class="td01_01 startSpotInfo">대여: <span>대구지점</span> </div> -->
	<!--                     <div class="td01_time startDateInfo">2017. 02. 23</div> -->
	<!--                     <div class="td01_01 endSpotInfo">반납: <span>대구지점</span></div> -->
	<!--                     <div class="td01_time endDateInfo">2017. 02. 24</div> -->
	<!--                 </div> -->
	<!--                 <div class="row3_td02" id="td02"> -->
	<!--                     <div class="td02_carimg"> -->
	<!--                         <img src="carImg/k5.jpg"> -->
	<!--                     </div> -->
	<!--                     <div class="td02_text"> -->
	<!--                         <div class="td02_text01">K5</div> -->
	<!--                         <div class="td02_text02">유종 : 휘발유 </div> -->
	<!--                         <div class="td02_text03">옵션 : 네비게이션 </div> -->
	<!--                     </div> -->
	<!--                 </div> -->
	<!--                 <div class="row3_td03" id="td03"> -->
	<!--                     <div class="td03_text">139,200원<br>  -->
	<!--                     	&nbsp&nbsp&nbsp&nbsp + 20000(보험)</div> -->
	<!--                 </div> -->
	<!--                 <div class="row3_td04" id="td04"> -->
	<!--                     <div class="td04_text">5800원</div> -->
	<!--                 </div> -->
	<!--                 <div class="row3_atext01"> -->
	<!--                     <a href="#" class="v_btn">자세히 </a> -->
	<!--                 </div> -->
	<!--                 <div class="clear"></div> -->
	<!--                 <div class="v_detail"> -->
	<!-- 	                <div class="row3_text01">할인&#8226;보험&#8226;옵션 선택</div> -->
	<!-- 	                <div class="row3_select02"> -->
	<!-- 	                    <select> -->
	<!-- 	                		<option value="0">베이비 시트 비 적용</option> -->
	<!-- 	                		<option value="1">베이비 시트 적용</option> -->
	<!-- 	                	</select> -->
	<!-- 	                </div> -->
	<!-- 	                <div class="row3_select02" id="select02_02"> -->
	<!-- 	                    <select> -->
	<!-- 	                        <option>보험(20,000원)</option> -->
	<!-- 	                    </select> -->
	<!-- 	                </div> -->
	<!-- 	                <div class="row3_select02" id="select02_03"> -->
	<!-- 	                	<select> -->
	<!-- 	                        <option>쿠폰을 선택하세요</option> -->
	<!-- 	                    </select> -->
	<!-- 	                </div> -->
	<!-- 	                <div class="bt"> -->
	<!-- 	                    <a href="#"> -->
	<!-- 	                        <div class="row3_bt">예약하기</div> -->
	<!-- 	                    </a> -->
	<!-- 	                </div> -->
	<!-- 	            </div> -->
	            </div>
	
	        </section>
        
    </div>	
</body>
</html>

<%-- </c:if> --%>

<%-- <c:if test="${empty authUser}"> --%>
<%-- 	<%response.sendRedirect("/version0116/login.do"); %> --%>
<%-- </c:if> --%>