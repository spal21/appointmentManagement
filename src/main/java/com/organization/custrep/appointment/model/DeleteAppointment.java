package com.organization.custrep.appointment.model;

public interface DeleteAppointment extends Appointment{

	boolean isCancelAll();
	boolean isCancelForRange();
}
