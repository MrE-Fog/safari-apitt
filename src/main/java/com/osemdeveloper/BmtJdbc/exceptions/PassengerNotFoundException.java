package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class PassengerNotFoundException extends RuntimeException {

	public PassengerNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public PassengerNotFoundException(String arg0) {
		super(arg0);
		
	}
	
}