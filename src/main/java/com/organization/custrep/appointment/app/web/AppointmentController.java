package com.organization.custrep.appointment.app.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.organization.custrep.appointment.generated.messages.AppointmentResponse;
import com.organization.custrep.appointment.generated.messages.CreateAppointmentRequest;
import com.organization.custrep.appointment.generated.messages.DeleteAppointmentRequest;
import com.organization.custrep.appointment.generated.messages.ErrorResponseObject;
import com.organization.custrep.appointment.generated.messages.UpdateAppointmentRequest;
import com.organization.custrep.appointment.model.AppointmentFactory;
import com.organization.custrep.appointment.model.AppointmentManager;
import com.organization.custrep.appointment.model.CreateAppointment;
import com.organization.custrep.appointment.model.DeleteAppointment;
import com.organization.custrep.appointment.model.UpdateAppointment;
import com.organization.custrep.component.appointment.exception.BadDataException;
import com.organization.custrep.component.appointment.service.notification.NotificationService;

@RestController
public class AppointmentController {

	// private static final Logger LOGGER =
	// Logger.getLogger(AppointmentController.class.getName());

	@Autowired
	private AppointmentManager appointmentManager;
	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = { "/getAppointment/{userID}/" }, method = RequestMethod.GET, headers = {
			"Accept=application/xml", "Accept=application/json" }, produces = { "application/xml", "application/json" })
	public ResponseEntity<AppointmentResponse> findAppointmentByUserID(@PathVariable("userID") String userID)
			throws Exception {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new AppointmentResponse("Success", appointmentManager.viewAppointment(userID)));
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponseObject> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorResponseObject errorDetails = new ErrorResponseObject(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadDataException.class)
	public final ResponseEntity<ErrorResponseObject> handleException(Exception ex, WebRequest request) {
		ErrorResponseObject errorDetails = new ErrorResponseObject(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = { "/createAppointment/" }, method = RequestMethod.POST, headers = {
			"Accept=application/json" })
	public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentRequest appointmentRequest)
			throws Exception {
		CreateAppointment ca = AppointmentFactory.createAppointment(appointmentRequest.getStartDate(),
				appointmentRequest.getEndDate(), appointmentRequest.getUserID(), appointmentRequest.getNumDays());
		appointmentManager.createAppointment(ca);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initiated");
	}

	@RequestMapping(value = { "/updateAppointment/" }, method = RequestMethod.PUT, headers = {
			"Accept=application/json" })
	public ResponseEntity<String> updateAppointment(@RequestBody UpdateAppointmentRequest appointmentRequest)
			throws Exception {
		UpdateAppointment ua = AppointmentFactory.updateAppointment(appointmentRequest.getStartDate(),
				appointmentRequest.getEndDate(), appointmentRequest.getOldStartDate(),
				appointmentRequest.getOldEndDate(), appointmentRequest.getUserID());
		appointmentManager.updateAppointment(ua);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initiated");
	}

	@RequestMapping(value = { "/cancelAppointment/" }, method = RequestMethod.DELETE, headers = {
			"Accept=application/json" })
	public ResponseEntity<String> cancelAppointment(@RequestBody DeleteAppointmentRequest appointmentRequest)
			throws Exception {
		DeleteAppointment ca = AppointmentFactory.deleteAppointment(appointmentRequest.getStartDate(),
				appointmentRequest.getEndDate(), appointmentRequest.isCancelAll(),
				appointmentRequest.isCancelForThisBlock(), appointmentRequest.getUserID());
		appointmentManager.deleteAppointment(ca);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initiated");
	}

	@RequestMapping(value = { "/sendNotification/{numDays}/" }, method = RequestMethod.GET, headers = {
			"Accept=application/json" })
	public ResponseEntity<String> sendNotification(@PathVariable("numDays") int numDays)
			throws Exception {
		notificationService.sendNotification(numDays);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initiated");
	}
}