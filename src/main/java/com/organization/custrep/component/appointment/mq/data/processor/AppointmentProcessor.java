package com.organization.custrep.component.appointment.mq.data.processor;

import java.util.Date;
import java.util.List;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.component.appointment.exception.ProcessingException;

public interface AppointmentProcessor {

	List<AppointmentMessage> findAppointmentByUserID(String userID) throws ProcessingException;

	int createAppointment(CreateAppointment appointmentRequest) throws ProcessingException;

	boolean findAppointment(String userID, Date startDate, Date endDate) throws ProcessingException;

	int updateAppointment(UpdateAppointment appointmentRequest) throws ProcessingException;

	int cancelAppointment(DeleteAppointment appointmentRequest) throws ProcessingException;

}