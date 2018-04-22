package com.organization.custrep.component.appointment.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;

public class AppointmentRowMapper implements RowMapper<AppointmentMessage>{
	@Override
	public AppointmentMessage mapRow(ResultSet rs, int arg1) throws SQLException {
		return new AppointmentMessage(rs.getString("id"),rs.getTimestamp("startDate"),
				rs.getTimestamp("creationDate"),rs.getTimestamp("lastUpdateDate"),
				rs.getTimestamp("endDate"),rs.getString("userid"));
	}
}
