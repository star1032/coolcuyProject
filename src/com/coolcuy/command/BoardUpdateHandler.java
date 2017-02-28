package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.BoardDto;
import com.coolcuy.service.BoardService;
import com.coolcuy.service.BoardServiceImpl;

public class BoardUpdateHandler implements CommandHandler{

	private BoardService service= (BoardService) new BoardServiceImpl();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String inPass=request.getParameter("pass");
		String nowPage= request.getParameter("nowPage");
		
		BoardDto dbDto=service.get(Integer.parseInt(request.getParameter("num")));
		String dbPass=dbDto.getPass();
		
		if(!inPass.equals(dbPass)){
			request.setAttribute("passwordError", "패스워드 입력 똑바로 해라잉~");
			return "update.jsp";
		}
		
		BoardDto dto=new BoardDto();
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setTextArea(request.getParameter("textArea"));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		service.update(dto);
		
		String url ="read.jsp?nowPage="+nowPage+"&num="+dto.getNum();
		
		return url;
	}
	
}
