package com.coolcuy.dto;

public class SpotNameDto {
	private String spotArea;
	private String spotName;
	
	public SpotNameDto(String spotArea, String spotName) {
		this.spotArea = spotArea;
		this.spotName = spotName;
	}
	public String getSpotArea() {
		return spotArea;
	}
	public void setSpotArea(String spotArea) {
		this.spotArea = spotArea;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	
	
}
