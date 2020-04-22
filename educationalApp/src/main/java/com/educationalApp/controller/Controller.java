package com.educationalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educationalApp.model.BasicResponse;
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


}
