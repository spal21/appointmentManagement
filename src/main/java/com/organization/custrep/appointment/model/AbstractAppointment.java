package com.organization.custrep.appointment.model;

import java.util.Objects;

import com.organization.custrep.component.appointment.exception.BadDataException;

public abstract class AbstractAppointment implements Appointment {
	
	@Override
	public void validateParams() throws BadDataException {
		if (Objects.nonNull(getStartDate()) && Objects.nonNull(getEndDate()) && Objects.nonNull(getUserID())) {
			if (getEndDate().toInstant().isBefore(getStartDate().toInstant())) {
				throw new BadDataException(" StartDate cannot be after EndDate");
			}
		} else {
			throw new BadDataException(" Params UserID,StartDate and EndDate are required");
		}
	}
}
