package com.osemdeveloper.BmtJdbc.customRepository;

import java.util.List;

import com.osemdeveloper.BmtJdbc.domain.BookingDetails;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.Passenger;
import com.osemdeveloper.BmtJdbc.domain.User;

public interface UserSqlRepository {

	public int save(User e);
	
//	public int newsLetterSave(NewsLetter n);

	public int update(User e);

	public User findByUserEmail(String email);

	public User findById(Integer userId);

	public int savePassenger(Passenger p);
	
	public int addPassenger(Passenger p);
	
	public int updatePassenger(Passenger pass);
	
	public Passenger findPassengerById(Integer userId);

	public BookingDetails findOne(Integer userId);

	public void deleteBooking(Integer bookingId);
	///////////
	
//	public int deleteBooking1(Integer bookingId);

//	public List<BusDetails> findByRouteDate(String sourceDepot, String destinationDepot, Timestamp departureDate_andTime);
	
	public List<BusDetails> findByRouteDate(String sourceDepot, String destinationDepot);
	

	public BusDetails getBusByBusNumber(Integer busNumber);

	public Integer getSeat(Integer busNumber);

	void updateSeats(Integer busNumber, Integer seatCount);
	
	public List<BookingDetails> getBookingNew(Integer bookingId);
	
	public List<BusDetails> getBusNew(Integer busNumber);
	
	public List<User> getUserNew(Integer userId);
	
	public List<Passenger> getPassengerNew(Integer bookingId);
	
	
	
	public List<Passenger> getPassenger(Integer bookingId);
	

	public User findByUserId(Integer userId);
	
	public int addBookingDetails (BookingDetails book );
	
	public List<BusDetails> gettingData( );
	
	public List<BookingDetails> getPDF(Integer userId);
	
	


}
