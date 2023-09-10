package com.jk.domain.employee;

import com.jk.domain.person.PersonRepository;
import com.jk.domain.person.PersonService;

public class EmployeeService extends PersonService{

	public EmployeeService(PersonRepository personRepository) {
		super(personRepository);
	}
	
}
