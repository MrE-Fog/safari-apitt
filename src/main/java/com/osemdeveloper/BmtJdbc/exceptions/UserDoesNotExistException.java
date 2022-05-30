package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class UserDoesNotExistException extends RuntimeException {

	public UserDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserDoesNotExistException(String message) {
		super(message);
		
	}

	
	
}