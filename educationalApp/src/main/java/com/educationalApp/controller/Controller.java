package com.educationalApp.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educationalApp.constants.EduAppConstants;
import com.educationalApp.model.BasicResponse;
import com.educationalApp.model.ChangePasswordRequest;
import com.educationalApp.model.ChangePasswordResponse;
import com.educationalApp.model.LoginRequest;
import com.educationalApp.model.LoginResponse;
import com.educationalApp.model.SignupRequest;
import com.educationalApp.serviceImpl.ServiceImplementation;

//@RequestMapping("educationalApp/users")
@RestController
public class Controller {

	@Autowired
	ServiceImplementation serviceImpl;

	
	/**
	 * Method to register new user
	 * @param signupRequest
	 * @return BasicResponse
	 */
	@PostMapping(EduAppConstants.SIGNUP_ENDPOINT)
	public ResponseEntity<BasicResponse> signup(@RequestBody SignupRequest signupRequest){
		
		BasicResponse response = serviceImpl.signup(signupRequest);
		
		return new ResponseEntity<BasicResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
		
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> loginAndCreateAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		
		LoginResponse response = serviceImpl.login(loginRequest);
		return new  ResponseEntity<LoginResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
	}
		
	/**
	 * Method for ForgotPassword
	 * @param email
	 * @return BasicResponse
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	@GetMapping(EduAppConstants.FORGOT_PASSWORD_ENDPOINT)
	public ResponseEntity<BasicResponse> forgotPassword(@PathVariable String email) throws AddressException, MessagingException, IOException{
		
		BasicResponse response = serviceImpl.forgotPassword(email);
		
		return new ResponseEntity<BasicResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
		
	}
	
	@PostMapping(EduAppConstants.CHANGEPASSWORD_ENDPOINT)
	public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
		
		ChangePasswordResponse response = serviceImpl.changePassword(changePasswordRequest);

		return new  ResponseEntity<ChangePasswordResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
	}
		

}
