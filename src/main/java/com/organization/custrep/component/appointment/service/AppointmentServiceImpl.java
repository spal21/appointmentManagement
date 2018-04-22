package com.organization.custrep.component.appointment.service;

import static com.organization.custrep.component.appointment.util.Utility.serializer;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.appointment.model.AppointmentCreateImpl;
import com.organization.custrep.appointment.model.AppointmentDeleteImpl;
import com.organization.custrep.appointment.model.AppointmentUpdateImpl;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.component.appointment.db.AppointmentDao;
import com.organization.custrep.component.appointment.exception.ServiceException;
import com.organization.custrep.component.appointment.mq.data.DataQueueManager;

@Component
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private DataQueueManager queueManager;

	@Autowired
	private AppointmentDao dao;

	@Override
	public List<AppointmentMessage> processFetchRequest(String userID) throws ServiceException {
		try {
			return dao.findAppointments(userID);
		} catch (Exception e) {
			throw new ServiceException("Exception encountered in processFetchRequest for user " + userID + " : "+ e.getMessage());
		}
	}

	@Override
	public void processInsertRequest(CreateAppointment request) throws ServiceException {
		String req;
		try {
			req = serializer(AppointmentCreateImpl.class, request);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException(
					"JAXBException encountered in processInsertRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException(
					"InterruptedException encountered in processInsertRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(
					"Exception encountered in processInsertRequest for user " + request.getUserID() + " : "+ e.getMessage());
		}
	}

	@Override
	public void processUpdateRequest(UpdateAppointment request) throws ServiceException {
		String req;
		try {
			req = serializer(AppointmentUpdateImpl.class, request);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException(
					"JAXBException encountered in processUpdateRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException(
					"InterruptedException encountered in processUpdateRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(
					"Exception encountered in processUpdateRequest for user " + request.getUserID() + " : "+ e.getMessage());
		}
	}

	@Override
	public void processDeleteRequest(DeleteAppointment request) throws ServiceException {
		String req;
		try {
			req = serializer(AppointmentDeleteImpl.class, request);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException(
					"JAXBException encountered in processDeleteRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException(
					"InterruptedException encountered in processDeleteRequest for user " + request.getUserID() + " : "+ e.getMessage());
		} catch (Exception e) {
			throw new ServiceException(
					"Exception encountered in processDeleteRequest for user " + request.getUserID() + " : "+ e.getMessage());
		}
	}
}
