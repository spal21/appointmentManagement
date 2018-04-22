package com.organization.custrep.component.appointment.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.organization.custrep.appointment.generated.messages.NotificationXML;

public interface NotificationDao {
	List<NotificationXML> findAppointmentsForNotification(Date date) throws SQLException;

}
