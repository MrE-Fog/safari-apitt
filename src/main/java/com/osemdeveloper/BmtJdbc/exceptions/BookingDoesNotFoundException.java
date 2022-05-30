package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class BookingDoesNotFoundException extends RuntimeException {

	public BookingDoesNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public BookingDoesNotFoundException(String arg0) {
		super(arg0);
		
	}
	
}