package com.organization.custrep.appointment.generated.messages;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement(name = "Appointment")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentMessage implements Serializable {

	private static final long serialVersionUID = -5002187932707677569L;
	private String appointmentID;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlSchemaType(name="datetime")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlSchemaType(name="datetime")
	private Date endDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlSchemaType(name="datetime")
	private Date creationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlSchemaType(name="datetime")
	private Date lastUpdateDate;
	private String userID;

	public AppointmentMessage() {

	}

	public AppointmentMessage(String appointmentID, Date startDate, Date creationDate, Date lastUpdateDate, Date endDate,String userID) {
		this.appointmentID = appointmentID;
		this.startDate = startDate;
		this.creationDate = creationDate;
		this.lastUpdateDate = lastUpdateDate;
		this.endDate = endDate;
		this.userID = userID;
	}

	public String getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}


	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}