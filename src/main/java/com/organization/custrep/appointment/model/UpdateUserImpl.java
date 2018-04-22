package com.organization.custrep.appointment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UpdateUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateUserImpl extends AbstractUpdateUser implements UpdateUser{
	@XmlElement(name="userID")
	private String userID;
	@XmlElement(name="emailID")
	private String emailID;
	@XmlElement(name="oldEmailID")
	private String oldEmailID;
	
	public UpdateUserImpl(){
		
	}
	public UpdateUserImpl(String userID, String emailID, String oldEmailID) {
		this.userID = userID;
		this.emailID = emailID;
		this.oldEmailID = oldEmailID;
	}

	@Override
	public String getUserID() {
		return userID;
	}

	@Override
	public String getEmailID() {
		return emailID;
	}

	@Override
	public String getOldEmailID() {
		return oldEmailID;
	}
}
