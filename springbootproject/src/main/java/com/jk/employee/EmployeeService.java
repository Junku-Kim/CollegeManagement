package com.jk.employee;

import com.jk.person.PersonRepository;
import com.jk.person.PersonService;

public class EmployeeService extends PersonService{

	public EmployeeService(PersonRepository personRepository) {
		super(personRepository);
	}
	
}
