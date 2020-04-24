package com.educationalApp.serviceImpl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.educationalApp.config.JwtTokenUtil;
import com.educationalApp.entity.LoginDetEntity;
import com.educationalApp.model.BasicResponse;
import com.educationalApp.model.LoginRequest;
import com.educationalApp.model.LoginResponse;
import com.educationalApp.model.SignupRequest;
import com.educationalApp.repository.LoginRepository;
import com.educationalApp.repository.SignupRepository;
import com.educationalApp.serviceImpl.JwtUserDetailsService;

@Service
public class ServiceImplementation {

	
	@Autowired
	SignupRepository signupRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
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


	public LoginResponse login(LoginRequest loginRequest) {
		
		LoginDetEntity loginEnt = new LoginDetEntity();
		LoginResponse response =new LoginResponse();
		String username=loginRequest.getUsername();
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(username);
		
		try{
			loginEnt=loginRepository.findByEmail(username);
			if(loginEnt.getPassword().equals(loginRequest.getPassword())) {
				response.setHttpStatus("Success");
				response.setHttpStatusCode(200);
				response.setMessage("logged in successfully");
				final String token = jwtTokenUtil.generateToken(userDetails);
				response.setToken(token);
			}
			else {
				response.setHttpStatus("Failed");
				response.setHttpStatusCode(400);
				response.setMessage("wrong password");
			}
			return response;

		}catch(Exception e){
			System.out.println(e.getMessage());
			response.setHttpStatus("Failure");
			response.setHttpStatusCode(400);
			response.setMessage("user does not exists");
			return response;
		}
		
	}

}
