package com.jk.domain.student.enrollment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.jk.domain.student.Student;
import com.jk.domain.subject.semester.SubjectSemester;


public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

	Optional<StudentEnrollment> findByStudentAndSubjectSemester(Student student, SubjectSemester subjectSemester);
	
	List<StudentEnrollment> findAllByStudent(Student student);
	
	List<StudentEnrollment> findAllBySubjectSemester(SubjectSemester subjectSemester);
	
	void deleteByStudentAndSubjectSemester(Student student, SubjectSemester subjectSemester);
	
	void deleteAllByStudent(Student student);
	
	void deleteAllBySubjectSemester(SubjectSemester subjectSemester);
}
