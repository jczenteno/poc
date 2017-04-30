package com.home.exception;

public class FileJobLogger extends JobLoggerException{

	private static final long serialVersionUID = 1L;

	public FileJobLogger() {
		super();
	}
	
	public FileJobLogger(String message) {
		super(message);
	}
	
	public FileJobLogger(String message, Throwable exception) {
		super(message, exception);
	}
}
