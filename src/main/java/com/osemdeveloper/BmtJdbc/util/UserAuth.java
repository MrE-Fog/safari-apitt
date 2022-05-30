package com.osemdeveloper.BmtJdbc.util;

public class UserAuth {
	

	private String email;
	private String password;

	public UserAuth() {
		super();

	}

	public UserAuth(String email, String password) {
		super();

		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
