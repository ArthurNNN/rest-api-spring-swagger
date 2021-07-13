package com.example.employee.error;

import java.util.Set;

public class EmployeeUnSupportedFieldPatchException extends RuntimeException {

	public EmployeeUnSupportedFieldPatchException(Set<String> keys) {
		super("Field " + keys.toString() + " update is not allow.");
	}

}
