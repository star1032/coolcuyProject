package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinFormHandler implements CommandHandler {
	private final String FORM_VIEW = "/member/joinForm.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

}
