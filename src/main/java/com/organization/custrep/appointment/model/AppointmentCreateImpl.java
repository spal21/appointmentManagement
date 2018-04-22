package com.organization.custrep.appointment.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.organization.custrep.component.appointment.util.DateAdapter;

@XmlRootElement(name = "CreateAppointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentCreateImpl extends AbstractAppointment implements CreateAppointment{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date endDate;
	private int numDays;
	private String userID;
	
	public AppointmentCreateImpl(){
		
	}
	AppointmentCreateImpl(Date startDate, Date endDate,String userID, int numDays) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.userID = userID;
		this.numDays = numDays;
	}
	
	@Override
	public Date getStartDate() {
		return startDate;
	}
	@Override
	public Date getEndDate() {
		return endDate;
	}
	
	@Override
	public String getUserID() {
		return userID;
	}
	@Override
	public int getNumDays() {
		return numDays;
	}
	@Override
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
}
