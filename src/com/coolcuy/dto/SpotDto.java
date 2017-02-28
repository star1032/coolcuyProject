package com.coolcuy.dto;

public class SpotDto {
	private String spotName;
	private String password;
	private String position;
	private String openTime;
	private String closeTime;
	private String phoneNumber;
	private String regDate;
	private String area;
	private String zipCode;
	private String roadAddr;
	private String detailAddr;
	
	public SpotDto(){
		
	}
	
	public SpotDto(String spotName, String password, String position, 
			String openTime, String closeTime, String phoneNumber, String regDate,String area,String zipCode,String roadAddr,String detailAddr){
		this.password=password;
		this.position=position;
		this.spotName=spotName;
		this.openTime=openTime;
		this.closeTime=closeTime;
		this.phoneNumber=phoneNumber;
		this.regDate=regDate;
		this.area=area;
		this.zipCode=zipCode;
		this.roadAddr=roadAddr;
		this.detailAddr=detailAddr;
	}
	public SpotDto(String spotName, String password, String position, 
			String openTime, String closeTime, String phoneNumber,String area,String zipCode,String roadAddr,String detailAddr){		
		this.spotName=spotName;
		this.password=password;
		this.position=position;
		this.openTime=openTime;
		this.closeTime=closeTime;
		this.phoneNumber=phoneNumber;
		this.area=area;
		this.zipCode=zipCode;
		this.roadAddr=roadAddr;
		this.detailAddr=detailAddr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRoadAddr() {
		return roadAddr;
	}

	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

}
