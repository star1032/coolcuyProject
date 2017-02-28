package com.coolcuy.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.UserDto;
import com.coolcuy.service.MemberService;
import com.coolcuy.service.MemberServiceImpl;

public class UpdateHandler implements CommandHandler {
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDto userDto=(UserDto)request.getSession().getAttribute("authUser");
		String email=userDto.getId();
		String name=userDto.getName();
		String phoneNumber=request.getParameter("zeroPhone")+"-"+request.getParameter("firPhone")+"-"+request.getParameter("secPhone");
		String licenseNumber=request.getParameter("licenseNumber1")+"-"+request.getParameter("licenseNumber2")+"-"+request.getParameter("licenseNumber3")+"-"+request.getParameter("licenseNumber4");
		String issuDate=request.getParameter("issuDate1")+"-"+request.getParameter("issuDate2")+"-"+request.getParameter("issuDate3");
		String expiryDate=request.getParameter("expiryDate1")+"-"+request.getParameter("expiryDate2")+"-"+request.getParameter("expiryDate3");
		MemberDto updateMember = memberService.get(email);
		String gender=updateMember.getLicenseDto().getGender();
		String licenseType=(String)request.getParameter("licenseType");
		String password;
		
		if(request.getParameter("changePassword")=="" || request.getParameter("changePassword")==null ){
			password=memberService.get(email).getPassword();
		}else{
			password=request.getParameter("changePassword");
		}
		
		LicenseDto license=new LicenseDto(email,
				licenseNumber,
				licenseType,
				gender,
				issuDate,
				expiryDate);
		
			MemberDto member= new MemberDto(email,
				phoneNumber,
				name,
				password,
				request.getParameter("zipAddr"),
				request.getParameter("roadAddr"),
				request.getParameter("detailAddr"),
				request.getParameter("primaryArea"),
				license);
		
		try{
			memberService.update(member);

		}catch(Exception e){
			e.printStackTrace();
			return "/index.do";
		}
		
		return "/myPage.do";
	}

}
