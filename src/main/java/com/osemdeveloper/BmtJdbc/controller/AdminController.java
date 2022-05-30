package com.osemdeveloper.BmtJdbc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.exceptions.UserAlreadyExistException;
import com.osemdeveloper.BmtJdbc.exceptions.UserValidationException;
import com.osemdeveloper.BmtJdbc.service.AdminService;
import com.osemdeveloper.BmtJdbc.util.AdminAuth;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin, Errors error) {
		if (error.hasErrors()) {
			throw new UserValidationException("invalid data provided");
		}
		try {
			Admin status = adminService.addAdmin(admin);
			if (status != null) {
				return new ResponseEntity<Admin>(status, HttpStatus.OK);
			} else {
				return new ResponseEntity<Admin>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (UserAlreadyExistException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Admin>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@GetMapping("/getAdmin/{adminId}")
	public ResponseEntity<Admin> getUser(@PathVariable Integer adminId) {

		Admin admin = adminService.getAdmin(adminId);
		
		System.err.println("COntroller PPP"+ResponseEntity.ok(admin));

		return ResponseEntity.ok(admin);
	}

	@PostMapping("/adminLogin")
	public ResponseEntity<Admin> loginAdmin(@RequestBody AdminAuth auth) {
		Admin admin = adminService.adminLogin(auth);


		return ResponseEntity.ok().body(admin);

	}

	@PostMapping("/addBusDetails")
	public ResponseEntity<BusDetails> addBus(@RequestBody BusDetails busDetails) {
		
	


		BusDetails details = adminService.addBusDetails(busDetails);
		System.err.println("CONT"+details);

		return ResponseEntity.ok().body(details);
	}

	@GetMapping("/getAllBusDetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails(BusDetails details) {
		List<BusDetails> list = adminService.getAllBusDetails();
		
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/deleteBusDetails")
	public ResponseEntity<BusDetails> deleteBus(@RequestBody BusDetails busDtls) {
		adminService.deleteBusDetails(busDtls);

		return new ResponseEntity<BusDetails>(HttpStatus.OK);
	}
	
	

}
