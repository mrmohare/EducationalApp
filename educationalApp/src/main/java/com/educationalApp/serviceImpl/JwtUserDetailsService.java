package com.educationalApp.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educationalApp.entity.LoginDetEntity;
import com.educationalApp.repository.LoginRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginDetEntity loginEnt = new LoginDetEntity();
		loginEnt=loginRepository.findByEmail(username);
		if ((loginEnt != null) &&(loginEnt.getEmail().equals(username))) {
			return new User(username, loginEnt.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}

