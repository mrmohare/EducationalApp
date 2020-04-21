package com.educationalApp.ServiceImpl;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educationalApp.Model.BasicResponse;
import com.educationalApp.Model.SignupRequest;
import com.educationalApp.Repository.SignupRepository;
import com.educationalApp.entity.LoginDetEntity;

@Service
public class ServiceImplementation {

	
	@Autowired
	SignupRepository signupRepository;
	
	
	public BasicResponse signup(SignupRequest signupRequest) {
		LoginDetEntity loginEnt = new LoginDetEntity();
		BasicResponse response =new BasicResponse();
		
		loginEnt.setName(signupRequest.getFullName());
		loginEnt.setEmail(signupRequest.getEmail());
		loginEnt.setPassword(signupRequest.getPassword());
		loginEnt.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		loginEnt.setMobile(signupRequest.getMobile());
		loginEnt.setRole("user");
		try{
			signupRepository.save(loginEnt);
			response.setHttpStatus("Success");
			response.setHttpStatusCode(200);
			response.setMessage("Registration completed successfully");
			return response;
		}catch(Exception e){
			System.out.println(e.getMessage());
			response.setHttpStatus("Failure");
			response.setHttpStatusCode(400);
			response.setMessage("Email already exists");
			return response;
		}
		
		
		
	}

}
