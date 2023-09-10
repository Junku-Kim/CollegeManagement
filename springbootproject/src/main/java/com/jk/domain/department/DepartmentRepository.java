package com.jk.domain.department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByMajor(String major);
	
	List<Department> findAllByFaculty(String faculty);
	
	List<Department> findAllByCollege(String college);
	
	void deleteByMajor(String major);
	
	void deleteAllByFaculty(String faculty);
	
	void deleteAllByCollege(String college);
}
