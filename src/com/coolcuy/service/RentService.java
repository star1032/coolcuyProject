package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.RentDto;

public interface RentService extends TransGenericService<RentDto>{
	public List<CarDto> getRentAbleCar(String spot, String type, String startDate, String endDate, String email);
	
}
