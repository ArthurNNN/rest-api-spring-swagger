package com.example.employee;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.error.EmployeeNotFoundException;

@RestController
@Validated
@RequestMapping("/webapi")
public class EmployeeRestController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping(path = "/addEmployee", consumes = "application/json")
	public void addEmployee(@Valid @RequestBody Employee employee) {
		employeeRepository.save(employee);
	}

	@GetMapping("/getAllEmployees")
	public Iterable<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@GetMapping("/getEmployee/{id}")
	public Employee findById(@Valid @PathVariable @Min(1) int id) {

		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/updateEmployee/{id}")
	public void updateEmployee(@Valid @RequestBody Employee employee, @PathVariable @Min(1) int id) {

		Optional<Employee> employeeFound = employeeRepository.findById(id);

		if (employeeFound.isPresent()) {

			if (!employee.getName().equals(employeeFound.get().getName()))
				employeeFound.get().setName(employee.getName());

			if (!employee.getSurname().equals(employeeFound.get().getSurname()))
				employeeFound.get().setSurname(employee.getSurname());

			if (employee.getAge() != employeeFound.get().getAge())
				employeeFound.get().setAge(employee.getAge());

			if (!employee.getBloodType().equals(employeeFound.get().getBloodType()))
				employeeFound.get().setBloodType(employee.getBloodType());

			if (!employee.getEmail().equals(employeeFound.get().getEmail()))
				employeeFound.get().setEmail(employee.getEmail());

			if (employee.getMonthSalary() != employeeFound.get().getMonthSalary())
				employeeFound.get().setMonthSalary(employee.getMonthSalary());

			employeeRepository.save(employeeFound.get());
		} else {
			throw new EmployeeNotFoundException(id);
		}
	}

	@DeleteMapping("/deleteAllEmployees")
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@Valid @PathVariable @Min(1) int id) {

		Optional<Employee> employeeFound = employeeRepository.findById(id);

		if (employeeFound.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new EmployeeNotFoundException(id);
		}
	}
}
