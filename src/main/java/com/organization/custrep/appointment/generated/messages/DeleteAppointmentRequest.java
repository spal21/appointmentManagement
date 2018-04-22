package com.organization.custrep.appointment.generated.messages;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.organization.custrep.component.appointment.util.DateAdapter;

@XmlRootElement(name = "DeleteAppointmentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteAppointmentRequest {
	@XmlElement(name="userID")
	private String userID;
	@XmlElement(name="startDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date startDate;
	@XmlElement(name="endDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date endDate;
	@XmlElement(name="cancelAll")
	private boolean cancelAll;
	@XmlElement(name="cancelForThisBlock")
	private boolean cancelForThisBlock;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isCancelAll() {
		return cancelAll;
	}
	public void setCancelAll(boolean cancelAll) {
		this.cancelAll = cancelAll;
	}
	public boolean isCancelForThisBlock() {
		return cancelForThisBlock;
	}
	public void setCancelForThisBlock(boolean cancelForThisBlock) {
		this.cancelForThisBlock = cancelForThisBlock;
	}
}