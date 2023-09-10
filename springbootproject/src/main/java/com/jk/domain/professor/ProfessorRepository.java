package com.jk.domain.professor;

import java.util.List;

import com.jk.domain.employee.EmployeeRepository;

public interface ProfessorRepository extends EmployeeRepository {
	
	List<Professor> findAllByRank(Rank rank);
	
}
