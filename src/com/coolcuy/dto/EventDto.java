package com.coolcuy.dto;

import java.sql.Date;

public class EventDto {
	private String eventNumber;
	private String eventName;
	private Date eventNoticeDay;
	private Date eventStart;
	private Date eventEnd;
	private String availableSpot;
	private String saleName;
	private String restriction;
	private String note;
	private String imageName;
	private String limitType;
	private String regDate;
	
	
	public EventDto() {}
	
	public EventDto(String limitType) {this.limitType = limitType;}
	
	public EventDto(String eventNumber, String eventName, Date eventNoticeDay, Date eventStart, Date eventEnd,
			String availableSpot, String saleName, String restriction, String note, String imageName, String limitType,
			String regDate) {
		this.eventNumber = eventNumber;
		this.eventName = eventName;
		this.eventNoticeDay = eventNoticeDay;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.availableSpot = availableSpot;
		this.saleName = saleName;
		this.restriction = restriction;
		this.note = note;
		this.imageName = imageName;
		this.limitType = limitType;
		this.regDate = regDate;
	}

	public EventDto(String eventNumber, String eventName, Date eventNoticeDay, Date eventStart, Date eventEnd,
			String availableSpot, String saleName, String restriction, String note, String imageName,
			String limitType) {
		this.eventNumber = eventNumber;
		this.eventName = eventName;
		this.eventNoticeDay = eventNoticeDay;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.availableSpot = availableSpot;
		this.saleName = saleName;
		this.restriction = restriction;
		this.note = note;
		this.imageName = imageName;
		this.limitType = limitType;
	}

	public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventNoticeDay() {
		return eventNoticeDay;
	}

	public void setEventNoticeDay(Date eventNoticeDay) {
		this.eventNoticeDay = eventNoticeDay;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getAvailableSpot() {
		return availableSpot;
	}

	public void setAvailableSpot(String availableSpot) {
		this.availableSpot = availableSpot;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "EventDto [eventNumber=" + eventNumber + ", eventName=" + eventName + ", eventNoticeDay="
				+ eventNoticeDay + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + ", availableSpot="
				+ availableSpot + ", saleName=" + saleName + ", restriction=" + restriction + ", note=" + note
				+ ", imageName=" + imageName + ", limitType=" + limitType + ", regDate=" + regDate + "]";
	}
	
}
