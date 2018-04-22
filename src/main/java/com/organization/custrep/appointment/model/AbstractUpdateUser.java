package com.organization.custrep.appointment.model;

import java.util.Objects;

import com.organization.custrep.component.appointment.exception.BadDataException;

public abstract class AbstractUpdateUser extends AbstractUser implements UpdateUser {

	@Override
	public void validateParams() throws BadDataException {
		super.validateParams();
		if (Objects.nonNull(getOldEmailID())) {
			if (getOldEmailID().equals(getEmailID())) {
				throw new BadDataException(" OldEmailID cannot be same as  newEmailID");
			}
		} else {
			throw new BadDataException(" Params OldEmailID is required");
		}
	}
}