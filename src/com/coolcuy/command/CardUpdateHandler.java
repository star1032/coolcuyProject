package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.CardDto;
import com.coolcuy.service.CardService;
import com.coolcuy.service.CardServiceImpl;

public class CardUpdateHandler implements CommandHandler {
	private CardService cardService = new CardServiceImpl();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cardType=request.getParameter("bank");
		String cardNumber=request.getParameter("card1")+"-"+request.getParameter("card2")+"-"+request.getParameter("card3")+"-"+request.getParameter("card4");
		String cardExpiryDate=request.getParameter("month")+"-"+request.getParameter("year");
				
		CardDto cardDto= new CardDto(
				cardNumber,
				cardType, 
				cardExpiryDate,
				request.getParameter("cardBirth"), 
				request.getParameter("cardPassword"), 
				request.getParameter("cardEmail"));
		
		try {
			cardService.add(cardDto);
		} catch (Exception e) {
			e.printStackTrace();
			return "/index.do";
		}
		
		return "/myPage.do";
	}

}
