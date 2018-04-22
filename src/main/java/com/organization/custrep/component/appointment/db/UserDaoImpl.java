package com.organization.custrep.component.appointment.db;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.organization.custrep.appointment.generated.messages.UserMessage;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserMessage getUser(String userID) throws SQLException {
		try {
			List<UserMessage> userList = jdbcTemplate.query(
					"select userID, email,creationDate,lastUpdateDate  from user where userID=?",
					new Object[] { userID }, new UserRowMapper());
			return (userList != null && userList.size() > 0) ? userList.get(0) : null;
		} catch (Exception e) {
			throw new SQLException("Exception encountered in getUser for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int createUser(String userID, String email) throws SQLException {
		try {
			return jdbcTemplate.update(
					"insert into  user(userID,email,creationDate,lastUpdateDate) values (?,?,current_timestamp(),current_timestamp())",
					userID, email);
		} catch (Exception e) {
			throw new SQLException("Exception encountered in createUser for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int updateUser(String userID, String email) throws SQLException {
		try {
			return jdbcTemplate.update("update user set email = ? , lastUpdateDate = current_timestamp()"

					+ "where userID = ?", email, userID);
		} catch (Exception e) {
			throw new SQLException("Exception encountered in updateUser for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int deleteUser(String userID) throws SQLException {
		try {
			return jdbcTemplate.update("delete from user where userID = ?", userID);
		} catch (Exception e) {
			throw new SQLException("Exception encountered in deleteUser for user " + userID + " : " + e.getMessage());
		}
	}
}