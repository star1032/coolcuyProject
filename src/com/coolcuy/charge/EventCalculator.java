package com.coolcuy.charge;

import java.util.List;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.EventDto;

public class EventCalculator {
	public EventCalculator() {}
	
	public List<CarDto> operator(List<EventDto> eventList, List<CarDto> carList){
		String saleName = null;
		int disCount = 0;
		
		for(CarDto car : carList){
			for(EventDto event : eventList){
				String types[] = event.getLimitType().split(",");
				
				if(compareType(car.getType(), types)){
					saleName = event.getSaleName();
					disCount = Integer.parseInt(saleName.substring(0, saleName.length() - 1));
					
					car.setEventAppMoney(innerOperator(car.getTotalMoney(), disCount));
				}
			}
		}
		
		return carList;
	}
	
	public int innerOperator(int totalMoney, int disCount){
		return (int)(totalMoney * (1-(double)disCount / 100));
	}
	
	public boolean compareType(String carType, String[] eventTypes){
		for(int i=0; i<eventTypes.length; i++){
			System.out.println(eventTypes[i].trim() + "||");
			if(carType.equals(eventTypes[i].trim())){
				return true;
			}
		}
		
		return false;
	}
}
