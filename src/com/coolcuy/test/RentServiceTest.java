package com.coolcuy.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.RentDto;
import com.coolcuy.service.RentService;
import com.coolcuy.service.RentServiceImpl;

public class RentServiceTest {
	RentService service = new RentServiceImpl();
	List<RentDto> rents = new ArrayList<RentDto>();
	
	@Before
	public void setUp(){
		
	}
	
	
	@Test
	public void get() {
		
	}

}
