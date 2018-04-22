package com.organization.custrep.appointment.generated.messages;

import java.util.Date;

public class ErrorResponseObject {
	private Date date;
	private String request;
	private String message;
	
	public ErrorResponseObject(Date date, String request, String message) {
		this.date = date;
		this.request = request;
		this.message = message;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
	
}
