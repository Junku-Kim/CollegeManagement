package com.jk.professor;

import java.util.List;

import com.jk.employee.EmployeeRepository;

public interface ProfessorRepository extends EmployeeRepository {
	
	List<Professor> findAllByRank(Rank rank);
	
}
