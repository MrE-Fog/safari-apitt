package com.osemdeveloper.BmtJdbc.service;

import java.util.List;

import com.osemdeveloper.BmtJdbc.domain.BookingDetails;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.Passenger;
import com.osemdeveloper.BmtJdbc.domain.User;
import com.osemdeveloper.BmtJdbc.util.UserAuth;

public interface UserService {

	public User addUser(User user);

//	public NewsLetter addRequest(Integer requestId);

	//////
	public void updateUser(User user);

	public User getUser(String email);

	public List<User> pdfUser(String email);

	public User getUser(Integer userId);

	public User userLogin(UserAuth auth);

	public BookingDetails addBooking(BookingDetails booking, Integer userId, Integer busNumber);

	public void deleteBookingDetails(Integer bookingId);

	public Passenger addPassenger(Passenger passenger);

	/////////
	public Passenger updatePassenger(Passenger passenger);

//	public BusDetails findByRouteAndDate(String arrivalDepot, String departureDepot,  Timestamp departureDate_andTime);
	
	public BusDetails findByRouteAndDate(String arrivalDepot, String departureDepot);

	public BusDetails getBusByBusNumber(Integer busNumber);

	public List<BookingDetails> getBookingByUserId(Integer userId);

	public BookingDetails getBookingByBookingId(Integer bookingId);

	public List<Passenger> getPassengerByBookingId(Integer bookingId);

	public List<BusDetails> getData();

	public List<BookingDetails> bookingPDF(Integer userId);

	public List<BusDetails> busPDF(Integer busNumber);

	public List<User> userPDF(Integer userId);

	public List<Passenger> passengerPDF(Integer bookingId);

}
