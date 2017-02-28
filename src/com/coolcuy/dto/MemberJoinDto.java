package com.coolcuy.dto;

public class MemberJoinDto {
	private MemberDto memberDto;
	private CardDto cardDto;
	
	public MemberJoinDto(MemberDto memberDto, CardDto cardDto) {
		this.memberDto = memberDto;
		this.cardDto = cardDto;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public CardDto getCardDto() {
		return cardDto;
	}

	public void setCardDto(CardDto cardDto) {
		this.cardDto = cardDto;
	}

	@Override
	public String toString() {
		return memberDto.toString() +", "+ cardDto.toString();
	}
	
	
}
