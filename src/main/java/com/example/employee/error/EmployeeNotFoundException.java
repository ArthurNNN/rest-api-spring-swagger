package com.example.employee.error;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException( int id) {
        super("Employee id not found : " + id);
    }

}
