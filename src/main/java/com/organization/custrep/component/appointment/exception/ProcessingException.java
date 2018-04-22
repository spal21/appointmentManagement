package com.organization.custrep.component.appointment.exception;

public class ProcessingException extends Exception{

	private static final long serialVersionUID = 1853332345156169271L;

	public ProcessingException() {
	}
	
	public ProcessingException(String message) {
		super(message);
	}
	
	public ProcessingException(Throwable throwable) {
		super(throwable);
	}
	
	public ProcessingException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public ProcessingException(String message,Throwable throwable, boolean enableSuppression,boolean writableStackTrace) {
		super(message,throwable,enableSuppression,writableStackTrace);
	}
}