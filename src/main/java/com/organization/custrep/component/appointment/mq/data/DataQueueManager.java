package com.organization.custrep.component.appointment.mq.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataQueueManager {

	@Autowired
	private DataQueueReceiver queueReceiver;
	
	private DataQueue queue;
	
	@PostConstruct
	public void postProcessor(){
		queue = new DataQueue();
		Thread t1 = new Thread(queueReceiver);
		t1.start();
	}

	public DataQueueReceiver getQueueReceiver() {
		return queueReceiver;
	}

	public DataQueue getQueue() {
		return queue;
	}
}
