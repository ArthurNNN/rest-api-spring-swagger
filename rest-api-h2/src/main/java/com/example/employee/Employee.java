package com.example.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @NotBlank(message = "Name is mandatory")
	@Size(min=2, max=30)
	public String name;
    
    @NotBlank(message = "Surname is mandatory")
	@Size(min=2, max=30)
	public String surname;
    
	@NotNull
	@Min(0)
	@Max(130)
	public int age;
	
	@NotBlank(message = "Email is mandatory")
	public String email;
	
	@Min(0)
	@Max(1000000)
	public double monthSalary;

	public Employee() {
		super();
	}

	public Employee(String name, String surname, int age, String email, double monthSalary) {
		super();

		this.name = name;
		this.surname = surname;
		this.age = age;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", email=" + email
				+ ", monthSalary=" + monthSalary + "]\n";
	}
}
