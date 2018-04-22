package com.organization.custrep.appointment.generated.messages;

import java.io.Serializable;

public class UserResponse  implements Serializable{

	private static final long serialVersionUID = 459242792124556148L;
	private String message;
	private UserMessage user;
	
	public UserResponse(String message, UserMessage user) {
		this.message = message;
		this.user = user;
	}

	public UserResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserMessage getUser() {
		return user;
	}
	public void setUser(UserMessage user) {
		this.user = user;
	}
}
