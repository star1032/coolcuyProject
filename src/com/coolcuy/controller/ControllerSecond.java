package com.coolcuy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.SpotNameDto;
import com.coolcuy.dto.UserDto;
import com.coolcuy.service.CarService;
import com.coolcuy.service.CarServiceImpl;
import com.coolcuy.service.RentService;
import com.coolcuy.service.RentServiceImpl;
import com.coolcuy.service.SpotService;
import com.coolcuy.service.SpotServiceImpl;
import com.google.gson.Gson;

@WebServlet("*.data")
public class ControllerSecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ControllerSecond() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		System.out.println(uri);
		
		if(command.equals("/getSpotName.data")){
			
			try{
				SpotService service = new SpotServiceImpl();
				List<SpotNameDto> getSpotName = service.getAllSpotName();
				
				Gson g = new Gson();
				String spotName = g.toJson(getSpotName);	
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(spotName);
			}catch(RuntimeException e){
				e.printStackTrace();
			}
		} else if(command.equals("/getCarType.data")){
			CarService service = new CarServiceImpl();
			List<String> getCarType = service.getAllType(request.getParameter("startName"));
			
			Gson g = new Gson();
			String spotName = g.toJson(getCarType);
						
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(spotName);
		} else if(command.equals("/getCar.data")){
			RentService service = new RentServiceImpl();
			UserDto user = (UserDto)request.getSession().getAttribute("authUser");
			System.out.println("contllorer 들왔");
			List<CarDto> getCars = service.getRentAbleCar(
					request.getParameter("startName").trim(), 
					request.getParameter("carType").trim(), 
					request.getParameter("startDate").trim(),
					request.getParameter("endDate").trim(),
					user.getId()
					);
			Gson g = new Gson();
			
			String cars = g.toJson(getCars);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(cars);
		}
	}
}
