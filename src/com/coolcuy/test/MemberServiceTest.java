package com.coolcuy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.exception.DuplicateException;
import com.coolcuy.exception.NotFoundMemberExecption;
import com.coolcuy.service.CardService;
import com.coolcuy.service.CardServiceImpl;
import com.coolcuy.service.MemberServiceImpl;

public class MemberServiceTest {
	MemberServiceImpl service = new MemberServiceImpl();
	List<MemberDto> members = new ArrayList<MemberDto>();
	CardService cardService = new CardServiceImpl();
	
	@Before
	public void setUp() {
		cardService.deleteAll();
		service.deleteAll();
		
		members = Arrays.asList(
				new MemberDto("a@naver.com", "010-1111-1111", "조조", "q111111", "11111", 
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
	}

	@After
	public void end() {}
	
	@Test
	public void addAndGet(){
		for(MemberDto member : members){
			service.add(member);
		}
		
		for(int i=0; i<members.size(); i++){
			MemberDto getMember = service.get(members.get(i).getEmail());
			validate(getMember, members.get(i));
		}
	}
	
	@Test
	public void addAndDelete(){
		addByTest();
		
		service.delete(members.get(0).getEmail());
		
		int count = service.getCount();
		
		assertThat(count, is(members.size()-1));
	}
	
	@Test
	public void getAll(){
		addByTest();
		
		List<MemberDto> getMembers = service.getAll();
		
		for(int i=0; i<members.size(); i++){
			validate(members.get(i), getMembers.get(i));
		}
	}
		
	@Test(expected = DuplicateException.class)
	public void duplicate(){
		addByTest();
		service.checkDuplicate(members.get(0).getEmail());
	}
	
	public void addByTest(){
		for(MemberDto member : members){
			service.add(member);
		}
	}
	
	@Test
	public void update(){
		addByTest();
		
		MemberDto updateMember = new MemberDto("a@naver.com", "01011112222", "봉봉이", "1111", "12345", 
				"대구 신암동 해봉이동네", "한국 아이티 교육원", "대구, 서울", new LicenseDto("a@naver.com", "04-025005-11", "1종보통", "1",
				"2010-01-01", "2011-02-02"));
		
		MemberDto updatedMember = service.update(updateMember);
		
		validate(updatedMember, updateMember);
	}
	
	@Test
	public void checkPassword(){
		addByTest();
		
		int x = service.checkPassword(members.get(0).getEmail(), members.get(0).getPassword());
		assertThat(x, is(1));
	}
	
	@Test(expected = NotFoundMemberExecption.class)
	public void failCheckPassword(){
		addByTest();
		
		service.checkPassword(members.get(0).getEmail(), "잘못된비밀번호");
	}

	public void validate(MemberDto getMember, MemberDto member) {
		assertThat(getMember.getEmail(), is(member.getEmail()));
		assertThat(getMember.getPhoneNumber(), is(member.getPhoneNumber()));
		assertThat(getMember.getName(), is(member.getName()));
		assertThat(getMember.getPassword(), is(member.getPassword()));
		assertThat(getMember.getRating(), is(member.getRating()));
		assertThat(getMember.getZipCode().trim(), is(member.getZipCode()));
		assertThat(getMember.getRoadAddr(), is(member.getRoadAddr()));
		assertThat(getMember.getDetailAddr(), is(member.getDetailAddr()));
		
		LicenseDto license = member.getLicenseDto();
		LicenseDto getLicense = getMember.getLicenseDto();
		
//		System.out.println(license.getLicenseNumber());
		
		assertThat(getLicense.getEmail(), is(license.getEmail()));		
		assertThat(getLicense.getLicenseNumber(), is(license.getLicenseNumber()));		
		assertThat(getLicense.getLicenseType(), is(license.getLicenseType()));		
		assertThat(getLicense.getIssuDate(), is(license.getIssuDate()));
		assertThat(getLicense.getExpiryDate(), is(license.getExpiryDate()));
	}
}