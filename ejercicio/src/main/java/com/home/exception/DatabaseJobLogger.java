package com.home.exception;

public class DatabaseJobLogger extends JobLoggerException{

	private static final long serialVersionUID = 1L;

	public DatabaseJobLogger() {
		super();
	}
	
	public DatabaseJobLogger(String message) {
		super(message);
	}
	
	public DatabaseJobLogger(String message, Throwable exception) {
		super(message, exception);
	}
}
