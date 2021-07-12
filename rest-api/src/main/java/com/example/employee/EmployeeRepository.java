package com.example.employee;

import org.springframework.data.repository.CrudRepository;

	public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
		
		public Iterable<Employee> findByAge (int age);
		
	}


