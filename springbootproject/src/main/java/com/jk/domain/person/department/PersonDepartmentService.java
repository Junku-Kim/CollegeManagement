package com.jk.domain.person.department;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.department.Department;
import com.jk.domain.person.Person;

@Transactional
@Service
public class PersonDepartmentService {

	private final PersonDepartmentRepository personDepartmentRepository;
	
	public PersonDepartmentService(PersonDepartmentRepository personDepartmentRepository) {
		this.personDepartmentRepository = personDepartmentRepository;
	}
	
	public Long registerPersonDepartment(PersonDepartment personDepartment) {
		validatePersonDepartmentForCreation(personDepartment);
		this.personDepartmentRepository.save(personDepartment);
		
		return personDepartment.getId();
	}
	
	private void validatePersonDepartmentForCreation(PersonDepartment personDepartment) {
		this.personDepartmentRepository
			.findByPersonAndDepartment(personDepartment.getPerson(), personDepartment.getDepartment())
			.ifPresent(pd -> {
				throw new IllegalStateException("이미 존재하는 PersonDepartment입니다.");
			});
	}

	public List<PersonDepartment> findAllByPerson(Person person) {
		return this.personDepartmentRepository.findAllByPerson(person);
	}
	
	public List<PersonDepartment> findAllByDepartment(Department department) {
		return this.personDepartmentRepository.findAllByDepartment(department);
	}
	
	public Optional<PersonDepartment> findByPersonAndDepartment(Person person, Department department) {
		return this.personDepartmentRepository.findByPersonAndDepartment(person, department);
	}
	
	public List<PersonDepartment> findAllPersonDepartments() {
		return this.personDepartmentRepository.findAll();
	}
	
	public Long deletePersonDepartment(PersonDepartment personDepartment) {
		this.personDepartmentRepository.delete(personDepartment);
		
		return personDepartment.getId();
	}
	
	public void deleteAllByPerson(Person person) {
		this.personDepartmentRepository.deleteAllByPerson(person);
	}

	public void deleteAllByDepartment(Department department) {
		this.personDepartmentRepository.deleteAllByDepartment(department);
	}

	public void deleteByPersonAndDepartment(Person person, Department department) {
		this.personDepartmentRepository.deleteByPersonAndDepartment(person, department);
	}
	
	public void deleteAllPersonDepartments() {
		this.personDepartmentRepository.deleteAll();
	}
}
