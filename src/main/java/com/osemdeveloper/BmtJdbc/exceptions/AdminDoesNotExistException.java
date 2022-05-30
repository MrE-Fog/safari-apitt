package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class AdminDoesNotExistException  extends RuntimeException {

	public AdminDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public AdminDoesNotExistException(String message) {
		super(message);

	}

}