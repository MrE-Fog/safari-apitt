package com.osemdeveloper.BmtJdbc.domain;

import java.util.Objects;

public class Passenger {
	
	private Integer passengerId;
	private String name;
	private Integer age;
	private Double luggage;
	private Integer bookingId;
	
	
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getLuggage() {
		return luggage;
	}
	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}
	
	
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, luggage, name, passengerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		return Objects.equals(age, other.age) && Objects.equals(luggage, other.luggage)
				&& Objects.equals(name, other.name) && Objects.equals(passengerId, other.passengerId);
	}
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", name=" + name + ", age=" + age + ", luggage=" + luggage
				+ ", bookingId=" + bookingId + "]";
	}
	
	
	
	
	
	
	
	
	

}
