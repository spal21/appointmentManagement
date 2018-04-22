package com.organization.custrep.appointment.model;

import java.util.Objects;

import com.organization.custrep.component.appointment.exception.BadDataException;

public abstract class AbstractUpdateAppointment extends AbstractAppointment implements UpdateAppointment {
	
	@Override
	public void validateParams() throws BadDataException {
		super.validateParams();
		if (Objects.nonNull(getOldStartDate()) && Objects.nonNull(getOldEndDate())) {
			if (getOldEndDate().toInstant().isBefore(getOldStartDate().toInstant())) {
				throw new BadDataException(" OldStartDate cannot be after OldEndDate");
			}
		} else {
			throw new BadDataException(" Params OldStartDate ,getOldEndDate are required");
		}
	}
}
