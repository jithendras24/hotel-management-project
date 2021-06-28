package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@SpringBootApplication
public class SpringJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return new CommandLineRunner() {
			
			@Autowired
			private EmployeeRepository repo;
			
			@Override
			public void run(String... args) throws Exception {
				
				this.repo.save(new Employee(101, "jithendra@gmail.com", new BCryptPasswordEncoder().encode("jithendra"), true, true));
				this.repo.save(new Employee(201, "jith@gmail.com", new BCryptPasswordEncoder().encode("jithendra"), false, false));
				
			}
		};
		
	}
	
}
