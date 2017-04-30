package com.home.exception;

public class JobLoggerException extends Exception{

	private static final long serialVersionUID = 1L;

	public JobLoggerException() {
		super();
	}
	
	public JobLoggerException(String message) {
		super(message);
	}
	
	public JobLoggerException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public JobLoggerException(Throwable exception) {
		super(exception);
	}
}
