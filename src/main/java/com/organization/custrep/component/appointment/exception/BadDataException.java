package com.organization.custrep.component.appointment.exception;

public class BadDataException extends Exception{

	private static final long serialVersionUID = 1853332345156169271L;

	public BadDataException() {
	}
	
	public BadDataException(String message) {
		super(message);
	}
	
	public BadDataException(Throwable throwable) {
		super(throwable);
	}
	
	public BadDataException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public BadDataException(String message,Throwable throwable, boolean enableSuppression,boolean writableStackTrace) {
		super(message,throwable,enableSuppression,writableStackTrace);
	}
}