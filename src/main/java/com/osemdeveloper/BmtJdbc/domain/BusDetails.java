package com.osemdeveloper.BmtJdbc.domain;

import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BusDetails extends User {

	private Integer busNumber;
	private String registrationNumber;
	private String sourceDepot;
	private String destinationDepot;
	private Integer avaliableSeats;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp departureDate_andTime;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp arrivalDate_andTime;

	private String busVendor;
	private Double price;
	private Integer isDeleted;

	public Integer getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getSourceDepot() {
		return sourceDepot;
	}

	public void setSourceDepot(String sourceDepot) {
		this.sourceDepot = sourceDepot;
	}

	public String getDestinationDepot() {
		return destinationDepot;
	}

	public void setDestinationDepot(String destinationDepot) {
		this.destinationDepot = destinationDepot;
	}

	public Integer getAvaliableSeats() {
		return avaliableSeats;
	}

	public void setAvaliableSeats(Integer avaliableSeats) {
		this.avaliableSeats = avaliableSeats;
	}

	public Timestamp getDepartureDate_andTime() {
		return departureDate_andTime;
	}

	public void setDepartureDate_andTime(Timestamp departureDate_andTime) {
		this.departureDate_andTime = departureDate_andTime;
	}

	public Timestamp getArrivalDate_andTime() {
		return arrivalDate_andTime;
	}

	public void setArrivalDate_andTime(Timestamp arrivalDate_andTime) {
		this.arrivalDate_andTime = arrivalDate_andTime;
	}

	public String getBusVendor() {
		return busVendor;
	}

	public void setBusVendor(String busVendor) {
		this.busVendor = busVendor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "BusDetails [busNumber=" + busNumber + ", registrationNumber=" + registrationNumber + ", sourceDepot="
				+ sourceDepot + ", destinationDepot=" + destinationDepot + ", avaliableSeats=" + avaliableSeats
				+ ", departureDate_andTime=" + departureDate_andTime + ", arrivalDate_andTime=" + arrivalDate_andTime
				+ ", busVendor=" + busVendor + ", price=" + price + ", isDeleted=" + isDeleted + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrivalDate_andTime, avaliableSeats, busNumber, busVendor, departureDate_andTime,
				destinationDepot, isDeleted, price, registrationNumber, sourceDepot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusDetails other = (BusDetails) obj;
		return Objects.equals(arrivalDate_andTime, other.arrivalDate_andTime)
				&& Objects.equals(avaliableSeats, other.avaliableSeats) && Objects.equals(busNumber, other.busNumber)
				&& Objects.equals(busVendor, other.busVendor)
				&& Objects.equals(departureDate_andTime, other.departureDate_andTime)
				&& Objects.equals(destinationDepot, other.destinationDepot)
				&& Objects.equals(isDeleted, other.isDeleted) && Objects.equals(price, other.price)
				&& Objects.equals(registrationNumber, other.registrationNumber)
				&& Objects.equals(sourceDepot, other.sourceDepot);
	}

}