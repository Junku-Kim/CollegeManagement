package com.jk.student.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.student.Student;
import com.jk.subject.semester.SubjectSemester;

@Transactional
@Service
public class StudentEnrollmentService {

	private final StudentEnrollmentRepository studentEnrollmentRepository;

	public StudentEnrollmentService(StudentEnrollmentRepository studentEnrollmentRepository) {
		this.studentEnrollmentRepository = studentEnrollmentRepository;
	}
	
	public Long registerStudentEnrollment(StudentEnrollment studentEnrollment) {
		validateStudentEnrollmentForCreation(studentEnrollment);
		this.studentEnrollmentRepository.save(studentEnrollment);
		
		return studentEnrollment.getId();
	}

	private void validateStudentEnrollmentForCreation(StudentEnrollment studentEnrollment) {
		this.studentEnrollmentRepository
			.findByStudentAndSubjectSemester(studentEnrollment.getStudent(), studentEnrollment.getSubjectSemester())
			.ifPresent(se -> {
				throw new IllegalStateException("이미 존재하는 StudentEnrollment입니다.");
			});
	}
	
	public Optional<StudentEnrollment> findAllByStudentAndSubjectSemester(Student student, SubjectSemester subjectSemester) {
		return this.studentEnrollmentRepository.findByStudentAndSubjectSemester(student, subjectSemester);
	}
	
	public List<StudentEnrollment> findAllByStudent(Student student) {
		return this.studentEnrollmentRepository.findAllByStudent(student);
	}
	
	public List<StudentEnrollment> findAllBySubjectSemester(SubjectSemester subjectSemester) {
		return this.studentEnrollmentRepository.findAllBySubjectSemester(subjectSemester);
	}
	
	public Long deleteStudentEnrollment(StudentEnrollment studentEnrollment) {
		this.studentEnrollmentRepository.delete(studentEnrollment);
		
		return studentEnrollment.getId();
	}
	
	public void deleteByStudentAndSubjectSemester(Student student, SubjectSemester subjectSemester) {
		this.studentEnrollmentRepository.deleteByStudentAndSubjectSemester(student, subjectSemester);
	}
	
	public void deleteAllByStudent(Student student) {
		this.studentEnrollmentRepository.deleteAllByStudent(student);
	}
	
	public void deleteAllBySubjectSemester(SubjectSemester subjectSemester) {
		this.studentEnrollmentRepository.deleteAllBySubjectSemester(subjectSemester);
	}
}
