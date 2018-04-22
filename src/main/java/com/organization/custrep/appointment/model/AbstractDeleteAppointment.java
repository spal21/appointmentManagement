package com.organization.custrep.appointment.model;

import com.organization.custrep.component.appointment.exception.BadDataException;

public abstract class AbstractDeleteAppointment extends AbstractAppointment implements DeleteAppointment {
	@Override
	public void validateParams() throws BadDataException {
		if(!isCancelAll()){
			super.validateParams();
		}
	}
}
