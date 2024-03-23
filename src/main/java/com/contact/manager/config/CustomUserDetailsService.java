package com.contact.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contact.manager.dao.UserRepository;
import com.contact.manager.entites.User;
import com.contact.manager.helper.Message;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Fetching User From Database
		User user = userRepository.getUserByUserName(username );
		if(user == null) {
			throw new UsernameNotFoundException("Username or Password is wrong!");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;
		
	}
	
}
