package com.osemdeveloper.BmtJdbc.domain;

import java.util.Objects;

public class Admin {
	
	private Integer adminId;
	private String password;
	private String adminName;
	private String adminEmail;
	private String adminRole;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", password=" + password + ", adminName=" + adminName + ", adminEmail="
				+ adminEmail + ", adminRole=" + adminRole + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(adminEmail, adminId, adminName, adminRole, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adminEmail, other.adminEmail) && Objects.equals(adminId, other.adminId)
				&& Objects.equals(adminName, other.adminName) && Objects.equals(adminRole, other.adminRole)
				&& Objects.equals(password, other.password);
	}
	
	

}
