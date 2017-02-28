package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.QnADto;
import com.coolcuy.service.QnAService;
import com.coolcuy.service.QnAServiceImpl;


public class QnABoardJoinHandler implements CommandHandler {
	
	private QnAService service= new QnAServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnADto dto= new QnADto(
				request.getParameter("name"),
				request.getParameter("subject"), 
				request.getParameter("textArea"), 
				request.getParameter("pass"));
				
				
				
		service.add(dto);

		response.sendRedirect("QnA.jsp");
		
		return null;
	}

}
