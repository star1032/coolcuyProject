package com.coolcuy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dao.FreeBoardDaoImpl;
import com.coolcuy.dto.FreeBoardDto;

public class FreeReReplyProcHandler implements CommandHandler {
private FreeBoardDaoImpl dao = new FreeBoardDaoImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int ref= Integer.parseInt(request.getParameter("ref"));
		int pos= Integer.parseInt(request.getParameter("pos"));
		int depth= Integer.parseInt(request.getParameter("depth"));
		int replyNum= Integer.parseInt(request.getParameter("repleNum"));
		FreeBoardDto dto= new FreeBoardDto(
				request.getParameter("name"),
				request.getParameter("subject"),
				request.getParameter("textArea"),
				pos,
				depth,
				ref,
				request.getParameter("pass"),
				request.getParameter("userId"));
		dao.depthOnlyUp(ref,pos,depth);
		int[] x= dao.rereply(dto);
		int num=Integer.parseInt(request.getParameter("num"));
		int nowPage=Integer.parseInt(request.getParameter("nowPage"));
		request.setAttribute("num", num);
		request.setAttribute("nowPage", nowPage);
		
		List<FreeBoardDto>  vlist= dao.getRepleByRef(ref);
		request.setAttribute("vlist", vlist);
		return "Freeread.jsp";
	}
}
