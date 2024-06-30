package com.Amazon;

public class userDetails {
	String userName="";
	String userCountry="";
	
	public userDetails() {}
	public userDetails(String uname, String ucon) {
		this.userName = uname;
		this.userCountry = ucon;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserName(String uname) {
		this.userName = uname;
	}
	public void setUserCountry(String ucon) {
		this.userCountry = ucon;
	}
}
