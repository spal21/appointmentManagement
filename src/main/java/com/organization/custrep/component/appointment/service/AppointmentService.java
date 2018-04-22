package com.organization.custrep.component.appointment.service;

import java.util.List;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.component.appointment.exception.ServiceException;

public interface AppointmentService {

	List<AppointmentMessage> processFetchRequest(String userID) throws ServiceException;
		
	void processInsertRequest(CreateAppointment request) throws ServiceException;
	
	void processUpdateRequest(UpdateAppointment request) throws ServiceException;
	
	void processDeleteRequest(DeleteAppointment request) throws ServiceException;
}
