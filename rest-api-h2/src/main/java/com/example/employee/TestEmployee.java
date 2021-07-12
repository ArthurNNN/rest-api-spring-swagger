package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestEmployee implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;


	public void run(String... args) throws Exception {

		Employee isa = new Employee("Isabel", "Alvaro", 25, "isa@fakeyahoo.com", 1500.00);
		Employee joan = new Employee("Joan", "Moreno", 25, "joan@fakegmail.com", 1200.00);
		Employee dimitry = new Employee("Dimitry", "Morozov", 23, "dimitry@fakeyandex.com", 1100.00);

		employeeRepository.save(isa);
		employeeRepository.save(joan);
		employeeRepository.save(dimitry);

	}
}
