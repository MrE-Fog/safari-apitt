package com.osemdeveloper.BmtJdbc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osemdeveloper.BmtJdbc.customRepository.AdminSqlRepository;
import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.User;
import com.osemdeveloper.BmtJdbc.exceptions.NullBusDetailsException;
import com.osemdeveloper.BmtJdbc.exceptions.NullUserException;
import com.osemdeveloper.BmtJdbc.exceptions.UserAlreadyExistException;
import com.osemdeveloper.BmtJdbc.service.AdminService;
import com.osemdeveloper.BmtJdbc.service.UserService;
import com.osemdeveloper.BmtJdbc.util.AdminAuth;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminSqlRepository adminSqlRepository;
	
	@Autowired
	UserService userService;

	@Override
	public Admin addAdmin(Admin admin) {
		int status = 0;
		Integer adminId = 0;
		if (admin == null)
			throw new NullUserException("No Data Recieved");

		admin.setAdminRole("admin");

		Admin isPresent = adminSqlRepository.findByAdminEmail(admin.getAdminEmail());

		if (admin.getAdminId() == null) {

			if (isPresent == null) {

//				adminId = (int) ((Math.random() * 900) + 100);
				admin.setAdminId(adminId);
				admin.setAdminRole("admin");
				status = adminSqlRepository.save(admin);
				if (status > 0) {
//					return adminSqlRepository.findByAdminId(adminId);
					return adminSqlRepository.findByAdminEmail(admin.getAdminEmail());
				}
			} else {
				throw new UserAlreadyExistException("user already exists");
			}
		} else {

			status = adminSqlRepository.update(admin);
			if (status > 0) {
				return admin;
			}
		}
		return null;
	}

	@Override
	public BusDetails addBusDetails(BusDetails details) {
		
		if (details == null)
			throw new NullBusDetailsException("no data provided");
		System.err.println("Need"+details.getBusNumber());
		
		if (details.getBusNumber() == null) {
			Integer busNumber = (int) ((Math.random() * 9000) + 1000);

			details.setBusNumber(busNumber);
			System.err.println("SET"+busNumber);
			details.setAvaliableSeats(56);

			adminSqlRepository.saveBus(details);
		} else  {
			
			

			adminSqlRepository.updateBus(details);

		}

		return details;
	}

	@Override
	public List<BusDetails> getAllBusDetails() {

		return adminSqlRepository.listBusesOn();

	}

	@Override
	public Admin getAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullUserException("No data recieved");
		Admin admin = adminSqlRepository.findByAdminId(adminId);
		
		System.err.println("Service PPPP"+admin);
		return admin;

	}

	@Override
	public void deleteBusDetails(BusDetails busdetails) {

		adminSqlRepository.deleteBus(busdetails.getBusNumber());

	}

	@Override
	public Admin adminLogin(AdminAuth auth) {
		if (auth == null) {
			throw new NullUserException("No data recieved");
		}

		Admin admin = adminSqlRepository.findByAdminEmail(auth.getAdminEmail());

		if (admin != null) {
			if (admin.getAdminEmail().equalsIgnoreCase(auth.getAdminEmail())
					&& admin.getPassword().equals(auth.getPassword())) {

				Admin adminData = new Admin();
				User adminUser = new User();

				adminUser.setEmail(adminData.getAdminEmail());
				adminUser.setUserId(adminData.getAdminId());
				adminUser.setUserRole(adminData.getAdminRole());
				;

			}

		}
		return admin;

	}

}
