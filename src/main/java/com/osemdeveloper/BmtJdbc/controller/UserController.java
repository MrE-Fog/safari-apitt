package com.osemdeveloper.BmtJdbc.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.osemdeveloper.BmtJdbc.domain.BookingDetails;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.domain.Passenger;
import com.osemdeveloper.BmtJdbc.domain.User;
import com.osemdeveloper.BmtJdbc.exceptions.UserAlreadyExistException;
import com.osemdeveloper.BmtJdbc.exceptions.UserValidationException;
import com.osemdeveloper.BmtJdbc.service.UserService;
import com.osemdeveloper.BmtJdbc.util.UserAuth;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user, Errors error) {

		if (error.hasErrors()) {
			throw new UserValidationException("invalid data provided");
		}
		try {
			User status = userService.addUser(user);

			if (status != null) {

				return new ResponseEntity<User>(status, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (UserAlreadyExistException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Integer userId) {

		User user = userService.getUser(userId);

		return ResponseEntity.ok(user);
	}

//	@GetMapping("/getUser/{email}")
//	public ResponseEntity<User> getUser(@PathVariable String email) {
//
//		User user = userService.getUser(email);
//
//		return ResponseEntity.ok(user);
//	}

	@PostMapping("/userLogin")
	public ResponseEntity<User> loginUser(@RequestBody UserAuth auth) {
		User user = userService.userLogin(auth);

		return ResponseEntity.ok().body(user);

	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);

		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@PostMapping("/updatePassenger")
	public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
		Passenger p = userService.addPassenger(passenger);
		return ResponseEntity.ok().body(p);
	}

//	@PostMapping("/updatePassenger")
//	public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
//		Passenger p = userService.updatePassenger(passenger);
//		return ResponseEntity.ok().body(p);
//	}

//	@GetMapping("/findBus/{arrivalDepot}/{departureDepot}/{departureDate_andTime}")
//	public ResponseEntity<BusDetails> findByRouteAndDate(@PathVariable String arrivalDepot,
//			@PathVariable String departureDepot, @PathVariable Timestamp departureDate_andTime) {
//
//		BusDetails details = userService.findByRouteAndDate(arrivalDepot, departureDepot,  departureDate_andTime);
//		System.err.println("ContRol"+details);
//		return ResponseEntity.ok().body(details);
//	}

	@GetMapping("/findBus/{arrivalDepot}/{departureDepot}")
	public ResponseEntity<BusDetails> findByRouteAndDate(@PathVariable String arrivalDepot,
			@PathVariable String departureDepot) {

		BusDetails details = userService.findByRouteAndDate(arrivalDepot, departureDepot);
		System.err.println("ContRol" + details);
		return ResponseEntity.ok().body(details);
	}

	@GetMapping("/getBusByNumber/{busNumber}")
	public ResponseEntity<BusDetails> getBusByNumber(@PathVariable Integer busNumber) {
		BusDetails details = userService.getBusByBusNumber(busNumber);

		return ResponseEntity.ok().body(details);
	}

	@PostMapping("/addBooking/{userId}/{busNumber}")
	public ResponseEntity<BookingDetails> addBooking(@RequestBody BookingDetails booking, @PathVariable Integer userId,
			@PathVariable Integer busNumber) {

//		System.err.println("C-booking"+booking);
//		System.err.println("C-userId"+userId);
//		System.err.println("C-busNumber"+busNumber);

		BookingDetails details = userService.addBooking(booking, userId, busNumber);
		System.err.println("C-details: :" + details);
		return ResponseEntity.ok().body(details);
	}
//
//	@GetMapping("/getBookingByUser/{userId}")
//	public ResponseEntity<List<BookingDetails>> getBookingByUser(@PathVariable Integer userId) {
//		List<BookingDetails> list = userService.getBookingByUserId(userId);
//
//		return ResponseEntity.ok().body(list);
//	}

	@GetMapping("/getBookingByUser/{userId}")
	public ResponseEntity<List<BookingDetails>> getBookingByUser(@PathVariable Integer userId) {
		List<BookingDetails> list = userService.getBookingByUserId(userId);
		
		System.err.println("CONTROLLER  :"+list);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/getBookingByUserBook/{bookingId}")
	public ResponseEntity<BookingDetails> getBookingByUserBookId(@PathVariable Integer bookingId) {
		BookingDetails list = userService.getBookingByBookingId(bookingId);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/getingData")
	public ResponseEntity<List<BusDetails>> gettingData() {
		List<BusDetails> list = userService.getData();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/getPassengerByBookingId/{bookingId}")
	public ResponseEntity<List<Passenger>> getPassengerByBookingId(@PathVariable Integer bookingId) {

		List<Passenger> list = userService.getPassengerByBookingId(bookingId);

		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/deleteBooking/{bookingId}")
	public void deleteBooking(@PathVariable Integer bookingId) {

		userService.deleteBookingDetails(bookingId);

	}

	@GetMapping("/report/product/{userId}/{busNumber}/{bookingId}")
	public ResponseEntity<Resource> getPdf(@PathVariable Integer userId, @PathVariable Integer busNumber,
			@PathVariable Integer bookingId) throws IOException, DocumentException {
		List<BookingDetails> products = userService.bookingPDF(userId);
		List<BusDetails> busDetails = userService.busPDF(busNumber);
		List<Passenger> passengerDetails = userService.passengerPDF(bookingId);
		List<User> userDetails = userService.userPDF(userId);

		Document document = new Document(PageSize.A4, 20, 20, 20, 20);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, os);

		document.open();

		Image img = Image.getInstance("C:\\Users\\shrinivas\\Downloads\\logo.jpg");
//		 img.setAbsolutePosition(450f, 10f);

		document.add(img);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(52);
		f.setColor(10, 120, 0);

		document.add(new Paragraph("       MAKE MY TRIP", f));

		Paragraph title = new Paragraph("                                                   BOOKING DETAILS",
				FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 25, 25)));

		document.add(title);

		Paragraph p2 = new Paragraph();
		p2.add("1. You can travel on e-ticket sent on SMS or take a Virtual Reservation Message (VRM) along with any one of the prescribed ID in original. Please do not print the ERS unless extremely necessary. This   Ticket will be valid with an ID proof in original. Please carry original identity proof. If found traveling without original ID proof, passenger will be treated as without ticket and charged as per extent Railway Rules. "
				+ ""

				+ "2. Only confirmed/RAC/Partially confirmed E-ticket is valid for travel. Fully Waitlisted E-ticket is invalid for travel if it remains fully waitlisted after preparation of chart and the refund of the booking amount shall be credited to the account used for payment for booking of the ticket. Travelling on a fully waitlisted e-ticket is illegal."
				+ ""

				+ " 3. Fully Waitlisted E-ticket is invalid for travel if it remains fully waitlisted after preparation of chart and the refund of the booking amount shall be credited to the account used for payment for booking of the ticket. Passengers travelling on a fully waitlisted e-ticket will be treated as Ticketless."
				+ ""

				+ " 4. Valid IDs to be presented during train journey by one of the passenger booked on an e-ticket :- Voter Identity Card / Passport / PAN Card / Driving License / Photo ID card issued by Central / State Govt / Public Sector Undertakings of State / Central Government ,District Administrations , Municipal bodies and Panchayat Administrations which are having serial number / Student Identity Card with photograph issued by recognized School or College for their students / Nationalized Bank Passbook with photograph / Credit Cards issued by Banks with laminated photograph/Unique Identification Card \"Aadhaar\", m-Aadhaar, e-Aadhaar. /Passenger showing the Aadhaar/Driving License from the   Issued Document\" section by logging into his/her Digi Locker account considered as valid proof of identity. (Documents uploaded by the user i.e. the document in \"Uploaded Document\" section will not be considered as a valid proof of identity)."
				+ ""

				+ " 5. Service Accounting Code (SAC) 996411: Local land transport services of passengers by railways for distance up to 150 KMs Service Accounting Code (SAC) 996416: Sightseeing transportation services by railways for Tourist Ticket Service Accounting Code (SAC) 996421: Long distance transport services of passengers through rail network by Railways for distance beyond 150 KMs "
				+ ""

				+ "6. While booking this ticket, you have agreed of having read the Health Protocol of Destination State of your travel. You are again advised to clearly read the Health Protocol advisory of destination state before start of your travel and follow them properly. "
				+ ""

				+ "7. General rules/ Information for e-ticket passenger have to be studied by the customer for cancellation & refund.\r\n"
				+ ""); // no alignment

		document.add(p2);

		PdfPTable table = new PdfPTable(5);
		table.setSpacingBefore(25);
		table.setSpacingAfter(25);

		PdfPCell c1 = new PdfPCell(new Phrase("BookingId"));
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("DATE AND TIME"));
		table.addCell(c2);

//		PdfPCell c3 = new PdfPCell(new Phrase("time"));
//		table.addCell(c3);

		PdfPCell c3 = new PdfPCell(new Phrase("total cost"));
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("bus no"));
		table.addCell(c4);

		PdfPCell c5 = new PdfPCell(new Phrase("owner id"));
		table.addCell(c5);

		for (BookingDetails product : products) {

			table.addCell(String.valueOf(product.getBookingId()));
//			table.addCell(product.getBookingDate());
//			table.addCell(String.valueOf(product.getBookingTime()));
			table.addCell(String.valueOf(product.getBookingDate_andTime()));
			table.addCell(String.valueOf(product.getTotalCost()));
			table.addCell(String.valueOf(product.getBusNumber()));
			table.addCell(String.valueOf(product.getOwnerId()));

			PdfPTable table1 = new PdfPTable(6);
			table1.setSpacingBefore(25);
			table1.setSpacingAfter(25);

			PdfPCell b6 = new PdfPCell(new Phrase("RegistrationNumber"));
			table1.addCell(b6);

			PdfPCell b7 = new PdfPCell(new Phrase("Boarding point"));
			table1.addCell(b7);

			PdfPCell b8 = new PdfPCell(new Phrase("Dropping Point"));
			table1.addCell(b8);

			PdfPCell b9 = new PdfPCell(new Phrase("DepartureDate"));
			table1.addCell(b9);

			PdfPCell b10 = new PdfPCell(new Phrase("ArrivalDate"));
			table1.addCell(b10);

//			PdfPCell b12 = new PdfPCell(new Phrase("ArrivalTime"));
//			table1.addCell(b12);
//
//			PdfPCell b13 = new PdfPCell(new Phrase("DepartureTime"));
//			table1.addCell(b13);

			PdfPCell b11 = new PdfPCell(new Phrase("BusVendor"));
			table1.addCell(b11);

			for (BusDetails bus : busDetails) {
				table1.addCell(String.valueOf(bus.getRegistrationNumber()));
				table1.addCell(String.valueOf(bus.getSourceDepot()));
				table1.addCell(String.valueOf(bus.getDestinationDepot()));
				table1.addCell(String.valueOf(bus.getDepartureDate_andTime()));
				table1.addCell(String.valueOf(bus.getArrivalDate_andTime()));
//				table1.addCell(String.valueOf(bus.getArrivalTime()));
//				table1.addCell(String.valueOf(bus.getDepartureTime()));
				table1.addCell(String.valueOf(bus.getBusVendor()));

				PdfPTable table2 = new PdfPTable(4);
				table2.setSpacingBefore(25);
				table2.setSpacingAfter(25);

				PdfPCell b12 = new PdfPCell(new Phrase("UserId"));
				table2.addCell(b12);

				PdfPCell b13 = new PdfPCell(new Phrase("User Name"));
				table2.addCell(b13);

				PdfPCell b14 = new PdfPCell(new Phrase("User Email"));
				table2.addCell(b14);

				PdfPCell b15 = new PdfPCell(new Phrase("Phone Number"));
				table2.addCell(b15);

				for (User user : userDetails) {
					table2.addCell(String.valueOf(user.getUserId()));
					table2.addCell(String.valueOf(user.getUserName()));
					table2.addCell(String.valueOf(user.getEmail()));
					table2.addCell(String.valueOf(user.getPhone()));

					PdfPTable table3 = new PdfPTable(9);
					table3.setSpacingBefore(25);
					table3.setSpacingAfter(25);

					PdfPCell b16 = new PdfPCell(new Phrase("name"));
					table3.addCell(b16);

					PdfPCell b17 = new PdfPCell(new Phrase("age"));
					table3.addCell(b17);

					PdfPCell b18 = new PdfPCell(new Phrase("Luggage"));
					table3.addCell(b18);

					for (Passenger pass : passengerDetails) {
						table3.addCell(String.valueOf(pass.getName()));
						table3.addCell(String.valueOf(pass.getAge()));
						table3.addCell(String.valueOf(pass.getLuggage()));

						document.add(table);
						document.add(table1);
						document.add(table2);
						document.add(table3);

						document.close();

						ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.parseMediaType("application/pdf"));
						headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
						headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductPdfReport.pdf");

						ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is),
								headers, HttpStatus.OK);

						return response;
					}
				}
			}
		}
		return null;

	}

}
