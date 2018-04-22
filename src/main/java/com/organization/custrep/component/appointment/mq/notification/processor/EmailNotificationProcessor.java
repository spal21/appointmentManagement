package com.organization.custrep.component.appointment.mq.notification.processor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.NotificationXML;

@Component
public class EmailNotificationProcessor implements NotificationProcessor{

	@Override
	public void process(NotificationXML xml, LocalDateTime dispathTimeStamp) {
		System.out.println("EMail send to "+xml.getUserID()+" mail ID : "+xml.getEmailID()+" message : "+xml.getMessage());
	}
}
