package com.coolcuy.dto;

import java.sql.Date;

public class CouponDto {
	private String couponNumber;
	private String couponName;
	private Date issueStart;
	private Date issueEnd;
	private Date expiredStart;
	private Date expiredEnd;
	private String availableSpot;
	private String restriction;
	private String limitType;
	private String note;
	private String imageName;
	private String regDate;

	public CouponDto() {}

	public CouponDto(String couponNumber, String couponName, Date issueStart, Date issueEnd, Date expiredStart,
			Date expiredEnd, String availableSpot, String restriction, String limitType, String note, String imageName,
			String regDate) {
		this.couponNumber = couponNumber;
		this.couponName = couponName;
		this.issueStart = issueStart;
		this.issueEnd = issueEnd;
		this.expiredStart = expiredStart;
		this.expiredEnd = expiredEnd;
		this.availableSpot = availableSpot;
		this.restriction = restriction;
		this.limitType = limitType;
		this.note = note;
		this.imageName = imageName;
		this.regDate = regDate;
	}

	public CouponDto(String couponNumber, String couponName, Date issueStart, Date issueEnd, Date expiredStart,
			Date expiredEnd, String availableSpot, String restriction, String limitType, String note,
			String imageName) {
		this.couponNumber = couponNumber;
		this.couponName = couponName;
		this.issueStart = issueStart;
		this.issueEnd = issueEnd;
		this.expiredStart = expiredStart;
		this.expiredEnd = expiredEnd;
		this.availableSpot = availableSpot;
		this.restriction = restriction;
		this.limitType = limitType;
		this.note = note;
		this.imageName = imageName;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Date getIssueStart() {
		return issueStart;
	}

	public void setIssueStart(Date issueStart) {
		this.issueStart = issueStart;
	}

	public Date getIssueEnd() {
		return issueEnd;
	}

	public void setIssueEnd(Date issueEnd) {
		this.issueEnd = issueEnd;
	}

	public Date getExpiredStart() {
		return expiredStart;
	}

	public void setExpiredStart(Date expiredStart) {
		this.expiredStart = expiredStart;
	}

	public Date getExpiredEnd() {
		return expiredEnd;
	}

	public void setExpiredEnd(Date expiredEnd) {
		this.expiredEnd = expiredEnd;
	}

	public String getAvailableSpot() {
		return availableSpot;
	}

	public void setAvailableSpot(String availableSpot) {
		this.availableSpot = availableSpot;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CouponDto [couponNumber=" + couponNumber + ", couponName=" + couponName + ", issueStart=" + issueStart
				+ ", issueEnd=" + issueEnd + ", expiredStart=" + expiredStart + ", expiredEnd=" + expiredEnd
				+ ", availableSpot=" + availableSpot + ", restriction=" + restriction + ", limitType=" + limitType
				+ ", note=" + note + ", imageName=" + imageName + ", regDate=" + regDate + "]";
	}
	

}
