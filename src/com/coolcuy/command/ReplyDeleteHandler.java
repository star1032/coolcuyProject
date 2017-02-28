package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.service.FreeBoardService;
import com.coolcuy.service.FreeBoardServiceImpl;

public class ReplyDeleteHandler implements CommandHandler{

	private FreeBoardService service= new FreeBoardServiceImpl();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("댓글 삭제이벤트 들어옴");
		int i=Integer.parseInt(request.getParameter("repleNum"));
		service.deleteReply(i);
		int num=Integer.parseInt(request.getParameter("num"));
		int nowPage=Integer.parseInt(request.getParameter("nowPage"));
		request.setAttribute("num", num);
		request.setAttribute("nowPage", nowPage);
		return "Freeread.jsp";
	}

}
