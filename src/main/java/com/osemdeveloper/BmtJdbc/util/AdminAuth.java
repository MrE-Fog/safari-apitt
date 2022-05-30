package com.osemdeveloper.BmtJdbc.util;

public class AdminAuth {
	
	private String adminEmail;
	private String password;

	public AdminAuth() {
		super();

	}

	public AdminAuth(String adminEmail, String password) {
		super();

		this.adminEmail = adminEmail;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

