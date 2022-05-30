package com.osemdeveloper.BmtJdbc.exceptions;

@SuppressWarnings("serial")
public class NullAdminException extends RuntimeException {

	public NullAdminException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public NullAdminException(String arg0) {
		super(arg0);
		
	}

	
	
}
