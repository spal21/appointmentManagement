package com.organization.custrep.appointment.model;

import com.organization.custrep.component.appointment.exception.BadDataException;

public interface Validable {
	void validateParams() throws BadDataException;
}
