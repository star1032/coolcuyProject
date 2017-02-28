package com.coolcuy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.CardDto;
import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.MemberJoinDto;
import com.coolcuy.service.MemberJoinService;
import com.coolcuy.service.MemberJoinServiceImpl;
import com.coolcuy.service.MemberService;
import com.coolcuy.service.MemberServiceImpl;

public class MemberJoinServiceTest {
	List<MemberJoinDto> joins = new ArrayList<MemberJoinDto>();
	List<CardDto> cards = new ArrayList<CardDto>();
	List<MemberDto> members = new ArrayList<MemberDto>();
	MemberJoinService service = new MemberJoinServiceImpl();
	MemberService memberService = new MemberServiceImpl();
	
	@Before
	public void setup(){
		service.deleteAll();
		
		cards = Arrays.asList(
				new CardDto("111-456-7890-00", "대구은행", "10/11","851130","1234","a@naver.com"),
				new CardDto("222-456-7891-00", "농협", "10/11","851130","1234","b@naver.com"),
				new CardDto("333-456-7892-00", "우리은행", "10/11","851130","1234","c@naver.com")
				);
		
		members = Arrays.asList(
				new MemberDto("a@naver.com", "010-1111-1111", "조조", "1111111a", "11111", 
						"대구", "한국 아이티 교육원", "대구/경북", new LicenseDto("a@naver.com", "01-02-321555-11", "1종보통", "1",
						"2010-01-01", "2011-01-01")),
				new MemberDto("b@naver.com", "010-2222-2222", "유비", "2222222a", "22222", 
						"경산", "영대", "대구/경북", new LicenseDto("b@naver.com", "02-02-321555-12", "2종보통", "1",
						"2011-01-01", "2011-01-01")),
				new MemberDto("c@naver.com", "010-3333-3333", "손권", "3333333a", "33333", 
						"대전", "IBM", "부산/경남", new LicenseDto("c@naver.com", "03-02-321555-13", "1종보통", "2",
						"2015-05-05", "2011-01-01")),
				new MemberDto("d@naver.com", "010-4444-4444", "하후돈", "4444444a", "44444", 
						"서울", "IBM", "부산/경남", new LicenseDto("d@naver.com", "03-02-321555-13", "1종보통", "2",
						"2015-05-05", "2011-01-01")),
				new MemberDto("e@naver.com", "010-5555-5555", "관우", "5555555a", "55555", 
						"서울", "IBM", "부산/경남", new LicenseDto("e@naver.com", "03-02-321555-13", "1종보통", "2",
						"2010-05-05", "2011-01-01"))
		);
		
		joins = Arrays.asList(
					new MemberJoinDto(members.get(2), cards.get(2)),
					new MemberJoinDto(members.get(1), cards.get(1)),
					new MemberJoinDto(members.get(0), cards.get(0))
				);
	}
	
	@Test
	public void addAndGet(){
		addByTest();
	}
	
	public void addByTest(){
		for(MemberJoinDto join : joins){
			service.add(join);
		}
	}
}
