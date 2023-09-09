package com.jk.subject.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.schedule.Schedule;
import com.jk.subject.semester.SubjectSemester;

public interface SubjectEnrollmentRepository extends JpaRepository<SubjectEnrollment, Long> {

	Optional<SubjectEnrollment> findBySubjectSemesterAndSchedule(SubjectSemester subjectSemester, Schedule schedule);
	
	List<SubjectEnrollment> findAllBySubjectSemester(SubjectSemester subjectSemester);
	
	void deleteBySubjectSemesterAndSchedule(SubjectSemester subjectSemester, Schedule schedule);
	
	void deleteAllBySubjectSemester(SubjectSemester subjectSemester);
}
