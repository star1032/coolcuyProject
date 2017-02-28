package com.coolcuy.charge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.PriceDto;

public class ChargeCalculator {
	public final static int DIV_HOUR = 3600000;
	
	private Map<String, PriceDto> priceMap;
	private List<CarDto> carList;
	private int rating;
	private Date sDate;
	private Date eDate;
	private long temp;
	private long weekendHour;
	private long weekHour;
	private long peakSeasonHour;
	private long standardHour;
	
	public ChargeCalculator() {}
	
	public ChargeCalculator(Map<String, PriceDto> priceMap, List<CarDto> carList, int rating, String startDate, String endDate){
		this.priceMap = priceMap;
		this.carList = carList;
		this.rating = rating;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		
		try {
			this.sDate = format.parse(startDate);
			this.eDate = format.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.temp = sDate.getTime();
	}
		
	public List<CarDto> operator(){
		calculatorHour();
		setUpCarPrice();
		
		return carList;
	}
	
	private void setUpCarPrice(){
		int weekendHour = (int)(this.weekendHour / DIV_HOUR);
		int weekHour = (int)(this.weekHour / DIV_HOUR);			
		int peakSeasonHour = (int)(this.peakSeasonHour / DIV_HOUR);
		int standardHour = (int)(this.standardHour / DIV_HOUR);
		int weekendCharge = 0;
		int weekCharge = 0;
		int peakSeasonCharge = 0;
		int standardCharge = 0;
		int totalPrice = 0;
		
		for(CarDto car : carList){
			PriceDto price = priceMap.get(car.getCarName());
			
			peakSeasonCharge = price.getPeakSeasonCharge() * peakSeasonHour;
			weekendCharge = price.getWeekEndCharge() * weekendHour;
			weekCharge = price.getWeekDayCharge() * weekHour;
			standardCharge = price.getStandardCharge() * standardHour; 
			
			totalPrice = weekendCharge + weekCharge + peakSeasonCharge + standardCharge;
			
			car.setTimeMoney(totalPrice / (weekHour + weekendHour + peakSeasonHour + standardHour));
			
			car.setTotalMoney(totalPrice);
		}
		
		
	}
		
	private void calculatorHour(){
		int hour = (60*60*1000);
		
		while(temp < eDate.getTime()){
			if(isPeakSeason()){
				peakSeasonHour += hour;
			}else if(rating <= 1){
				standardHour += hour;
			}else if(isWeekend()){
				weekendHour += hour;
			} else{
				weekHour += hour;
			}
			
			temp += hour;			
		}
		
	}
	
	private boolean isWeekend(){
		int dayOfWeek = getCalendar().get(Calendar.DAY_OF_WEEK);
		
		if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
			return true;
		}
		return false;
	}
		
	private boolean isPeakSeason(){
		int month = getCalendar().get(Calendar.MONTH);
		
		if((month+1) == 8 || (month+1) == 7){
			return true;
		}else{
			return false;
		}
	}

	private Calendar getCalendar(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(temp);
		
		return calendar;
	}
	
	public long getWeekendHour(){
		return (int)this.weekendHour;
	}
	
	public long getWeekHour(){
		return this.weekHour;
	}
}
