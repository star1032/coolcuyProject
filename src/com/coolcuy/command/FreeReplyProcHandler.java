package com.coolcuy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dao.FreeBoardDaoImpl;
import com.coolcuy.dto.FreeBoardDto;


public class FreeReplyProcHandler implements CommandHandler {

//	private final String FORM_VIEW = "/FreeBoard.jsp";
	private FreeBoardDaoImpl dao = new FreeBoardDaoImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int ref= Integer.parseInt(request.getParameter("ref"));
		int pos= Integer.parseInt(request.getParameter("pos"));
		int depth= Integer.parseInt(request.getParameter("depth"));
		FreeBoardDto dto= new FreeBoardDto(
				request.getParameter("name"),
				request.getParameter("subject"),
				request.getParameter("textArea"),
				pos,
				depth,
				ref,
				request.getParameter("pass"),
				request.getParameter("userId"));		
		
		int x=dao.reply(dto); // pos값을 증가시킬 값 새로들어간 칼럼의 오토num
		int y=dao.maxPos(ref); //가장 큰 포스값 받아온 ref 값 중
		dao.replyUp(x,y);
		int num=Integer.parseInt(request.getParameter("num"));
		int nowPage=Integer.parseInt(request.getParameter("nowPage"));
		
		List<FreeBoardDto>  vlist= dao.getRepleByRef(ref);
		response.sendRedirect("/version0116/Freeread.jsp?num="+num+"&nowPage="+nowPage+"&keyField=&keyWord=");
		return null;
	}

}
