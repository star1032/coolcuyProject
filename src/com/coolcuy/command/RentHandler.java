package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.RentDto;
import com.coolcuy.dto.UserDto;
import com.coolcuy.service.RentService;
import com.coolcuy.service.RentServiceImpl;

public class RentHandler implements CommandHandler{
	RentService service = new RentServiceImpl();
	
	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserDto userDto=(UserDto)request.getSession().getAttribute("authUser");
		String email=userDto.getId();
		
		
		RentDto rent = new RentDto(
				email, 
				request.getParameter("carNumber"), 
				request.getParameter("startName"), 
				request.getParameter("endName"), 
				"0", 
				"0", 
				request.getParameter("startDate"), 
				request.getParameter("endDate"), 
				null, 
				null, 
				request.getParameter("insurance"), 
				request.getParameter("babySeat"));
		
		try{
			int x = service.add(rent);
			System.out.println(x);
		}catch(RuntimeException e){
			System.out.println("등록 실패..");
		}
		
		return "success.jsp";
	}

}
