package com.organization.custrep.appointment.model;

import java.util.Date;

public interface Appointment extends Validable {

	Date getStartDate();
	Date getEndDate();
	String getUserID();
}
