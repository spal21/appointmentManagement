package com.organization.custrep.component.appointment.mq.notification;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.NotificationXML;
import com.organization.custrep.component.appointment.exception.BadDataException;
import com.organization.custrep.component.appointment.mq.notification.processor.NotificationProcessor;

@Component()
public class NotificationQueueReceiver implements Runnable {

	@Autowired
	private NotificationQueueManager<NotificationXML> queueManager;
	@Autowired
	private NotificationProcessor notificationProcessor;

	@Override
	public void run() {
		NotificationXML content = null;
		while (true) {
			try {
				while ((content = queueManager.getQueue().getQueue().take()) != null) {
					try {
						process(content);
					} catch (BadDataException e) {
						e.printStackTrace();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void process(NotificationXML xml) throws BadDataException {
		notificationProcessor.process(xml, LocalDateTime.now());
	}
}