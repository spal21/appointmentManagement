package com.organization.custrep.component.appointment.db;

import java.sql.SQLException;

import com.organization.custrep.appointment.generated.messages.UserMessage;

public interface UserDao {

	UserMessage getUser(String userID) throws SQLException;

	int createUser(String userID, String email) throws SQLException;

	int updateUser(String userID, String email) throws SQLException;

	int deleteUser(String userID) throws SQLException;
}