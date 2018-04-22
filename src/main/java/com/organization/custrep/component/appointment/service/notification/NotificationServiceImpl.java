package com.organization.custrep.component.appointment.service.notification;

import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.NotificationXML;
import com.organization.custrep.component.appointment.db.NotificationDao;
import com.organization.custrep.component.appointment.exception.ServiceException;
import com.organization.custrep.component.appointment.mq.notification.NotificationQueueManager;

@Component
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao dao;
	
	@Autowired
	private NotificationQueueManager<NotificationXML> notificationQueueManager;
	
	@Override
	public void sendNotification(int numDays) throws ServiceException {
		Date date = Date.from(Instant.now().plus(numDays, ChronoUnit.DAYS));
		List<NotificationXML> list = null;
		try {
			list = dao.findAppointmentsForNotification(date);
			for(NotificationXML xml : list){
				notificationQueueManager.getQueue().getQueue().put(xml);
			}
		} catch (SQLException e) {
			throw new ServiceException("SQLException encountered in sendNotification : "+ e.getMessage());
		}catch (Exception e) {
			throw new ServiceException("Exception encountered in sendNotification : "+ e.getMessage());
		}
	}
}
