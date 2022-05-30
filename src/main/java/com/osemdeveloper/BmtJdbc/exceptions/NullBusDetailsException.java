package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class NullBusDetailsException extends RuntimeException {

	/**
	 * @param message
	 * @param cause
	 */
	public NullBusDetailsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NullBusDetailsException(String message) {
		super(message);
	}
	
}
