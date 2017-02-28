package com.coolcuy.dto;

public class CarDto {
	private String carNumber;
	private int timeMoney;
	private int totalMoney;
	private double eventAppMoney;
	private String type;
	private String brand;
	private String seat;
	private String options;
	private String oilType;
	private String carName;
	private String yearModel;
	private String kilometer;
	private String regDate;
	private String babySeat;
	private String spotName;
	
	public CarDto(){}
	
	public CarDto(String carNumber, String brand, int totalMoney, String type,
			String seat, String options, String oilType, String carName, String yearModel, String kilometer, 
			String babySeat, String spotName){
		this.carNumber=carNumber;
		this.type=type;
		this.brand=brand;
		this.seat=seat;
		this.options=options;
		this.oilType=oilType;
		this.carName=carName;
		this.yearModel=yearModel;
		this.kilometer=kilometer;
		this.spotName=spotName;
		this.babySeat = babySeat;
	}
	
	public CarDto(String carNumber, String brand, int timeMoney, int totalMoney, String type,
			String seat, String options, String oilType, String carName, String yearModel, String kilometer, 
			String babySeat, String spotName){
		this.carNumber=carNumber;
		this.timeMoney=timeMoney;
		this.totalMoney = totalMoney;
		this.type=type;
		this.brand=brand;
		this.seat=seat;
		this.options=options;
		this.oilType=oilType;
		this.carName=carName;
		this.yearModel=yearModel;
		this.kilometer=kilometer;
		this.spotName=spotName;
		this.babySeat = babySeat;
	}
	
	public CarDto(String carNumber, String brand, int timeMoney, String type, String regDate,
			String seat, String options, String oilType, String carName, String yearModel, String kilometer, String spotName, String babySeat){
		this.carNumber=carNumber;
		this.timeMoney=timeMoney;
		this.type=type;
		this.regDate=regDate;
		this.brand=brand;
		this.seat=seat;
		this.options=options;
		this.oilType=oilType;
		this.carName=carName;
		this.yearModel=yearModel;
		this.kilometer=kilometer;
		this.spotName=spotName;
		this.babySeat = babySeat;
	}
	
	
	
	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getTimeMoney() {
		return timeMoney;
	}

	public void setTimeMoney(int timeMoney) {
		this.timeMoney = timeMoney;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getOilType() {
		return oilType;
	}

	public void setOilType(String oilType) {
		this.oilType = oilType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getYearModel() {
		return yearModel;
	}

	public void setYearModel(String yearModel) {
		this.yearModel = yearModel;
	}

	public String getKilometer() {
		return kilometer;
	}

	public void setKilometer(String kilometer) {
		this.kilometer = kilometer;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getBabySeat() {
		return babySeat;
	}

	public void setBabySeat(String babySeat) {
		this.babySeat = babySeat;
	}

	public double getEventAppMoney() {
		return eventAppMoney;
	}

	public void setEventAppMoney(double eventAppMoney) {
		this.eventAppMoney = eventAppMoney;
	}
}
