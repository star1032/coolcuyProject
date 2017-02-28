package com.coolcuy.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.CarDto;
import com.coolcuy.service.CarService;
import com.coolcuy.service.CarServiceImpl;

public class CarDaoTest {
	CarService service = new CarServiceImpl();
	List<CarDto> cars = new ArrayList<CarDto>();
	
	@Before
	public void setUp(){
		service.deleteAll();
		
		cars = Arrays.asList(
				new CarDto("00가 0000", "기아", 3000, "경차", "6", "후방카메라, 네비게이션", "휘발유", "레이", "2017", "6000", "0", "대구지점"),
				new CarDto("22가 2222", "기아", 3000, "suv", "8", "후방카메라, 네비게이션", "디젤", "모하비", "2015", "10000", "1","대구지점"),
				new CarDto("33가 3333", "현대", 3000, "대형", "6", "후방카메라, 네비게이션", "LPG", "그랜져", "2016", "7000", "0", "대구지점"),
				new CarDto("44가 4444", "기아", 3000, "중형", "6", "후방카메라, 네비게이션", "휘발유", "K5", "2017", "6000", "0", "대구지점"),
				new CarDto("12가 4512", "기아", 3000, "중형", "6", "후방카메라, 네비게이션", "휘발유", "K5(LPG)", "2017", "6000", "0", "대구지점"),
				new CarDto("55가 5555", "기아", 3000, "대형", "6", "후방카메라, 네비게이션", "휘발유", "체어맨", "2017", "6000", "0", "대구지점"),	
				new CarDto("66가 6666", "현대", 3000, "대형", "6", "후방카메라, 네비게이션", "휘발유", "제네시스", "2017", "6000", "0", "대구지점"),	
				new CarDto("77가 7777", "현대", 3000, "준중형", "6", "후방카메라, 네비게이션", "휘발유", "아반떼", "2017", "6000", "0", "대구지점"),	
				new CarDto("88가 8888", "현대", 3000, "준중형", "6", "후방카메라, 네비게이션", "휘발유", "엑센트", "2017", "6000", "0", "부산지점"),	
				new CarDto("99가 9999", "기아", 3000, "경차", "6", "후방카메라, 네비게이션", "휘발유", "모닝", "2017", "6000", "0", "부산지점")	
		);
	}
	
	@Test
	public void addAndGet(){
		addByTest();
		
		CarDto getCar = service.get(cars.get(0).getCarNumber());
		validate(cars.get(0), getCar);		
	}
	
	public void addByTest(){
		for(CarDto car : cars)
			service.add(car);
	}
	
	@Test
	public void getAllAndDeleteAndCount(){
		addByTest();
		List<CarDto> getCars = service.getAll();
		
		for(int i=0; i<cars.size(); i++)
			validate(cars.get(i), getCars.get(i));
		int x = service.getCountBySpot(getCars.get(0).getSpotName());
		
		assertThat(x, is(7));
		
		service.delete(cars.get(0).getCarNumber());
		
		x = service.getCount();
		
		assertThat(x, is(8));
	}
	
	@Test
	public void getAllBySpot(){
		addByTest();
		List<CarDto> getCars = service.getAllBySpot(cars.get(0).getSpotName());
		
	
		for(int i=0; i<cars.size()-2; i++){
			validate(cars.get(i), getCars.get(i));
		}

	}
	
	@Test
	public void update(){
		addByTest();
		
		CarDto updateCar = new CarDto("00가 0000", "현대", 2000, "중형", "6", "후방카메라", "휘발유", "그랜져", "2016", "15000", "0", "부산지점");
		
		CarDto updatedCar = service.update(updateCar);
		
		validate(updateCar, updatedCar);
	}
	
	public void validate(CarDto car, CarDto getCar){
		assertThat(car.getCarNumber(), is(getCar.getCarNumber()));
		assertThat(car.getCarName(), is(getCar.getCarName()));
		assertThat(car.getBrand(), is(getCar.getBrand()));
		assertThat(car.getKilometer(), is(getCar.getKilometer()));
		assertThat(car.getOilType(), is(getCar.getOilType()));
		assertThat(car.getType(), is(getCar.getType()));
		assertThat(car.getOptions(), is(getCar.getOptions()));
		assertThat(car.getSeat(), is(getCar.getSeat()));
		assertThat(car.getYearModel(), is(getCar.getYearModel()));
		assertThat(car.getTimeMoney(), is(getCar.getTimeMoney()));
		assertThat(car.getSpotName(), is(getCar.getSpotName()));
	}
}