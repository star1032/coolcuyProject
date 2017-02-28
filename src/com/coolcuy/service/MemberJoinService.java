package com.coolcuy.service;

import com.coolcuy.dto.MemberJoinDto;

public interface MemberJoinService{
	public int add(MemberJoinDto memberJoinDto);
	public int delete(String element);
	public int deleteAll();
}
