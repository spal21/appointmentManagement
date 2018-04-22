package com.organization.custrep.component.appointment.service.notification;

import com.organization.custrep.component.appointment.exception.ServiceException;

public interface NotificationService {

	void sendNotification(int numDays) throws ServiceException;
}
