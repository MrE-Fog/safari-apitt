package com.osemdeveloper.BmtJdbc.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BookingDetails  extends BusDetails{

	private Integer bookingId;
//	private String bookingDate;
//	private String bookingTime;
//	private Timestamp bookingDate_andTime;
	private LocalDateTime  bookingDate_andTime;
	private Double totalCost;
	private Integer busNumber;
	private Integer ownerId;
	private List<Passenger> passengers;
	
	

	
	

	

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

//	public String getBookingDate() {
//		return bookingDate;
//	}
//
//	public void setBookingDate(String bookingDate) {
//		this.bookingDate = bookingDate;
//	}
//
//	public String getBookingTime() {
//		return bookingTime;
//	}
//
//	public void setBookingTime(String bookingTime) {
//		this.bookingTime = bookingTime;
//	}
	
	

	public Double getTotalCost() {
		return totalCost;
	}

	

	public LocalDateTime getBookingDate_andTime() {
		return bookingDate_andTime;
	}

	public void setBookingDate_andTime(LocalDateTime bookingDate_andTime) {
		this.bookingDate_andTime = bookingDate_andTime;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		System.err.println("D-passengers"+passengers);
		this.passengers = passengers;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(bookingDate_andTime, bookingId, busNumber, ownerId, passengers, totalCost);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingDetails other = (BookingDetails) obj;
		return Objects.equals(bookingDate_andTime, other.bookingDate_andTime)
				&& Objects.equals(bookingId, other.bookingId) && Objects.equals(busNumber, other.busNumber)
				&& Objects.equals(ownerId, other.ownerId) && Objects.equals(passengers, other.passengers)
				&& Objects.equals(totalCost, other.totalCost);
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", bookingDate_andTime=" + bookingDate_andTime
				+ ", totalCost=" + totalCost + ", busNumber=" + busNumber + ", ownerId=" + ownerId + ", passengers="
				+ passengers + "]";
	}



}
