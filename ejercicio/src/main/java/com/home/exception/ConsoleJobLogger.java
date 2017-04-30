package com.home.exception;

public class ConsoleJobLogger extends JobLoggerException{

	private static final long serialVersionUID = 1L;

	public ConsoleJobLogger() {
		super();
	}
	
	public ConsoleJobLogger(String message) {
		super(message);
	}
	
	public ConsoleJobLogger(String message, Throwable exception) {
		super(message, exception);
	}
}
