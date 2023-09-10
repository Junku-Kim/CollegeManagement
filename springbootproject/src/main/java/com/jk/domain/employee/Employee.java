package com.jk.domain.employee;


import com.jk.domain.person.Address;
import com.jk.domain.person.Person;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("EMPLOYEE")
@Entity
public abstract class Employee extends Person {
	
	private int salary;
	
	private String location;
	
	private String officeNumber;

	public Employee(String name, String loginId, String password, String phoneNumber, String email, Address address, int salary, String location, String officeNumber) {
		super(name, loginId, password, phoneNumber, email, address);
		this.salary = salary;
		this.location = location;
		this.officeNumber = officeNumber;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + ", location=" + location + ", officeNumber=" + officeNumber + "]";
	}
	
	
}
