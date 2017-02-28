package com.coolcuy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.CardDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.UserDto;
import com.coolcuy.service.CardService;
import com.coolcuy.service.CardServiceImpl;
import com.coolcuy.service.MemberService;
import com.coolcuy.service.MemberServiceImpl;

public class MyPageHandler implements CommandHandler {
	private final String FORM_VIEW = "/member/myPage01.jsp";
	private MemberService memberService = new MemberServiceImpl();
	private CardService cardService = new CardServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDto userDto=(UserDto)request.getSession().getAttribute("authUser");
		String email=userDto.getId();
		MemberDto member = memberService.get(email);
		List<CardDto> cards = cardService.getAllByEmail(email);
		request.setAttribute("member", member);
		request.setAttribute("cards", cards);
		
//		for(CardDto list : cards){
//			System.out.println(list.getCardNumber());
//		}
		
		for(int i=0; i<cards.size(); i++){
		}
		
		return FORM_VIEW;
	}
}
