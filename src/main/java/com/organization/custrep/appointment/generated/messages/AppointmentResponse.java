package com.organization.custrep.appointment.generated.messages;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentResponse implements Serializable {
	
	private static final long serialVersionUID = -6157785479280014482L;
	private String message;
	@XmlElementWrapper(name="appointmentList")
    @XmlElement(name="appointmentElement")
	private List<AppointmentMessage> appointments;
	
	public AppointmentResponse(String message, List<AppointmentMessage> appointments) {
		this.message = message;
		this.appointments = appointments;
	}
	
	public AppointmentResponse(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<AppointmentMessage> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<AppointmentMessage> appointments) {
		this.appointments = appointments;
	}

}
