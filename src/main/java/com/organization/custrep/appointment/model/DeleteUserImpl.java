package com.organization.custrep.appointment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeleteUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteUserImpl extends AbstractUser{
	@XmlElement(name="userID")
	private String userID;
	@XmlElement(name="email")
	private String emailID;
	
	public DeleteUserImpl(){
		
	}
	public DeleteUserImpl(String userID, String emailID) {
		this.userID = userID;
		this.emailID = emailID;
	}

	@Override
	public String getUserID() {
		return userID;
	}

	@Override
	public String getEmailID() {
		return emailID;
	}
}
