package com.osemdeveloper.BmtJdbc.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.osemdeveloper.BmtJdbc.customRepository.AdminSqlRepository;
import com.osemdeveloper.BmtJdbc.customRepository.UserSqlRepository;
import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BookingDetails;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.Passenger;
import com.osemdeveloper.BmtJdbc.domain.User;
import com.osemdeveloper.BmtJdbc.exceptions.BusDetailsNotFoundException;
import com.osemdeveloper.BmtJdbc.exceptions.NullBusDetailsException;
import com.osemdeveloper.BmtJdbc.exceptions.NullUserException;
import com.osemdeveloper.BmtJdbc.exceptions.PassengerNotFoundException;
import com.osemdeveloper.BmtJdbc.exceptions.UserAlreadyExistException;
import com.osemdeveloper.BmtJdbc.exceptions.UserDoesNotExistException;
import com.osemdeveloper.BmtJdbc.exceptions.UserValidationException;
import com.osemdeveloper.BmtJdbc.service.UserService;
import com.osemdeveloper.BmtJdbc.util.UserAuth;

@Service
public class UserServiceImpl implements UserService {

//	private  static int count = 56;

	@Autowired
	UserSqlRepository userSqlRepository;

	@Autowired
	AdminSqlRepository adminSqlRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User user) {
		int status = 0;
//		Integer userId = 0;
		if (user == null)
			throw new NullUserException("No Data Recieved");

		user.setUserRole("user");

		User isPresent = userSqlRepository.findByUserEmail(user.getEmail());

		if (user.getUserId() == null) {

			if (isPresent == null) {

				user.setUserRole("user");
				status = userSqlRepository.save(user);

				if (status > 0) {

					return userSqlRepository.findByUserEmail(user.getEmail());
				}
			} else {
				throw new UserAlreadyExistException("user already exists");
			}
		} else {

			status = userSqlRepository.update(user);
			if (status > 0) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User getUser(String email) {
		if (email == null)
			throw new NullUserException("No data recieved");
		User user = userSqlRepository.findByUserEmail(email);

		return user;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> pdfUser(String email) {
		User user = userSqlRepository.findByUserEmail(email);

		return (List<User>) user;
	}

	@Override
	public User getUser(Integer userId) {
		if (userId == null)
			throw new NullUserException("No data recieved");
		User user = userSqlRepository.findByUserId(userId);

		return user;

	}

	@Override
	public User userLogin(UserAuth auth) {
		if (auth == null) {
			throw new NullUserException("No data recieved");
		}

		User user = userSqlRepository.findByUserEmail(auth.getEmail());
		Admin admin = adminSqlRepository.findByAdminEmail(auth.getEmail());

		if (user != null) {
			if (user.getEmail().equalsIgnoreCase(auth.getEmail()) && user.getPassword().equals(auth.getPassword())) {
				return user;
			}

		}

		if (admin != null) {

			if (admin.getAdminEmail().equalsIgnoreCase(auth.getEmail())
					&& admin.getPassword().equals(auth.getPassword())) {

				User adminUser = new User();

				adminUser.setEmail(admin.getAdminEmail());
				adminUser.setUserId(admin.getAdminId());
				adminUser.setUserRole(admin.getAdminRole());
				;

				return adminUser;

			} else
				throw new UserValidationException("Invalid Admin Credentials");

		} else {
			throw new UserDoesNotExistException("No User Found=======================");
		}

	}

	@Override
	public Passenger addPassenger(Passenger passenger) {
		if (passenger == null) {
			throw new PassengerNotFoundException("no data provided");
		}
		Passenger oldPassenger = userSqlRepository.findPassengerById(passenger.getPassengerId());

		if (oldPassenger == null) {
			throw new PassengerNotFoundException("passenger not found");
		}

		userSqlRepository.addPassenger(passenger);

		return passenger;
	}

//	@Override
//	public BusDetails findByRouteAndDate(String arrivalDepot, String departureDepot, Timestamp departureDate_andTime) {
//		List<BusDetails> list = userSqlRepository.findByRouteDate(arrivalDepot.toLowerCase(),
//				departureDepot.toLowerCase(), departureDate_andTime);
//		System.err.println("Source :"+arrivalDepot);
//		System.err.println("Drop  :"+departureDepot);
//		System.err.println("date :"+departureDate_andTime);
//		for (BusDetails f : list) {
////			if (f.getDepartureDate().equals(date)) {
//			System.err.println("Impl"+f);
//
//			return f;
////			}
//		}
//		return null;
//	}
	
//	
	@Override
	public BusDetails findByRouteAndDate(String arrivalDepot, String departureDepot) {
		List<BusDetails> list = userSqlRepository.findByRouteDate(arrivalDepot.toLowerCase(),
				departureDepot.toLowerCase());
		System.err.println("Source :"+arrivalDepot);
		System.err.println("Drop  :"+departureDepot);

		for (BusDetails f : list) {
//			if (f.getDepartureDate().equals(date)) {
			System.err.println("Impl"+f);

			return f;
//			}
		}
		return null;
	}

	@Override
	public BusDetails getBusByBusNumber(Integer busNumber) {
		System.err.println("GetBus" + busNumber);
		if (busNumber == null) {
			throw new NullBusDetailsException("no data privided");
		}
		BusDetails details = userSqlRepository.getBusByBusNumber(busNumber);
		if (details == null) {
			throw new BusDetailsNotFoundException("no bus found for given number");
		}

		return details;
	}

	@Override
	public List<BusDetails> getData() {

		List<BusDetails> details = userSqlRepository.gettingData();

		return details;
	}

	@Override
	public List<BookingDetails> getBookingByUserId(Integer userId) {
//		BookingDetails user = userSqlRepository.getBookingNew(userId);
		if (userId == null) {
			throw new UserDoesNotExistException("user id not found");
		}

//		List<BookingDetails> list = user.getBookingDetails();
		
		System.err.println("SERVICE :"+(List<BookingDetails>) userSqlRepository.getBookingNew(userId));

		return (List<BookingDetails>) userSqlRepository.getBookingNew(userId);
	}
	
//	@Override
//	public List<BookingDetails> getBookingByUserId(Integer bookingId) {
////		BookingDetails user = userSqlRepository.getBookingNew(userId);
//		if (bookingId == null) {
//			throw new UserDoesNotExistException("user id not found");
//		}
//
////		List<BookingDetails> list = user.getBookingDetails();
//
//		return (List<BookingDetails>) userSqlRepository.getBookingNew(bookingId);
//	}


	@Override
	public List<Passenger> getPassengerByBookingId(Integer bookingId) {

		return (List<Passenger>) userSqlRepository.getPassenger(bookingId);
	}

	@Override
	public void updateUser(User user) {
		if (user == null)
			throw new NullUserException("No data recieved");
		User checkUser = userSqlRepository.findByUserId(user.getUserId());

		if (checkUser != null) {
			user.setUserRole("user");

			userSqlRepository.update(user);

		}

		else
			throw new UserDoesNotExistException("User not found");

	}

	@Override
	public BookingDetails addBooking(BookingDetails booking, Integer userId, Integer busNumber) {
		User user = userSqlRepository.findByUserId(userId);
		BusDetails bus = adminSqlRepository.findById(busNumber);

		if (user == null) {
			throw new UserDoesNotExistException("user id not found");
		}
		if (bus == null) {
			throw new BusDetailsNotFoundException("bus details not found");
		}

		
		Integer bookingId = (int) ((Math.random() * 9000) + 1000);
		booking.setBookingId(bookingId);

		booking.setOwnerId(userId);
		booking.setBusNumber(busNumber);
		
		System.err.println("S-userId"+userId);
		System.err.println("S-busNumber"+busNumber);

//		booking.setBookingDate(LocalDate.now().toString());
//		booking.setBookingTime(LocalTime.now().toString().substring(0, 5));
		//WRITTEN LIKE THIS 
//		booking.setBookingDate_andTime(booking.getBookingDate_andTime());
		
		booking.setBookingDate_andTime( LocalDateTime.now());
		
		
		
		booking.setTotalCost(bus.getPrice() * booking.getPassengers().size());

		List<BookingDetails> bookingList = user.getBookingDetails();
		bookingList.add(booking);
		user.setBookingDetails(bookingList);
		List<BookingDetails> list = user.getBookingDetails();
		userSqlRepository.addBookingDetails(list.get(0));
		for (Passenger p : list.get(0).getPassengers()) {
			p.setBookingId(bookingId);
			System.err.println("S-bookingId"+bookingId);
			userSqlRepository.addPassenger(p);
		}

		// updateUser(user);

		Integer seats = booking.getPassengers().size();
		BookingDetails status = userSqlRepository.findOne(bookingId);
		if (status != null) {
			
			Integer count = userSqlRepository.getSeat(busNumber);

			count -= seats;
			userSqlRepository.updateSeats(busNumber, count);
		}
		BookingDetails bDetails = userSqlRepository.findOne(bookingId);
	
		bDetails.setPassengers(list.get(0).getPassengers());
		System.err.println("S-bDetails"+bDetails);
		return bDetails;

	}

	@Override
	public void deleteBookingDetails(Integer bookingId) {
		userSqlRepository.deleteBooking(bookingId);

	}

	@Override
	public Passenger updatePassenger(Passenger passenger) {
		userSqlRepository.updatePassenger(passenger);
		return passenger;
	}

	@Override
	public List<BookingDetails> bookingPDF(Integer userId) {
		if (userId == null) {
			throw new UserDoesNotExistException("user id not found");
		}

//		List<BookingDetails> list = user.getBookingDetails();

		return (List<BookingDetails>) userSqlRepository.getBookingNew(userId);
	}

	@Override
	public List<BusDetails> busPDF(Integer busNumber) {
		return (List<BusDetails>) userSqlRepository.getBusNew(busNumber);
	}

	@Override
	public List<User> userPDF(Integer userId) {

		return (List<User>) userSqlRepository.getUserNew(userId);
	}

	@Override
	public List<Passenger> passengerPDF(Integer bookingId) {
		return (List<Passenger>) userSqlRepository.getPassengerNew(bookingId);
	}

	@Override
	public BookingDetails getBookingByBookingId(Integer bookingId) {
		return userSqlRepository.findOne(bookingId);
	}

}
