package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dao.QnADaoImpl;
import com.coolcuy.dto.QnADto;
import com.coolcuy.service.QnAService;
import com.coolcuy.service.QnAServiceImpl;

public class ReplyProcHandler implements CommandHandler{
	
	private final String FORM_VIEW = "/QnA.jsp";

	
	private QnADaoImpl dao= new QnADaoImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int ref= Integer.parseInt(request.getParameter("ref"));
		int pos= Integer.parseInt(request.getParameter("pos"));
		int depth= Integer.parseInt(request.getParameter("depth"));
		
		QnADto dto= new QnADto(
				request.getParameter("name"),
				request.getParameter("subject"),
				request.getParameter("textArea"),
				pos,
				depth,
				ref,
				request.getParameter("pass"));		
		
		dao.replyUp(ref, pos);
		dao.reply(dto);
		
		
		return FORM_VIEW;
	}

}
