package com.organization.custrep.component.appointment.mq.data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DataQueue {

	private BlockingQueue<String> queue;
	
	public DataQueue(){
		queue = new LinkedBlockingQueue<>();
	}

	public BlockingQueue<String> getQueue() {
		return queue;
	}
}
