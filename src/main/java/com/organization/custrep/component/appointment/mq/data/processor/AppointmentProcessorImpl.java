package com.organization.custrep.component.appointment.mq.data.processor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.organization.custrep.appointment.generated.messages.AppointmentMessage;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.component.appointment.db.AppointmentDao;
import com.organization.custrep.component.appointment.exception.ProcessingException;

@Component
public class AppointmentProcessorImpl implements AppointmentProcessor {

	@Value("${application.allowedUpdationBeforeDuration}")
	private int allowedUpdationBeforeDuration;

	@Value("${application.allowedUpdationBeforeDurationUnit}")
	private String timeUnit;

	@Autowired
	private AppointmentDao dao;

	@Override
	public List<AppointmentMessage> findAppointmentByUserID(String userID) throws ProcessingException {
		try {
			return dao.findAppointments(userID);
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in findAppointmentByUserID for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int createAppointment(CreateAppointment appointmentRequest) throws ProcessingException {
		try {
			int recordCount = 0;
			Instant startInstant = appointmentRequest.getStartDate().toInstant();
			Instant endInstant = appointmentRequest.getEndDate().toInstant();
			Instant now = Instant.now();
			if (startInstant.isBefore(now)) {
				throw new ProcessingException("Cannot Book an appointment in the past");
			}

			if (endInstant.isBefore(startInstant)) {
				throw new ProcessingException("End Date cannot be before Start Date");
			}
			recordCount = dao.createAppointment(Date.from(startInstant), Date.from(endInstant),
					appointmentRequest.getUserID());
			int count = 1;
			for (; count < appointmentRequest.getNumDays(); count++) {
				Instant nextStartDay = startInstant.plus(1, ChronoUnit.DAYS);
				Instant nextEndDay = endInstant.plus(1, ChronoUnit.DAYS);
				recordCount += dao.createAppointment(Date.from(nextStartDay), Date.from(nextEndDay),
						appointmentRequest.getUserID());
				startInstant = nextStartDay;
				endInstant = nextEndDay;
			}
			return recordCount;
		} catch (Exception e) {
			throw new ProcessingException("Exception encountered in createAppointment for user "
					+ appointmentRequest.getUserID() + " : " + e.getMessage());
		}
	}

	@Override
	public boolean findAppointment(String userID, Date startDate, Date endDate) throws ProcessingException {
		try {
			Instant startInstant = startDate.toInstant();
			Instant endInstant = endDate.toInstant();
			return dao.findAppointment(userID, Date.from(startInstant), Date.from(endInstant));
		} catch (Exception e) {
			throw new ProcessingException(
					"Exception encountered in findAppointment for user " + userID + " : " + e.getMessage());
		}
	}

	@Override
	public int updateAppointment(UpdateAppointment appointmentRequest) throws ProcessingException {
		try {
			// Check if an appointment already exists on the oldAppointDate
			if (findAppointment(appointmentRequest.getUserID(), appointmentRequest.getOldStartDate(),
					appointmentRequest.getOldEndDate())) {
				if (appointmentRequest.getOldStartDate().toInstant()
						.minus(allowedUpdationBeforeDuration, getTimeUnit(timeUnit)).isBefore(Instant.now())) {
					throw new ProcessingException("Appointments cannot be updated before " + allowedUpdationBeforeDuration
							+ " " + timeUnit + " of scheduled appointments");
				}
			} else {
				throw new ProcessingException("No Appointment exists on that Date");
			}
			// Check if an appointment already exists on the newAppointDate
			List<AppointmentMessage> appointments = dao.findAppointmentsInRange(appointmentRequest.getUserID(),
					appointmentRequest.getStartDate(), appointmentRequest.getEndDate());
			if (appointments != null && appointments.size() > 0) {
				throw new ProcessingException("Appointment already exists on that Date");
			}

			Instant oldStartInstant = appointmentRequest.getOldStartDate().toInstant();
			Instant oldEndInstant = appointmentRequest.getOldEndDate().toInstant();
			Instant newStartInstant = appointmentRequest.getStartDate().toInstant();
			Instant newEndInstant = appointmentRequest.getEndDate().toInstant();
			Instant now = Instant.now();
			if (newStartInstant.isBefore(now)) {
				throw new ProcessingException("Cannot Book an appointment in the past");
			}
			if (newEndInstant.isBefore(newStartInstant)) {
				throw new ProcessingException("New End Date cannot be less than New Start Date");
			}
			return dao.updateAppointment(Date.from(oldStartInstant), Date.from(oldEndInstant),
					Date.from(newStartInstant), Date.from(newEndInstant), appointmentRequest.getUserID());
		} catch (Exception e) {
			throw new ProcessingException("Exception encountered in updateAppointment for user "
					+ appointmentRequest.getUserID() + " : " + e.getMessage());
		}
	}

	private ChronoUnit getTimeUnit(String timeUnit) {
		switch (timeUnit) {
		case "hours":
			return ChronoUnit.HOURS;
		case "days":
			return ChronoUnit.DAYS;
		case "minutes":
			return ChronoUnit.MINUTES;
		case "seconds":
			return ChronoUnit.SECONDS;
		case "millis":
			return ChronoUnit.MILLIS;
		case "micros":
			return ChronoUnit.MICROS;
		default:
			return ChronoUnit.HOURS;
		}
	}

	@Override
	public int cancelAppointment(DeleteAppointment appointmentRequest) throws ProcessingException {
		try {
			Instant startDateInstant = null;
			Instant endDateInstant = null;
			int recordNum = 0;
			if (appointmentRequest.isCancelAll()) {
				recordNum = dao.deleteAllAppointment(appointmentRequest.getUserID());
			} else if (!appointmentRequest.isCancelAll() && appointmentRequest.isCancelForRange()) {
				startDateInstant = appointmentRequest.getStartDate().toInstant();
				endDateInstant = appointmentRequest.getEndDate().toInstant();
				recordNum = dao.deleteAppointmentsInRange(Date.from(startDateInstant), Date.from(endDateInstant),
						appointmentRequest.getUserID());
			} else if (!appointmentRequest.isCancelAll() && !appointmentRequest.isCancelForRange()) {
				if (appointmentRequest.getStartDate() != null && appointmentRequest.getEndDate() != null
						&& findAppointment(appointmentRequest.getUserID(), appointmentRequest.getStartDate(),
								appointmentRequest.getEndDate())) {
					if (appointmentRequest.getStartDate().toInstant()
							.minus(allowedUpdationBeforeDuration, getTimeUnit(timeUnit)).isBefore(Instant.now())) {
						throw new ProcessingException("Appointments cannot be deleted before "
								+ allowedUpdationBeforeDuration + " " + timeUnit + " of scheduled appointments");
					}
				} else if (appointmentRequest.getStartDate() != null && appointmentRequest.getEndDate() != null) {
					throw new ProcessingException("No Appointment exists on that Date");
				}
				startDateInstant = appointmentRequest.getStartDate().toInstant();
				endDateInstant = appointmentRequest.getEndDate().toInstant();
				recordNum = dao.deleteAppointment(Date.from(startDateInstant), Date.from(endDateInstant),
						appointmentRequest.getUserID());
			}
			return recordNum;
		} catch (Exception e) {
			throw new ProcessingException("Exception encountered in cancelAppointment for user "
					+ appointmentRequest.getUserID() + " : " + e.getMessage());
		}
	}
}