package com.jk.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.department.Department;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	Optional<Subject> findBySubjectCode(String subjectCode);
	
	List<Subject> findAllByName(String name);

	List<Subject> findAllByAcademicYear(int academicYear);

	List<Subject> findAllByCredit(int credit);
	
	List<Subject> findAllBySubjectType(SubjectType subjectType);
	
	List<Subject> findAllByDepartment(Department department);
	
	void deleteBySubjectCode(String subjectCode);
	
	void deleteAllByDepartment(Department department);
}
