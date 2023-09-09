package com.jk.subject.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.semester.Semester;
import com.jk.subject.Subject;
import com.jk.professor.Professor;


public interface SubjectSemesterRepository extends JpaRepository<SubjectSemester, Long>{
	
	List<SubjectSemester> findAllBySubject(Subject subject);
	
	List<SubjectSemester> findAllBySemester(Semester semester);
	
	List<SubjectSemester> findAllBySubjectAndSemester(Subject subject, Semester semester);
	
	Optional<SubjectSemester> findBySubjectAndSemesterAndSection(Subject subject, Semester semester, Section section); 
	
	List<SubjectSemester> findAllByProfessor(Professor professor);
	
	void deleteAllBySubject(Subject subject);
	
	void deleteAllBySemester(Semester semester);
	
	void deleteAllBySubjectAndSemester(Subject subject, Semester semester);
	
	void deleteBySubjectAndSemesterAndSection(Subject subject, Semester semester,Section section);
	
	void deleteAllByProfessor(Professor professor);
}
