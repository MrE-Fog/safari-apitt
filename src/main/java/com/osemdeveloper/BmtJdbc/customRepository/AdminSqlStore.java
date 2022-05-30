package com.osemdeveloper.BmtJdbc.customRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;

@Repository
public class AdminSqlStore implements AdminSqlRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int save(Admin a) {
		return jdbcTemplate.update(
				"INSERT INTO admin(adminId, password, adminName, adminEmail,adminRole) VALUES (?,?,?,?,?) ",
				new Object[] { a.getAdminId(), a.getPassword(), a.getAdminName(), a.getAdminEmail(),
						a.getAdminRole() });
	}

	@Override
	public int update(Admin a) {
		return jdbcTemplate.update(
				"UPDATE admin SET adminId=?, password=?, adminName=?, adminEmail=?, adminRole=? WHERE adminEmail=?",
				new Object[] { a.getAdminId(), a.getPassword(), a.getAdminName(), a.getAdminEmail(), a.getAdminRole(),
						a.getAdminEmail() });
	}

	/* Query to save the BusDetails */
	@Override
	public int saveBus(BusDetails b) {
		System.err.println( "SQLsave"+b.getBusNumber());
		return jdbcTemplate.update(
				"INSERT INTO bus_details (busNumber, registrationNumber, sourceDepot, destinationDepot,"
						+ "avaliableSeats, departureDate_andTime, arrivalDate_andTime,"
						+ "busVendor, price, isDeleted) VALUES (?,?,?,?,?,?,?,?,?,? )",
				new Object[] { b.getBusNumber(), b.getRegistrationNumber(), b.getSourceDepot(), b.getDestinationDepot(),
						b.getAvaliableSeats(), b.getDepartureDate_andTime(), b.getArrivalDate_andTime()
						, b.getBusVendor(), b.getPrice(), b.getIsDeleted() });

	}

	@Override
	public int updateBus(BusDetails b) {
		System.err.println( "SQLupdate"+b.getBusNumber());
		return jdbcTemplate.update(
				"UPDATE bus_details SET busNumber=?, registrationNumber=?, sourceDepot=?, destinationDepot=?,"
						+ "avaliableSeats=?, departureDate_andTime=?, arrivalDate_andTime=?, "
						+ "busVendor=?, price=?, isDeleted=? WHERE busNumber=?",
						new Object[] { b.getBusNumber(), b.getRegistrationNumber(), b.getSourceDepot(), b.getDestinationDepot(),
								b.getAvaliableSeats(), b.getDepartureDate_andTime(), b.getArrivalDate_andTime()
								, b.getBusVendor(), b.getPrice(), b.getIsDeleted(), b.getBusNumber() });
	}

	/* Query to List the BusDetails */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BusDetails> listBusesOn() {

		String sql = "SELECT * FROM bus_details where  isDeleted IS NULL ";

		List<BusDetails> buses = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BusDetails.class));

		return buses;

	}

	@Override
	public int deleteBus(Integer busNumber) {
		jdbcTemplate.update("UPDATE bus_details SET  isDeleted = 1 WHERE busNumber=?", busNumber);
		return busNumber;
	}

	/* Query to find the Admin with mailId */
	@Override
	public Admin findByAdminEmail(String adminEmail) {
		Admin admin = null;
		try {
			admin = jdbcTemplate.queryForObject("SELECT * FROM admin WHERE adminEmail=?",
					new BeanPropertyRowMapper<Admin>(Admin.class), adminEmail);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return admin;
	}

	/* Query to find the bus with the busNumber */
	@Override
	public BusDetails findById(Integer busNumber) {
		BusDetails busDetails = null;
		try {
			busDetails = jdbcTemplate.queryForObject("SELECT * FROM bus_details WHERE busNumber=?",
					new BeanPropertyRowMapper<BusDetails>(BusDetails.class), busNumber);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		return busDetails;
	}

	@Override
	public Admin findByAdminId(Integer adminId) {
		Admin admin = null;
		try {
			admin = jdbcTemplate.queryForObject("SELECT * FROM admin WHERE adminId=?",
					new BeanPropertyRowMapper<Admin>(Admin.class), adminId);
			System.err.println("Query PPPP"+admin);
			return admin;
		} 
		
		catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		

	}

}
