package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.FreeBoardDto;
import com.coolcuy.service.FreeBoardService;
import com.coolcuy.service.FreeBoardServiceImpl;

public class FreeUpdateProcHandler implements CommandHandler{
	private FreeBoardService service=  new FreeBoardServiceImpl();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("여기로들어오나?");
		String inPass=request.getParameter("pass");
		String nowPage= request.getParameter("nowPage");
		
		FreeBoardDto dbDto=service.get(Integer.parseInt(request.getParameter("num")));
		String dbPass=dbDto.getPass();
		
		if(!inPass.equals(dbPass)){
			request.setAttribute("passwordError", "패스워드 입력 똑바로 해라잉~");
			return "Freeupdate.jsp";
		}
		
		FreeBoardDto dto=new FreeBoardDto();
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setTextArea(request.getParameter("textArea"));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		service.update(dto);
		
		String url ="Freeread.jsp?nowPage="+nowPage+"&num="+dto.getNum();
		
		return url;
		
	}

}
