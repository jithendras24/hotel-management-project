package com.example.demo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Employee employee = this.repo.findByEmailId(username);
		
		String role = "ROLE_USER";
		if(employee.isAdmin()) {
			role = "ROLE_ADMIN";
		}
		
		boolean notBlocked = true;
		if(employee.isBlocked()) {
			notBlocked = false;
		}
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(role));
		
		return new User(employee.getEmailId(), employee.getPassword(), true, true, true, notBlocked, authorities);
	}

}
