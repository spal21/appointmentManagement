package com.organization.custrep.component.appointment.mq.notification;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue<T> {

	private BlockingQueue<T> queue;
	
	public NotificationQueue(){
		queue = new LinkedBlockingQueue<>();
	}

	public BlockingQueue<T> getQueue() {
		return queue;
	}
}
