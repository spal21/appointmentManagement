package com.organization.custrep.component.appointment.mq.data.processor;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.UserMessage;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.db.UserDao;
import com.organization.custrep.component.appointment.exception.ProcessingException;

@Component
public class UserProcessorImpl implements UserProcessor {

	@Autowired
	private UserDao repository;

	@Override
	public UserMessage findUserByUserID(String userID) throws ProcessingException {
		try {
			return repository.getUser(userID);
		} catch (SQLException e) {
			throw new ProcessingException(
					"SQLException encountered in findUserByUserID for user " + userID + " : " + e.getMessage());
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in findUserByUserID for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int createUser(User userRequest) throws ProcessingException {
		try {
			return repository.createUser(userRequest.getUserID(), userRequest.getEmailID());
		} catch (SQLException e) {
			throw new ProcessingException("SQLException encountered in createUser for user " + userRequest.getUserID()
					+ " : " + e.getMessage());
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in createUser for user " + userRequest.getUserID() + " : " + e.getMessage());
		}
	}

	@Override
	public int updateUser(UpdateUser userRequest) throws ProcessingException {
		try {
			return repository.updateUser(userRequest.getUserID(), userRequest.getEmailID());
		} catch (SQLException e) {
			throw new ProcessingException("SQLException encountered in updateUser for user " + userRequest.getUserID()
					+ " : " + e.getMessage());
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in updateUser for user " + userRequest.getUserID() + " : " + e.getMessage());
		}
	}

	@Override
	public int deleteUser(User userRequest) throws ProcessingException {
		try {
			return repository.deleteUser(userRequest.getUserID());
		} catch (SQLException e) {
			throw new ProcessingException("SQLException encountered in deleteUser for user " + userRequest.getUserID()
					+ " : " + e.getMessage());
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in deleteUser for user " + userRequest.getUserID() + " : " + e.getMessage());
		}
	}
}
