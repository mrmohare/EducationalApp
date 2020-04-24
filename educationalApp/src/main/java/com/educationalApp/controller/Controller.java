package com.educationalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.educationalApp.model.BasicResponse;
import com.educationalApp.model.LoginRequest;
import com.educationalApp.model.LoginResponse;
import com.educationalApp.model.SignupRequest;
import com.educationalApp.serviceImpl.ServiceImplementation;

@RequestMapping("educationalApp/users")
@RestController
public class Controller {

	@Autowired
	ServiceImplementation serviceImpl;

	
	@PostMapping("/signup")
	public ResponseEntity<BasicResponse> signup(@RequestBody SignupRequest signupRequest){
		
		BasicResponse response = serviceImpl.signup(signupRequest);
		
		return new ResponseEntity<BasicResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
		
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> loginAndCreateAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		
		LoginResponse response = serviceImpl.login(loginRequest);

		return new  ResponseEntity<LoginResponse>(response,HttpStatus.valueOf(response.getHttpStatusCode()));
	}

}
