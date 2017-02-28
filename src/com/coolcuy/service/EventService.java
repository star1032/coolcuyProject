package com.coolcuy.service;

import com.coolcuy.dto.EventDto;

public interface EventService extends GenericService<EventDto> {
	
	public EventDto updateImage(EventDto object);
	public int getCount();

}
