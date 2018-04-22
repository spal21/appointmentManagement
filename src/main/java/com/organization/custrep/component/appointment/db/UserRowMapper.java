package com.organization.custrep.component.appointment.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.organization.custrep.appointment.generated.messages.UserMessage;

public class UserRowMapper implements RowMapper<UserMessage> {
		@Override
		public UserMessage mapRow(ResultSet rs, int arg1) throws SQLException {
			return new UserMessage(rs.getString("userID"), rs.getString("email"),
					rs.getTimestamp("creationDate"), rs.getTimestamp("lastUpdateDate"));
		}
	}