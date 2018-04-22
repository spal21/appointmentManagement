package com.organization.custrep.component.appointment.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;

public interface AppointmentDao {

	List<AppointmentMessage> findAppointments(String userID) throws SQLException;

	List<AppointmentMessage> findAppointmentsInRange(String userID, Date startDate, Date endDate) throws SQLException;

	int createAppointment(Date startDate, Date endDate, String userID) throws SQLException;

	boolean findAppointment(String userID, Date startDate, Date endDate) throws SQLException;

	AppointmentMessage getAppointment(String userID, Date startDate, Date endDate) throws SQLException;

	List<AppointmentMessage> getAppointments(String userID, Date startDate, Date endDate) throws SQLException;

	int updateAppointment(Date oldStartDate, Date oldEndDate, Date newStartDate, Date newEndDate, String userID)
			throws SQLException;

	int deleteAppointment(Date startDate, Date endDate, String userID) throws SQLException;

	int deleteAllAppointment(String userID) throws SQLException;

	int deleteAppointmentsInRange(Date startDate, Date endDate, String userID) throws SQLException;
}
