package com.organization.custrep.appointment.generated.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UpdateUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateUserRequest {
	@XmlElement(name="userID")
	private String userID;
	@XmlElement(name="email")
	private String email;
	
	@XmlElement(name="oldEmail")
	private String oldEmail;
	
	public UpdateUserRequest(){
	}
	public UpdateUserRequest(String userID, String email,  String oldEmail) {
		this.userID = userID;
		this.email = email;
		this.oldEmail = oldEmail;
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
	public String getOldEmail() {
		return oldEmail;
	}
	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
}
