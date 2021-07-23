package com.example.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.employee.error.validator.BloodType;

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @NotBlank(message = "Name is mandatory")
	@Size(min=2, max=30)
    private String name;
    
    @NotBlank(message = "Surname is mandatory")
	@Size(min=2, max=30)
    private String surname;
    
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    
    @BloodType
    @NotEmpty(message = "Please provide a BloodType")
    private String bloodType;
	
    @Email(message = "Email should be valid")
    private String email;
	
    @Min(value = 1, message = "MonthSalary should not be less than 1")
    @Max(value = 1000000, message = "MonthSalary should not be greater than 1000000")
    private double monthSalary;

	public Employee() {
	}

	public Employee(String name, String surname, int age, String bloodType, String email, double monthSalary) {
		super();

		this.name = name;
		this.surname = surname;
		this.age = age;
		this.bloodType = bloodType;
		this.email = email;
		this.monthSalary = monthSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getMonthSalary() {
		return monthSalary;
	}

	public void setMonthSalary(double monthSalary) {
		this.monthSalary = monthSalary;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", bloodType="
				+ bloodType + ", email=" + email + ", monthSalary=" + monthSalary + "]";
	}

}
