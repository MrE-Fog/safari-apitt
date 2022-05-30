package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class UserValidationException extends RuntimeException {

	public UserValidationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public UserValidationException(String arg0) {
		super(arg0);
		
	}

	
	
}