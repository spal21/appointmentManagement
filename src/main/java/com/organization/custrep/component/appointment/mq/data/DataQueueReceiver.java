package com.organization.custrep.component.appointment.mq.data;

import static com.organization.custrep.component.appointment.util.Utility.deserializer;

import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.NotificationXML;
import com.organization.custrep.appointment.generated.messages.UserMessage;
import com.organization.custrep.appointment.model.AppointmentCreateImpl;
import com.organization.custrep.appointment.model.AppointmentDeleteImpl;
import com.organization.custrep.appointment.model.AppointmentUpdateImpl;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.CreateUserImpl;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.DeleteUserImpl;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.UpdateUserImpl;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.exception.ProcessingException;
import com.organization.custrep.component.appointment.mq.data.processor.AppointmentProcessor;
import com.organization.custrep.component.appointment.mq.data.processor.UserProcessor;
import com.organization.custrep.component.appointment.mq.notification.NotificationQueueManager;

@Component()
public class DataQueueReceiver implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(DataQueueReceiver.class.getName());
	
	@Autowired
	private DataQueueManager queueManager;
	@Autowired
	private AppointmentProcessor appointmentProcessor;
	@Autowired
	private UserProcessor userProcessor;
	@Autowired
	private NotificationQueueManager<NotificationXML> notificationQueueManager;

	@Override
	public void run() {
		String content = "";
		while (true) {
			try {
				while ((content = queueManager.getQueue().getQueue().take()) != null) {
					System.out.println(content);
					try {
						process(content);
					} catch (JAXBException e) {
						LOGGER.severe(" JAXBException encountered while processing Message : "+e.getMessage());
					} catch (InterruptedException e) {
						LOGGER.severe(" InterruptedException encountered while processing Message : "+e.getMessage());
					} catch (Exception e) {
						LOGGER.severe(" Exception encountered while processing Message : "+e.getMessage());
					}
				}
			} catch (InterruptedException e) {
				LOGGER.severe(" InterruptedException encountered while processing Message : "+e.getMessage());
			}

		}
	}

	private void process(String str) throws JAXBException, InterruptedException, ProcessingException {
		str = str.substring(56);
		NotificationXML notification = null;
		String emailID = null;
		String message = null;
		if (str.startsWith("<CreateAppointmentRequest")) {
			CreateAppointment req = (AppointmentCreateImpl) deserializer(AppointmentCreateImpl.class, str);
			UserMessage user = userProcessor.findUserByUserID(req.getUserID());
			if(user!=null){
				emailID = user.getEmailID();
			}
			appointmentProcessor.createAppointment(req);
			message = "Hi "+req.getUserID()+ ", Your appointment is scheduled for time slot : "+req.getStartDate().toString()+ " and "+req.getEndDate().toString() +" for the next "+ req.getNumDays()+" days";
			notification = new NotificationXML(req.getUserID(),message, emailID);
		} else if (str.startsWith("<UpdateAppointmentRequest")) {
			UpdateAppointment req = (AppointmentUpdateImpl) deserializer(AppointmentUpdateImpl.class, str);
			emailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			appointmentProcessor.updateAppointment(req);
			message = "Hi "+req.getUserID()+ ", Your appointment is changed from : "+req.getOldStartDate().toString()+ " - "+req.getOldEndDate().toString() +" to : "+req.getStartDate().toString()+ " - "+req.getEndDate().toString();
			notification = new NotificationXML(req.getUserID(),message, emailID);
		} else if (str.startsWith("<DeleteAppointmentRequest")) {
			DeleteAppointment req = (AppointmentDeleteImpl) deserializer(AppointmentDeleteImpl.class, str);
			emailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			appointmentProcessor.cancelAppointment(req);
			message = "Hi "+req.getUserID()+ ", Your appointment slot : "+req.getStartDate().toString()+ " and "+req.getEndDate().toString() +" is cancelled ";
			notification = new NotificationXML(req.getUserID(),message, emailID);
		} else if (str.startsWith("<CreateUserRequest")) {
			User req = (CreateUserImpl) deserializer(CreateUserImpl.class, str);
			userProcessor.createUser(req);
			message = "Hi "+req.getUserID()+ ",  User profile is created";
			emailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			notification = new NotificationXML(req.getUserID(), message,emailID);
		} else if (str.startsWith("<UpdateUserRequest")) {
			UpdateUser req = (UpdateUserImpl) deserializer(UpdateUserImpl.class, str);
			String oldemailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			userProcessor.updateUser(req);
			message = "Hi "+req.getUserID()+ ",  User profile is updated.";
			emailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			notification = new NotificationXML(req.getUserID(), message,
					oldemailID);
			notificationQueueManager.getQueue().getQueue().put(notification);
			notification = new NotificationXML(req.getUserID(), message,
					emailID);
		} else if (str.startsWith("<DeleteUserRequest")) {
			User req = (DeleteUserImpl) deserializer(DeleteUserImpl.class, str);
			emailID = userProcessor.findUserByUserID(req.getUserID()).getEmailID();
			userProcessor.deleteUser(req);
			message = "Hi "+req.getUserID()+ ",  User profile is deleted.";
			notification = new NotificationXML(req.getUserID(), message, emailID);
		} else {
			System.out.println("Unknown Message Type");
		}
		notificationQueueManager.getQueue().getQueue().put(notification);
	}
}