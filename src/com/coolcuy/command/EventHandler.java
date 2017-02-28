package com.coolcuy.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.CouponDto;
import com.coolcuy.dto.EventDto;
import com.coolcuy.service.CouponService;
import com.coolcuy.service.CouponServiceImpl;
import com.coolcuy.service.EventService;
import com.coolcuy.service.EventServiceImpl;

public class EventHandler implements CommandHandler {
	private final String FORM_VIEW = "/event/event.jsp";
	private EventService eventService = new EventServiceImpl();
	private CouponService couponService = new CouponServiceImpl();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("event에 들어왓니 ?");
		List<EventDto> events = eventService.getAll();
		request.setAttribute("events", events);
		List<CouponDto> coupons = couponService.getAll();
		request.setAttribute("coupons", coupons);
		
		return FORM_VIEW;
	}

}
