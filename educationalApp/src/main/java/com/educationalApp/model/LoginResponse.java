package com.educationalApp.model;

public class LoginResponse extends BasicResponse {

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
