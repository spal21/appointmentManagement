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

import com.organization.custrep.appointment.generated.messages.CreateUserRequest;
import com.organization.custrep.appointment.generated.messages.DeleteUserRequest;
import com.organization.custrep.appointment.generated.messages.ErrorResponseObject;
import com.organization.custrep.appointment.generated.messages.UpdateUserRequest;
import com.organization.custrep.appointment.generated.messages.UserResponse;
import com.organization.custrep.appointment.model.CreateUserImpl;
import com.organization.custrep.appointment.model.DeleteUserImpl;
import com.organization.custrep.appointment.model.UpdateUser;
import com.organization.custrep.appointment.model.UpdateUserImpl;
import com.organization.custrep.appointment.model.User;
import com.organization.custrep.component.appointment.exception.BadDataException;
import com.organization.custrep.component.appointment.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/getUser/{userID}/" }, method = RequestMethod.GET, headers = {
			"Accept=application/xml", "Accept=application/json" }, produces = { "application/xml", "application/json" })
	public ResponseEntity<UserResponse> findUserByID(@PathVariable("userID") String userID) throws Exception {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new UserResponse("Success", userService.findUserByUserID(userID)));
	}

	@RequestMapping(value = { "/createUser/" }, method = RequestMethod.POST, headers = { "Accept=application/json" })
	public ResponseEntity<String> createUser(@RequestBody CreateUserRequest userRequest) throws Exception {
		User user = new CreateUserImpl(userRequest.getUserID(),userRequest.getEmail());
		user.validateParams();
		userService.createUser(user);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initialted");
	}

	@RequestMapping(value = { "/updateUser/" }, method = RequestMethod.PUT, headers = { "Accept=application/json" })
	public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest userRequest) throws Exception {
		UpdateUser user = new UpdateUserImpl(userRequest.getUserID(),userRequest.getEmail(),userRequest.getOldEmail());
		user.validateParams();
		userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initialted");
	}
	

	@RequestMapping(value = { "/deleteUser/" }, method = RequestMethod.DELETE, headers = {
			"Accept=application/json" })
	public ResponseEntity<String> deleteUsert(@RequestBody DeleteUserRequest userRequest)
			throws Exception {
		User user = new DeleteUserImpl(userRequest.getUserID(),userRequest.getEmail());
		user.validateParams();
		userService.deleteUser(user);
		return ResponseEntity.status(HttpStatus.OK).body("Process Initialted");
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
}
