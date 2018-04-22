package com.organization.custrep.appointment.generated.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeleteUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteUserRequest {
	@XmlElement(name="userID")
	private String userID;
	@XmlElement(name="email")
	private String email;
	
	public DeleteUserRequest(){
	}
	public DeleteUserRequest(String userID, String email) {
		this.userID = userID;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
