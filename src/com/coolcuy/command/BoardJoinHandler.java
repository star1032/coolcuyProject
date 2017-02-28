package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.BoardDto;
import com.coolcuy.service.BoardService;
import com.coolcuy.service.BoardServiceImpl;


public class BoardJoinHandler implements CommandHandler {
	private BoardService service= (BoardService) new BoardServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDto dto= new BoardDto(
				request.getParameter("name"),
				request.getParameter("subject"),
				request.getParameter("textArea"), 
				request.getParameter("pass"), 
				request.getParameter("ip")
				);
				
		service.add(dto);

		response.sendRedirect("list.jsp");

		return null;
	}

}
