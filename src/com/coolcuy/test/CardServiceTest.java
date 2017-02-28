package com.coolcuy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.CardDto;
import com.coolcuy.exception.DuplicateException;
import com.coolcuy.exception.NotFoundCardExecption;
import com.coolcuy.service.CardServiceImpl;

public class CardServiceTest {
	CardServiceImpl service = new CardServiceImpl();
	
	List<CardDto> cards = new ArrayList<CardDto>();
	
	@Before
	public void setup(){
		service.deleteAll();
		
		cards = Arrays.asList(
				new CardDto("111-456-7890-00", "대구은행", "10/11","851130","1234","a@naver.com"),
				new CardDto("222-456-7891-00", "농협", "10/11","851130","1234","a@naver.com"),
				new CardDto("333-456-7892-00", "우리은행", "10/11","851130","1234","a@naver.com")
				);
	}
		
	@Test
	public void addAndGet() {
		addByTest();
		
		int count = service.getCount();
		
		assertThat(count, is(cards.size()));
		
		CardDto getCard = service.get(cards.get(0).getCardNumber());
		
		validate(getCard, cards.get(0));
	}
	
	@Test
	public void getAll(){
		addByTest();
		
		List<CardDto> getCards = service.getAll();
		
		for(int i=0; i<cards.size(); i++){
			validate(getCards.get(i), cards.get(i));
		}
	}
	
	@Test(expected = DuplicateException.class)
	public void failCheckDuplicate(){
		addByTest();
		
		service.checkDuplicate(cards.get(0).getCardNumber());
	}
	
	@Test
	public void checkDuplicate(){
		addByTest();
		
		int x = service.checkDuplicate("321-321-123-521");
	
		assertThat(x, is(x));
	}
	
	@Test
	public void checkPassword(){
		addByTest();
		
		int x = service.checkPassword(cards.get(0).getCardNumber(), cards.get(0).getCardPassword());
		
		assertThat(x, is(1));
	}
	
	@Test(expected = NotFoundCardExecption.class)
	public void failCheckPassword(){
		addByTest();
		
		int x = service.checkPassword(cards.get(0).getCardNumber(), "321321");
		
		assertThat(x, is(0));
	}
	
	@Test
	public void getAllByEamil(){
		addByTest();
		
		List<CardDto> getCard = service.getAllByEmail(cards.get(0).getEmail());
		
		for(int i=0; i<cards.size(); i++){
			validate(getCard.get(i), cards.get(i));
		}
	}
	
	public void addByTest(){
		for(CardDto card : cards){
			service.add(card);
		}
	}
	
	public void validate(CardDto getCard, CardDto card){
		assertThat(getCard.getCardNumber(), is(card.getCardNumber()));
		assertThat(getCard.getCardType(), is(card.getCardType()));
		assertThat(getCard.getCardPassword(), is(card.getCardPassword()));
		assertThat(getCard.getCardExpiryDate(), is(card.getCardExpiryDate()));	
		assertThat(getCard.getEmail(), is(card.getEmail()));	
	}
	

}
