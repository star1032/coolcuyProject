package com.coolcuy.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.PriceDto;
import com.coolcuy.service.PriceListService;
import com.coolcuy.service.PriceListServiceImpl;


public class PriceHandler implements CommandHandler {
	private final String FORM_VIEW = "/price/charge.jsp";
	private PriceListService priceService = new PriceListServiceImpl();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PriceDto> prices = priceService.getAll();
		request.setAttribute("prices", prices);
		
		return FORM_VIEW;
	}

}
