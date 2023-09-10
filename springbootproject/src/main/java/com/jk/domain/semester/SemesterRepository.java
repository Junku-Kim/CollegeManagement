package com.jk.domain.semester;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long>{

	Optional<Semester> findBySemesterName(String semesterName);
	
	void deleteBySemesterName(String semesterName);
}
