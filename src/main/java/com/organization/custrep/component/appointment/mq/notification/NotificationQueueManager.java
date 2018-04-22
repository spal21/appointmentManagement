package com.organization.custrep.component.appointment.mq.notification;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationQueueManager<NotificationXML> {

	@Autowired
	private NotificationQueueReceiver queueReceiver;
	
	private NotificationQueue<NotificationXML> queue;
	
	@PostConstruct
	public void postProcessor(){
		queue = new NotificationQueue<>();
		Thread t1 = new Thread(queueReceiver);
		t1.start();
	}

	public NotificationQueueReceiver getQueueReceiver() {
		return queueReceiver;
	}

	public NotificationQueue<NotificationXML> getQueue() {
		return queue;
	}
}
