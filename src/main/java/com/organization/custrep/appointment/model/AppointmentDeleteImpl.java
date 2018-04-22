package com.organization.custrep.appointment.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.organization.custrep.component.appointment.util.DateAdapter;

@XmlRootElement(name = "DeleteAppointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppointmentDeleteImpl extends AbstractDeleteAppointment implements DeleteAppointment{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date endDate;
	private String userID;
	private boolean cancelAll;
	private boolean cancelForThisBlock;
	
	public AppointmentDeleteImpl(){
		
	}
	
	AppointmentDeleteImpl(Date startDate, Date endDate, boolean cancelAll, boolean cancelForThisBlock, String userID) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.cancelAll = cancelAll;
		this.cancelForThisBlock = cancelForThisBlock;
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
	public boolean isCancelAll() {
		return cancelAll;
	}
	@Override
	public boolean isCancelForRange() {
		return cancelForThisBlock;
	}
}
