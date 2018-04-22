package com.organization.custrep.appointment.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.component.appointment.exception.BadDataException;
import com.organization.custrep.component.appointment.service.AppointmentService;

@Component
public class AppointmentManagerImpl implements AppointmentManager{

	@Autowired
	private AppointmentService appointmentService;

	@Override
	public List<AppointmentMessage> viewAppointment(String userID) throws BadDataException{
		try {
			return appointmentService.processFetchRequest(userID);
		}catch (Exception e) {
			throw new BadDataException("Exception encountered in viewAppointment for user "+userID+" : "+ e.getMessage());
		}
	}

	@Override
	public void updateAppointment(UpdateAppointment updateAppointment) throws BadDataException{
		try {
			appointmentService.processUpdateRequest(updateAppointment);
		}catch (Exception e) {
			throw new BadDataException("Exception encountered in updateAppointment for user "+updateAppointment.getUserID()+" : "+ e.getMessage());
		}
	}

	@Override
	public void createAppointment(CreateAppointment createAppointment) throws BadDataException{
		try {
			appointmentService.processInsertRequest(createAppointment);
		}catch (Exception e) {
			throw new BadDataException("Exception encountered in createAppointment for user "+createAppointment.getUserID()+" : "+ e.getMessage());
		}
	}

	@Override
	public void deleteAppointment(DeleteAppointment deleteAppointment) throws BadDataException{
		try {
			appointmentService.processDeleteRequest(deleteAppointment);
		}catch (Exception e) {
			throw new BadDataException("Exception encountered in deleteAppointment for user "+deleteAppointment.getUserID()+" : "+ e.getMessage());
		}
	}
}
