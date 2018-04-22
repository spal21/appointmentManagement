package com.organization.custrep.appointment.generated.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NotificationXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationXML {
	@XmlElement(name = "userID")
	private String userID;
	@XmlElement(name = "emailID")
	private String emailID;
	@XmlElement(name = "message")
	private String message;

	public NotificationXML() {

	}

	public NotificationXML(String userID, String message, String emailID) {
		this.userID = userID;
		this.message = message;
		this.emailID = emailID;
	}

	public String getUserID() {
		return userID;
	}

	public String getMessage() {
		return message;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
