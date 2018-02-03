package com.github.automation.utils;


public enum Username {
	USER1("testUserMG1"),
	USER2("testUserMG2");
	
	private String username;
	
	private Username(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	
}
