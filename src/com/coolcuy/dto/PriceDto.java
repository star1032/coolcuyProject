package com.coolcuy.dto;

public class PriceDto {
	
	private String carType;
	private String carName;
	private int standardCharge;
	private int weekDayCharge;
	private int weekEndCharge;
	private int peakSeasonCharge;
	private String regDate;
	
	public PriceDto() {}

	public PriceDto(String carType, String carName, int standardCharge, int weekDayCharge, int weekEndCharge,
			int peakSeasonCharge, String regDate) {
		this.carType = carType;
		this.carName = carName;
		this.standardCharge = standardCharge;
		this.weekDayCharge = weekDayCharge;
		this.weekEndCharge = weekEndCharge;
		this.peakSeasonCharge = peakSeasonCharge;
		this.regDate = regDate;
	}

	public PriceDto(String carType, String carName, int standardCharge, int weekDayCharge, int weekEndCharge,
			int peakSeasonCharge) {
		this.carType = carType;
		this.carName = carName;
		this.standardCharge = standardCharge;
		this.weekDayCharge = weekDayCharge;
		this.weekEndCharge = weekEndCharge;
		this.peakSeasonCharge = peakSeasonCharge;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getStandardCharge() {
		return standardCharge;
	}

	public void setStandardCharge(int standardCharge) {
		this.standardCharge = standardCharge;
	}

	public int getWeekDayCharge() {
		return weekDayCharge;
	}

	public void setWeekDayCharge(int weekDayCharge) {
		this.weekDayCharge = weekDayCharge;
	}

	public int getWeekEndCharge() {
		return weekEndCharge;
	}

	public void setWeekEndCharge(int weekEndCharge) {
		this.weekEndCharge = weekEndCharge;
	}

	public int getPeakSeasonCharge() {
		return peakSeasonCharge;
	}

	public void setPeakSeasonCharge(int peakSeasonCharge) {
		this.peakSeasonCharge = peakSeasonCharge;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PriceListDto [carType=" + carType + ", carName=" + carName + ", standardCharge=" + standardCharge
				+ ", weekDayCharge=" + weekDayCharge + ", weekEndCharge=" + weekEndCharge + ", peakSeasonCharge="
				+ peakSeasonCharge + ", regDate=" + regDate + "]";
	}
	
}
