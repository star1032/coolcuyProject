package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.SpotDto;
import com.coolcuy.dto.SpotNameDto;

public interface SpotService extends GenericService<SpotDto>{
	public int getCount();
	public List<SpotNameDto> getAllSpotName();
}
