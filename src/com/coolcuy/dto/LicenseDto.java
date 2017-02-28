package com.coolcuy.dto;

public class LicenseDto {
	private String email;
	private String licenseNumber;
	private String licenseType;
	private String issuDate;
	private String expiryDate;
	private String gender;
	
	public LicenseDto() {}
	
	public LicenseDto(String email, String licenseNumber, String licenseType, String gender, String issuDate, String expiryDate) {
		this.email = email;
		this.licenseNumber = licenseNumber;
		this.licenseType = licenseType;
		this.gender = gender;
		this.issuDate = issuDate;
		this.expiryDate = expiryDate;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getIssuDate() {
		return issuDate;
	}

	public void setIssuDate(String issuDate) {
		this.issuDate = issuDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "LicenseDto [email=" + email + ", licenseType=" + licenseType + ", issuDate=" + issuDate
				+ ", expiryDate=" + expiryDate + ", gender=" + gender + "]";
	}
}
