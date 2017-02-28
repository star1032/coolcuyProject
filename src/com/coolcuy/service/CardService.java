package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.CardDto;

public interface CardService extends GenericService<CardDto>{
	
	public int checkDuplicate(String cardNumber);
	public int getCount();
	public int checkPassword(String cardNumber, String password);
	public List<CardDto> getAllByEmail(String element);
}
