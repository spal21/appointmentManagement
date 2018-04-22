package com.organization.custrep.component.appointment.service;

import static com.organization.custrep.component.appointment.util.Utility.serializer;

import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.UserMessage;
import com.organization.custrep.appointment.model.CreateUserImpl;
import com.organization.custrep.appointment.model.DeleteUserImpl;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.UpdateUserImpl;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.db.UserDao;
import com.organization.custrep.component.appointment.exception.ServiceException;
import com.organization.custrep.component.appointment.mq.data.DataQueueManager;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private DataQueueManager queueManager;
	
	@Autowired
	private UserDao repository;

	@Override
	public UserMessage findUserByUserID(String userID) throws ServiceException{
		try {
			return repository.getUser(userID);
		} catch (SQLException e) {
			throw new ServiceException("SQLException encountered in findUserByUserID for user " + userID + " : "+ e.getMessage());
		}catch (Exception e) {
			throw new ServiceException("Exception encountered in findUserByUserID for user " + userID + " : "+ e.getMessage());
		}
	}

	@Override
	public void createUser(User userRequest) throws ServiceException{
		String req;
		try {
			req = serializer(CreateUserImpl.class,userRequest);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException("JAXBException encountered in createUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException("InterruptedException encountered in createUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}catch (Exception e) {
			throw new ServiceException("Exception encountered in createUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}
	}
	
	@Override
	public void updateUser(UpdateUser userRequest) throws ServiceException {
		String req;
		try {
			req = serializer(UpdateUserImpl.class,userRequest);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException("JAXBException encountered in updateUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		} catch (InterruptedException e) {
			throw new ServiceException("InterruptedException encountered in updateUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}catch (Exception e) {
			throw new ServiceException("Exception encountered in updateUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}
	}
	
	@Override
	public void deleteUser(User userRequest) throws ServiceException{
		String req;
		try {
			req = serializer(DeleteUserImpl.class,userRequest);
			queueManager.getQueue().getQueue().put(req);
		} catch (JAXBException e) {
			throw new ServiceException("JAXBException encountered in deleteUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}catch (Exception e) {
			throw new ServiceException("Exception encountered in deleteUser for user " + userRequest.getUserID() + " : "+ e.getMessage());
		}
		
	}
}
