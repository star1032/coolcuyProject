package com.coolcuy.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dao.SequenceTemplateDao;
import com.coolcuy.dto.SpotDto;
import com.coolcuy.service.CarService;
import com.coolcuy.service.CarServiceImpl;
import com.coolcuy.service.SpotServiceImpl;


public class SpotServiceTest {
	List<SpotDto> spots = new ArrayList<SpotDto>();
	SpotServiceImpl service = new SpotServiceImpl();
	CarService carService = new CarServiceImpl();
	SequenceTemplateDao sequence = new SequenceTemplateDao();
	
	
	@Before
	public void setUp(){
		carService.deleteAll();
		service.deleteAll();
				
		spots = Arrays.asList(
					new SpotDto("서울지점", "1111", "관리자", "0900", "2200", "010-111-2222", "서울", "11111", "서울시 호이호이", "자세한 주소 없음"),
					new SpotDto("대구지점", "1111", "관지자",  "0830", "2000", "010-222-3333", "대구", "11111", "대구시 호이호이", "자세한 주소 없음"),
					new SpotDto("부산지점", "1111", "관지자",  "0930", "3000", "010-333-4444", "부산", "11111", "부산시 호이호이", "자세한 주소 없음")
				);
	}
	
	@Test
	public void addAndGet(){
		addForTest();
		
		SpotDto getSpot = service.get(spots.get(0).getSpotName());
		
		validate(spots.get(0), getSpot);
	}
	
	public void validate(SpotDto spot, SpotDto getSpot){
		assertThat(spot.getPassword(), is(getSpot.getPassword()));
	}
	
	public void addForTest(){
		for(SpotDto spot : spots){
			service.add(spot);
		}
	}
	
	@Test
	public void getAll(){
		addForTest();
		
		List<SpotDto> getCars = service.getAll();
		
		for(int i=0; i<spots.size(); i++){
			validate(spots.get(i), getCars.get(i));
		}
	}
	
	@Test
	public void deleteAndCount(){
		addForTest();
		
		service.delete(spots.get(0).getSpotName());
		
		int x = service.getCount();
		
		assertThat(x, is(2));
	}
	
	@Test
	public void update(){
		addForTest();
		
		SpotDto updateSpot = new SpotDto("서울지점", "1111", "관리자", "0930", "1200", "010-5555-2222", "서울", "11111", "서울시 호이호이", "자세한 주소 없음");
		
		service.update(updateSpot);
		
		SpotDto updatedSpot = service.get(spots.get(0).getSpotName());
		
		validate(updateSpot, updatedSpot);
	}
}
