package com.organization.custrep.component.appointment.mq.data.processor;

import com.organization.custrep.appointment.generated.messages.UserMessage;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.exception.ProcessingException;

public interface UserProcessor {

	UserMessage findUserByUserID(String userID) throws ProcessingException;

	int createUser(User userRequest) throws ProcessingException;

	int updateUser(UpdateUser userRequest) throws ProcessingException;

	int deleteUser(User userRequest) throws ProcessingException;

}