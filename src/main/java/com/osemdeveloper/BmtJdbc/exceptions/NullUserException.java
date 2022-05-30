package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class NullUserException extends RuntimeException {

	public NullUserException(String message, Throwable throwable) {
		super(message,throwable);
		
	}

	public NullUserException(String message) {
		super(message);
		
	}
}
