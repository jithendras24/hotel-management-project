package com.example.demo.controllers;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.utils.JwtUtil;

//@Controller
@RestController
public class HelloController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> createJwtToken(@RequestBody Employee employee) throws Exception {
		
		try {
			
			byte[] password = Base64.getDecoder().decode(employee.getPassword());
			
			employee.setPassword(new String(password));
			
										//spring security authentication
			authManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmailId(), employee.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials");
		} catch (LockedException e) {
			return ResponseEntity.status(560).body("Account Blocked");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(employee.getEmailId());
		
		String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(jwt);
		
		//passing token in response header
//		Employee emp = this.repo.findByEmailId(employee.getEmailId());
//		MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
//		headerMap.put("Authorization", Arrays.asList(jwt));
//		
//		return new ResponseEntity<>(emp, headerMap, HttpStatus.OK);
	}
	
	@GetMapping(path = "/hello")
//	@ResponseBody
	public ResponseEntity<String> hello() {
//		return "Hello world";
		
		return ResponseEntity.status(200).body("Hello World");
	}
	
	
	@GetMapping(path = "/hi")
//	@ResponseBody
	public String hi(Principal principal) {
//		System.out.println(principal);
//		System.out.println(principal.getName());
		return "Hello world";
	}
	
	@GetMapping(path = "/empDetails")
	public ResponseEntity<Employee> getEmployeeDetails(HttpServletRequest request) {
		
		String email = jwtUtil.extractUserName(request.getHeader("AUTHORIZATION").substring(7));
		
		Employee emp = this.repo.findByEmailId(email);
		
		return ResponseEntity.ok(emp);
	}
	
}
