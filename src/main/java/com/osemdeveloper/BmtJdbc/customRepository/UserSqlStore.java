package com.osemdeveloper.BmtJdbc.customRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osemdeveloper.BmtJdbc.domain.BookingDetails;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.Passenger;
import com.osemdeveloper.BmtJdbc.domain.User;

@Repository
public class UserSqlStore implements UserSqlRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/* Query to save the user */
	@Override
	public int save(User u) {
		return jdbcTemplate.update(
				"INSERT INTO user (userId ,userName, password, phone, email, userRole) VALUES (?, ?, ?,?,?,?)",
				new Object[] { u.getUserId(), u.getUserName(), u.getPassword(), u.getPhone(), u.getEmail(),
						u.getUserRole() });
	}

	@Override
	public int addPassenger(Passenger p) {
		return jdbcTemplate.update(
				"INSERT INTO passenger (passengerId, name, age, luggage , booking_id) VALUES (?,?,?,?,?) ",
				new Object[] { p.getPassengerId(), p.getName(), p.getAge(), p.getLuggage(), p.getBookingId() });
	}

//	/* Query to Update the user */
//	@Override
//	public int update(User e) {
//		return jdbcTemplate.update(
//				"UPDATE user SET userName = ?, password = ?, phone = ?,  userRole = ? WHERE userId = ?",
//				new Object[] { e.getUserName(), e.getPassword(), e.getPhone(), e.getUserRole(), e.getUserId() });
//
//	}

	/* Query to Update the user */
	@Override
	public int update(User e) {
		return jdbcTemplate.update("UPDATE user SET userName = ?, phone = ?,  userRole = ? WHERE userId = ?",
				new Object[] { e.getUserName(), e.getPhone(), e.getUserRole(), e.getUserId() });

	}

	@Override
	public int updatePassenger(Passenger pass) {
		return jdbcTemplate.update(
				"UPDATE passenger SET passengerId=?, name=?, age=?, luggage=?, booking_id=? WHERE passengerId=?",
				new Object[] { pass.getPassengerId(), pass.getName(), pass.getAge(), pass.getLuggage(),
						pass.getBookingId(), pass.getPassengerId() });
	}

	/* Query to find the user with mail */
	@Override
	public User findByUserEmail(String email) {

		User user = null;
		try {
			user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email=?",
					new BeanPropertyRowMapper<User>(User.class), email);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return user;
	}

	@Override
	public User findByUserId(Integer userId) {
		User user = null;
		try {
			user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE userId=?",
					new BeanPropertyRowMapper<User>(User.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;

	}

	/* Query to find the user with userId */
	@Override
	public User findById(Integer userId) {
		User user = null;
		try {
			user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE userId=?",
					new BeanPropertyRowMapper<User>(User.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return user;
	}

	@Override
	public Passenger findPassengerById(Integer passengerId) {
		Passenger passenger = null;
		try {
			passenger = jdbcTemplate.queryForObject("SELECT * FROM passenger WHERE passengerId=?",
					new BeanPropertyRowMapper<Passenger>(Passenger.class), passengerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return passenger;

	}

	@Override
	public BusDetails getBusByBusNumber(Integer busNumber) {
		BusDetails busDetails = null;
		try {
			busDetails = jdbcTemplate.queryForObject("SELECT * FROM bus_details WHERE busNumber=?",
					new BeanPropertyRowMapper<BusDetails>(BusDetails.class), busNumber);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return busDetails;

	}

//	@Override
//	public BookingDetails findOne(Integer bookingId) {
//		BookingDetails bookingDetails = null;
//		try {
//			bookingDetails = jdbcTemplate.queryForObject("SELECT * FROM booking_details WHERE booking_id=?",
//					new BeanPropertyRowMapper<BookingDetails>(BookingDetails.class), bookingId);
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
//		return bookingDetails;
//	}
	
	@Override
	public BookingDetails findOne(Integer bookingId) {
		BookingDetails bookingDetails = null;
		try {
			bookingDetails = jdbcTemplate.queryForObject("SELECT * FROM booking_details WHERE booking_id=?",
					new BeanPropertyRowMapper<BookingDetails>(BookingDetails.class), bookingId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return bookingDetails;
	}


	@Override
	public int savePassenger(Passenger p) {
		return jdbcTemplate.update("INSERT INTO passenger (passengerId, name, age, luggage) VALUES (?,?,?,?)",
				new Object[] { p.getPassengerId(), p.getName(), p.getAge(), p.getLuggage() });
	}

	@Override
	public int addBookingDetails(BookingDetails book) {

		return jdbcTemplate.update(
				"INSERT INTO booking_details (booking_id, booking_date_and_time, total_cost, bus_number,owner_id, userId) VALUES (?,?,?,?,?,?)",
				new Object[] { book.getBookingId(), book.getBookingDate_andTime(), book.getTotalCost(),
						book.getBusNumber(), book.getOwnerId(), book.getOwnerId() });

	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public List<BusDetails> findByRouteDate(String sourceDepot, String destinationDepot, Timestamp departureDate_andTime) {
//		String sql = "SELECT * FROM bus_details  WHERE   destinationDepot =?   and   sourceDepot=? and  departureDate_andTime  =? ";
//		List<BusDetails> busesOfRoute = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BusDetails.class),
//				sourceDepot, destinationDepot,  departureDate_andTime);
//		
//		System.err.println("Store"+busesOfRoute);
//
//		return busesOfRoute;
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BusDetails> findByRouteDate(String sourceDepot, String destinationDepot) {
		String sql = "SELECT * FROM bus_details  WHERE   destinationDepot =?   and   sourceDepot=? ";
		List<BusDetails> busesOfRoute = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BusDetails.class),
				sourceDepot, destinationDepot);
		
		System.err.println("Store"+busesOfRoute);

		return busesOfRoute;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Integer getSeat(Integer busNumber) {
		String sql = "select * from bus_details where  busNumber = ? ";

		@SuppressWarnings("rawtypes")
		List<BusDetails> seats = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BusDetails.class), busNumber);

		return seats.get(0).getAvaliableSeats();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusDetails> gettingData() {
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println("Today Date:: "+dtf.format(now));  
		String sql = "SELECT   sourceDepot ,destinationDepot ,departureDate_andTime FROM bus_details WHERE isDeleted is null and departureDate_andTime > now() and avaliableSeats BETWEEN '1' AND '56' ";

		@SuppressWarnings("rawtypes")
		List<BusDetails> dataa = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BusDetails.class));
		return dataa;

	}

	@Override
	public void updateSeats(Integer busNumber, Integer seatCount) {
		String SQL = "update bus_details b set avaliableSeats = ? where busNumber = ?";
		jdbcTemplate.update(SQL, seatCount, busNumber);

	}

//	@SuppressWarnings("rawtypes")
//	@Override
//	public List<BookingDetails> getBookingNew(Integer userId) {
//		String SQL = "SELECT * FROM booking_details WHERE userId=" + userId + "";
//
//		@SuppressWarnings("unchecked")
//		List<BookingDetails> bookingDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(BookingDetails.class));
//		return bookingDetails;
//	}
	
	
	//EXpe
	@SuppressWarnings("rawtypes")
	@Override
	public List<BookingDetails> getBookingNew(Integer userId) {
		String SQL = "SELECT * FROM booking_details WHERE userId=" + userId + "  " ;

		@SuppressWarnings("unchecked")
		List<BookingDetails> bookingDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(BookingDetails.class));
		System.err.println("QUERY :"+bookingDetails);
		
		return bookingDetails;
	}

	@Override
	public List<Passenger> getPassenger(Integer bookingId) {
		String SQL = "SELECT * FROM passenger WHERE booking_id=" + bookingId + "";

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Passenger> passengerDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Passenger.class));

		return passengerDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingDetails> getPDF(Integer userId) {
		String SQL = "SELECT * FROM booking_details WHERE userId=" + userId + "";
		@SuppressWarnings("rawtypes")
		List<BookingDetails> pdfDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Passenger.class));

		return pdfDetails;

	}

	@Override
	public void deleteBooking(Integer bookingId) {
		String deleteQuery = "delete from booking_details where booking_id = ?";
		jdbcTemplate.update(deleteQuery, bookingId);

	}

	@Override
	public List<BusDetails> getBusNew(Integer busNumber) {
		String SQL = "SELECT * FROM bus_details WHERE busNumber=" + busNumber + "";

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<BusDetails> busDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(BusDetails.class));
		return busDetails;
	}

	@Override
	public List<User> getUserNew(Integer userId) {
		String SQL = "SELECT * FROM user WHERE userId=" + userId + "";

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<User> userDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(User.class));
		return userDetails;
	}

	@Override
	public List<Passenger> getPassengerNew(Integer bookingId) {
		String SQL = "SELECT * FROM passenger WHERE booking_id=" + bookingId + "";

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Passenger> passengerDetails = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(Passenger.class));
		return passengerDetails;
	}

}
