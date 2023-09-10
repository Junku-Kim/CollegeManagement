package com.jk.domain.subject.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.schedule.Schedule;
import com.jk.domain.subject.semester.SubjectSemester;

@Transactional
@Service
public class SubjectEnrollmentService {

	private final SubjectEnrollmentRepository subjectEnrollmentRepository;

	public SubjectEnrollmentService(SubjectEnrollmentRepository subjectEnrollmentRepository) {
		this.subjectEnrollmentRepository = subjectEnrollmentRepository;
	}
	
	public Long registerSubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		validateSubjectEnrollmentForCreation(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		return subjectEnrollment.getId();
	}

	private void validateSubjectEnrollmentForCreation(SubjectEnrollment subjectEnrollment) {
		this.subjectEnrollmentRepository
			.findBySubjectSemesterAndSchedule(subjectEnrollment.getSubjectSemester(), subjectEnrollment.getSchedule())
			.ifPresent(se -> {
				throw new IllegalStateException("이미 존재하는 SubjectEnrollment입니다.");
			});
	}
	
	public Optional<SubjectEnrollment> findBySubjectSemesterAndSchedule(SubjectSemester subjectSemester, Schedule schedule) {
		return this.subjectEnrollmentRepository.findBySubjectSemesterAndSchedule(subjectSemester, schedule);
	}
	
	public List<SubjectEnrollment> findAllBySubjectSemester(SubjectSemester subjectSemester) {
		return this.subjectEnrollmentRepository.findAllBySubjectSemester(subjectSemester);
	}
	
	public List<SubjectEnrollment> findAllSubjectEnrollments() {
		return this.subjectEnrollmentRepository.findAll();
	}
	
	public Long deleteBySubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		this.subjectEnrollmentRepository.delete(subjectEnrollment);
		
		return subjectEnrollment.getId();
	}
	
	public void deleteBySubjectSemesterAndSchedule(SubjectSemester subjectSemester, Schedule schedule) {
		this.subjectEnrollmentRepository.deleteBySubjectSemesterAndSchedule(subjectSemester, schedule);
	}
	
	public void deleteAllBySubjectSemester(SubjectSemester subjectSemester) {
		this.subjectEnrollmentRepository.deleteAllBySubjectSemester(subjectSemester);
	}
	
	public void deleteAllSubjectEnrollments() {
		this.subjectEnrollmentRepository.deleteAll();
	}
}
