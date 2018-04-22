package com.organization.custrep.appointment.model;

import java.util.regex.Pattern;

import com.organization.custrep.component.appointment.exception.BadDataException;

public abstract class AbstractUser implements User {

	@Override
	public void validateParams() throws BadDataException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (getEmailID() == null)
			throw new BadDataException("Email ID is blank");
		if(!pat.matcher(getEmailID()).matches()){
			throw new BadDataException("Invalid Email ID");
		}
	}
}
