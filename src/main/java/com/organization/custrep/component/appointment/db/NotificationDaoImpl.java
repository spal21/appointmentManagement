package com.organization.custrep.component.appointment.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.NotificationXML;

@Component
public class NotificationDaoImpl implements NotificationDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<NotificationXML> findAppointmentsForNotification(Date date) throws SQLException {
		try {
			return jdbcTemplate.query(
					"select u.userid,a.startDate,a.endDate,u.email from appointment a , user u where a.userID= u.userID  and a.startDate < ?",
					new Object[] { date }, new RowMapper<NotificationXML>() {

						@Override
						public NotificationXML mapRow(ResultSet rs, int rowNum) throws SQLException {
							String message = "You have an appointment scheduled between "+rs.getTimestamp("startDate").toString()+" to "+rs.getTimestamp("startDate").toString();
							return new NotificationXML(rs.getString("userid"), message, rs.getString("email"));
						}
					});
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in findAppointmentsForNotification  : " + e.getMessage());
		}
	}

}
