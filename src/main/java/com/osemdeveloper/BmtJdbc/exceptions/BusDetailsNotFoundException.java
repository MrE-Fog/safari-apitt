package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class BusDetailsNotFoundException extends RuntimeException {
	/**
	 * @param message
	 * @param cause
	 */
	public BusDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusDetailsNotFoundException(String s) {
		super(s);
	}

}