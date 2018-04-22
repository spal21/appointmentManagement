package com.organization.custrep.component.appointment.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AppointmentMessage> findAppointments(String userID) throws SQLException {
		try {
			return jdbcTemplate.query(
					"select a.id,a.startDate,a.endDate,a.creationDate,a.lastUpdateDate,a.userID"
							+ ",u.userid, u.email,u.creationDate as ucreationDate,u.lastUpdateDate as ulastUpdateDate from appointment a , user u where a.userID= u.userID  and u.userID=?",
					new Object[] { userID }, new AppointmentRowMapper());
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in findAppointments for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public List<AppointmentMessage> findAppointmentsInRange(String userID, Date startDate, Date endDate)
			throws SQLException {
		try {
			return jdbcTemplate.query(
					"select a.id,a.startDate,a.endDate,a.creationDate,a.lastUpdateDate,a.userID"
							+ ",u.userid, u.email,u.creationDate as ucreationDate,u.lastUpdateDate as ulastUpdateDate from appointment a , user u where a.userID= u.userID and u.userID=? and a.startDate >= ? and a.endDate <= ?",
					new Object[] { userID, startDate, endDate }, new AppointmentRowMapper());
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in findAppointmentsInRange for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int createAppointment(Date startDate, Date endDate, String userID) throws SQLException {
		try {
			return jdbcTemplate.update(
					"insert into  appointment(startDate,endDate,creationDate,lastUpdateDate,userID) values (?,?,current_timestamp(),current_timestamp(),?)",
					startDate, endDate, userID);
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in createAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public boolean findAppointment(String userID, Date startDate, Date endDate) throws SQLException {
		try {
			AppointmentMessage appointment = getAppointment(userID, startDate, endDate);

			return (appointment != null) ? true : false;
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in findAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public AppointmentMessage getAppointment(String userID, Date startDate, Date endDate) throws SQLException {
		try {

			List<AppointmentMessage> appointments = getAppointments(userID, startDate, endDate);
			return (appointments != null && appointments.size() > 0) ? appointments.get(0) : null;
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in getAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public List<AppointmentMessage> getAppointments(String userID, Date startDate, Date endDate) throws SQLException {
		try {

			List<AppointmentMessage> appointments = jdbcTemplate.query(
					"select * from appointment where userID=? and startDate=? and endDate=?",
					new Object[] { userID, startDate, endDate }, new AppointmentRowMapper());
			return appointments;
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in getAppointments for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int updateAppointment(Date oldStartDate, Date oldEndDate, Date newStartDate, Date newEndDate, String userID)
			throws SQLException {
		try {
			return jdbcTemplate
					.update("update appointment set startDate = ?,endDate = ?, lastUpdateDate = current_timestamp() "

							+ "where userID = ? and startDate=? and endDate = ?", newStartDate, newEndDate, userID,
							oldStartDate, oldEndDate);
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in updateAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int deleteAppointment(Date startDate, Date endDate, String userID) throws SQLException {
		try {
			return jdbcTemplate.update("delete from appointment where userID = ? and startDate = ? and endDate = ?",
					userID, startDate, endDate);
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in deleteAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int deleteAllAppointment(String userID) throws SQLException {
		try {
			return jdbcTemplate.update("delete from appointment where userID = ?", userID);
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in deleteAllAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int deleteAppointmentsInRange(Date startDate, Date endDate, String userID) throws SQLException {
		try {
			return jdbcTemplate.update(
					"delete from appointment where userID = ? and startDate >=? and " + "endDate <= ? ", userID,
					startDate, endDate);
		} catch (Exception e) {
			throw new SQLException(
					"Exception encountered in deleteAppointmentsInRange for user " + userID + " : " + e.getMessage());
		}
	}
}