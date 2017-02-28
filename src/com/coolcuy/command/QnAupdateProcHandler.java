package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.QnADto;
import com.coolcuy.service.QnAService;
import com.coolcuy.service.QnAServiceImpl;

public class QnAupdateProcHandler implements CommandHandler{
	private QnAService service= (QnAService) new QnAServiceImpl();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String inPass=request.getParameter("pass");
		String nowPage= request.getParameter("nowPage");
		
		QnADto dbDto=service.get(Integer.parseInt(request.getParameter("num")));
		String dbPass=dbDto.getPass();
		
		if(!inPass.equals(dbPass)){
			request.setAttribute("passwordError", "패스워드 입력 똑바로 해라잉~");
			return "QnAupdate.jsp";
		}
		
		QnADto dto=new QnADto();
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setTextArea(request.getParameter("textArea"));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		service.update(dto);
		
		String url ="QnAread.jsp?nowPage="+nowPage+"&num="+dto.getNum();
		
		return url;
	}
}
