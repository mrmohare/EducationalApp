package com.educationalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educationalApp.Model.LoginRequest;
import com.educationalApp.Model.LoginResponse;

@RequestMapping("educationalApp/users")
@RestController
public class Controller {

	@Autowired
	
	
	@PostMapping("/signUp")
	public ResponseEntity<LoginResponse> signup(@RequestBody LoginRequest loginRequest){
		
		
		return new ResponseEntity<LoginResponse>(HttpStatus.OK);
		
	}
}
