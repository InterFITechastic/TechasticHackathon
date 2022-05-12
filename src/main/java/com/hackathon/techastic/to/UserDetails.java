package com.hackathon.techastic.to;

public class UserDetails {

	private double dunsNum;
	private String dunsName;
	private String county;
	private String streetAddress;
	private String city;
	private String state;
	private double zip;
	private double phone;
	private String executiveContact1;
	private String executiveContact2;
	private boolean isWomanOwned;
	private String MinorityOwnedDesc;
	
	
	public double getDunsNum() {
		return dunsNum;
	}


	public void setDunsNum(double dunsNum) {
		this.dunsNum = dunsNum;
	}


	public String getDunsName() {
		return dunsName;
	}


	public void setDunsName(String dunsName) {
		this.dunsName = dunsName;
	}


	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public double getZip() {
		return zip;
	}


	public void setZip(double zip) {
		this.zip = zip;
	}


	public double getPhone() {
		return phone;
	}


	public void setPhone(double phone) {
		this.phone = phone;
	}


	public String getExecutiveContact1() {
		return executiveContact1;
	}


	public void setExecutiveContact1(String executiveContact1) {
		this.executiveContact1 = executiveContact1;
	}


	public String getExecutiveContact2() {
		return executiveContact2;
	}


	public void setExecutiveContact2(String executiveContact2) {
		this.executiveContact2 = executiveContact2;
	}


	public boolean isWomanOwned() {
		return isWomanOwned;
	}


	public void setWomanOwned(boolean isWomanOwned) {
		this.isWomanOwned = isWomanOwned;
	}


	public String getMinorityOwnedDesc() {
		return MinorityOwnedDesc;
	}


	public void setMinorityOwnedDesc(String minorityOwnedDesc) {
		MinorityOwnedDesc = minorityOwnedDesc;
	}


	@Override
	public String toString() {
		return "dunsName: "+dunsName+" City: "+city+" state: "+state+" ExecutiveContact1: "+executiveContact1; 
	}
	
}
