package com.github.automation.utils;

public enum Password {

	USER1("Passw0rd1"),
	USER2("Passw9rd2");
	
	private String password;
	
	private Password(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
}
