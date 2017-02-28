 $(document).ready(function(){
	$('.reply_btn').click( function(){
		
		$('.what').remove();
		
			var test = $(this).parent().next(); //test=다음 폼
			
			test.append('<input type="text" name="textArea" class="what" size="90" style="margin-left:150px ; padding: 5px 0px" /> <input type="submit"  class="what" value="확인">');
			
			
		});	
 });