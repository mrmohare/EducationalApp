package com.educationalApp.serviceImpl;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.text.RandomStringGenerator;
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
			
			System.out.println();
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


	public BasicResponse forgotPassword(String email) throws AddressException, MessagingException, IOException {
		LoginDetEntity user = signupRepository.findByEmail(email);
		BasicResponse response = new BasicResponse();
		if(null!=user){
			emailNewPassword(user);
			response.setHttpStatus("Success");
			response.setHttpStatusCode(200);
			response.setMessage("New password sent to registered email");
			return response;
		}
		response.setHttpStatus("Failure");
		response.setHttpStatusCode(400);
		response.setMessage("Email is not registered");
		return response;	}


	private void emailNewPassword(LoginDetEntity user) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("mithunraj777999@gmail.com", "ammaBaba@7");
		      }
		   });
		   Random random = new SecureRandom();
		   ;
		   RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(97,122)
			        .build();
		   StringBuilder newPassword = new StringBuilder();
		   newPassword.append(pwdGenerator.generate(5));
		   newPassword.append(random.nextInt(1000));
		   user.setPassword(newPassword.toString());
		   signupRepository.save(user);
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("mithunraj777999@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		   msg.setSubject("Reset password");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Please use this temporary password to login and set new password - "+  newPassword, "text/html");
		   
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   /*MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);*/
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}

}
