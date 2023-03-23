package com.manager.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manager.microservice.entity.EmployeeLogin;
import com.manager.microservice.repository.EmployeeLoginRepo;

@Service
public class EmployeeLoginDetailsService implements UserDetailsService {

	@Autowired
	EmployeeLoginRepo emploginrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeLogin emplogin=emploginrepo.findById(username).get();
		return new EmployeeLoginDetails(emplogin);
	}

}
