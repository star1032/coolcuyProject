package com.coolcuy.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.UserDto;
import com.coolcuy.exception.LoginFailException;
import com.coolcuy.service.LoginService;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/loginForm.jsp";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		Map<String, Boolean> errors= new HashMap<>();
		

		if(email == null || email.isEmpty())
			errors.put("email", Boolean.TRUE);
		if(password == null || password.isEmpty())
			errors.put("password", Boolean.TRUE);
			
		request.setAttribute("errors", errors);

		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
	
		try{
		UserDto userDto= loginService.login(email, password);
		request.getSession().setAttribute("authUser", userDto);
	//	response.sendRedirect(request.getContextPath() + "/index.jsp");
		return "/index.jsp";
		}catch(LoginFailException e){
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}