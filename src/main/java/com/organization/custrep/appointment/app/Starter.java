package com.organization.custrep.appointment.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.organization.custrep.appointment.app.web", "com.organization.custrep.component.appointment.service","com.organization.custrep.component.appointment.service.notification",
		"com.organization.custrep.component.appointment.db","com.organization.custrep.appointment.model","com.organization.custrep.component.appointment.mq","com.organization.custrep.component.appointment.mq.processor" })
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
