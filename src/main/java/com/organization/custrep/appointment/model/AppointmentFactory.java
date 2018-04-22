package com.organization.custrep.appointment.model;

import java.util.Date;

import com.organization.custrep.component.appointment.exception.BadDataException;

public class AppointmentFactory {

	public static CreateAppointment createAppointment(Date startDate, Date endDate,String userID, int numDays) throws BadDataException{
		CreateAppointment ca = new AppointmentCreateImpl(startDate, endDate, userID, numDays);
		ca.validateParams();
		return ca;
	}
	
	public static UpdateAppointment updateAppointment(Date startDate, Date endDate, Date oldStartDate, Date oldEndDate, String userID) throws BadDataException{
		UpdateAppointment ua = new AppointmentUpdateImpl(startDate, endDate, oldStartDate, oldEndDate, userID);
		ua.validateParams();
		return ua;
	}
	
	public static DeleteAppointment deleteAppointment(Date startDate, Date endDate, boolean cancelAll, boolean cancelForThisBlock, String userID) throws BadDataException{
		DeleteAppointment da = new AppointmentDeleteImpl(startDate, endDate, cancelAll, cancelForThisBlock, userID);
		da.validateParams();
		return da;
	}
}
