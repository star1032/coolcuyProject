package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.FreeBoardDto;
import com.coolcuy.service.FreeBoardService;
import com.coolcuy.service.FreeBoardServiceImpl;

public class FreeBoardJoinHandler implements CommandHandler{
	
	private FreeBoardService service= new FreeBoardServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FreeBoardDto dto= new FreeBoardDto(
				request.getParameter("name"),
				request.getParameter("subject"), 
				request.getParameter("textArea"), 
				request.getParameter("pass"),
				request.getParameter("userId"));
				
				
				
		service.add(dto);

		response.sendRedirect("FreeBoard.jsp");
		
		return null;
		
	}

}
