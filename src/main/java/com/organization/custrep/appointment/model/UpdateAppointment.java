package com.organization.custrep.appointment.model;

import java.util.Date;

public interface UpdateAppointment extends Appointment{

	Date getOldStartDate();
	Date getOldEndDate();
}
