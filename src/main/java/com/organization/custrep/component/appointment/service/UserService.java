package com.organization.custrep.component.appointment.service;

import com.organization.custrep.appointment.generated.messages.UserMessage;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.exception.ServiceException;

public interface UserService {

	UserMessage findUserByUserID(String userID) throws ServiceException;;

	void createUser(User userRequest) throws ServiceException;

	void updateUser(UpdateUser userRequest) throws ServiceException;

	void deleteUser(User userRequest) throws ServiceException;

}