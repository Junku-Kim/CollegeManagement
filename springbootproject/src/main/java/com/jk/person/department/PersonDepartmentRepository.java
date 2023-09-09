package com.jk.person.department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.department.Department;
import com.jk.person.Person;

public interface PersonDepartmentRepository extends JpaRepository<PersonDepartment, Long>{

	List<PersonDepartment> findAllByPerson(Person person);

	List<PersonDepartment> findAllByDepartment(Department department);
	
	Optional<PersonDepartment> findByPersonAndDepartment(Person person, Department department);
	
	void deleteAllByPerson(Person person);

	void deleteAllByDepartment(Department department);
	
	void deleteByPersonAndDepartment(Person person, Department department);
}
