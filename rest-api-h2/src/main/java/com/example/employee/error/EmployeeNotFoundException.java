package com.example.employee.error;

public class EmployeeNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
        super("Employee id not found : " + id);
    }

}
