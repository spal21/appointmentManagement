package com.organization.custrep.component.appointment.mq.notification.processor;

import java.time.LocalDateTime;

import com.organization.custrep.appointment.generated.messages.NotificationXML;

public interface NotificationProcessor {

	void process(NotificationXML xml,LocalDateTime dispathTimeStamp);
}
