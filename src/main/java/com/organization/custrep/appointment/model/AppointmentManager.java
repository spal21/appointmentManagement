package com.organization.custrep.appointment.model;

import java.util.List;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.component.appointment.exception.BadDataException;

public interface AppointmentManager {
	
	/**
	 * Creates an appointment
	 * 
	 * @param createAppointment
	 * @throws BadDataException
	 */
	void createAppointment(CreateAppointment createAppointment) throws BadDataException;
	
	/**
	 * Fetches appointments for an User
	 * 
	 * @param userID
	 * @return List of Appointments
	 * @throws BadDataException
	 */
	List<AppointmentMessage> viewAppointment(String userID) throws BadDataException;
	
	/**
	 * Updates an existing appointment
	 * 
	 * @param updateAppointment
	 * @throws BadDataException
	 */
	void  updateAppointment(UpdateAppointment updateAppointment) throws BadDataException;
	
	/**
	 * Deletes an existing appointment
	 * 
	 * @param deleteAppointment
	 * @throws BadDataException
	 */
	void  deleteAppointment(DeleteAppointment deleteAppointment) throws BadDataException;
}
