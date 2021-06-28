package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {
	
	Employee findByEmailId(String emailId);
	
}
