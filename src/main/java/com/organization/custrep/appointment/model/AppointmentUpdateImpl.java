package com.organization.custrep.appointment.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.organization.custrep.component.appointment.util.DateAdapter;

@XmlRootElement(name = "UpdateAppointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentUpdateImpl extends AbstractUpdateAppointment implements UpdateAppointment{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date endDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date oldStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date oldEndDate;
	private String userID;
	
	public AppointmentUpdateImpl(){
		
	}
	
	AppointmentUpdateImpl(Date startDate, Date endDate, Date oldStartDate, Date oldEndDate, String userID) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.oldStartDate = oldStartDate;
		this.oldEndDate = oldEndDate;
		this.userID = userID;
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
	public Date getOldStartDate() {
		return oldStartDate;
	}

	@Override
	public Date getOldEndDate() {
		return oldEndDate;
	}

}
