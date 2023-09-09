package com.jk.department;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public String registerDepartment(Department department) {
		validateProfessorForCreation(department);
		this.departmentRepository.save(department);
		
		return department.getMajor();
	}
	
	private void validateProfessorForCreation(Department department) {
		this.departmentRepository.findByMajor(department.getMajor())
			.ifPresent(d -> {
				throw new IllegalStateException("이미 존재하는 학과입니다.");
			});
	}
	
	public Optional<Department> findByMajor(String major) {
		return this.departmentRepository.findByMajor(major);
	}
	
	public List<Department> findAllByFaculty(String faculty) {
		return this.departmentRepository.findAllByFaculty(faculty);
	}
	
	public List<Department> findAllByCollege(String college) {
		return this.departmentRepository.findAllByCollege(college);
	}
	
	public List<Department> findAllDepartments() {
		return this.departmentRepository.findAll();
	}
	
	public Long deleteDepartment(Department department) {
		this.departmentRepository.delete(department);
		
		return department.getId();
	}
	
	public void deleteByMajor(String major) {
		this.departmentRepository.deleteByMajor(major);
	}
	
	public void deleteAllByFaculty(String faculty) {
		this.departmentRepository.deleteAllByFaculty(faculty);
	}
	
	public void deleteAllByCollege(String college) {
		this.departmentRepository.deleteAllByCollege(college);
	}
	
	public void deleteAllDepartment() {
		this.departmentRepository.deleteAll();
	}
}
