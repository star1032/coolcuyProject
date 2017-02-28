package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.CarDto;

public interface CarService extends GenericService<CarDto>{
	public List<CarDto> getAllBySpot(String SpotName);
	public List<String> getAllType(String spotName);
	public int getCountBySpot(String spotName);
	public int getCount();
}
