package com.jk.domain.student.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.semester.Semester;
import com.jk.domain.student.Student;

@Transactional
@Service
public class StudentSemesterService {

	private final StudentSemesterRepository studentSemesterRepository;
	
	public StudentSemesterService(StudentSemesterRepository studentSemesterRepository) {
		this.studentSemesterRepository = studentSemesterRepository;
	}
	
	public Long registerStudentSemester(StudentSemester studentSemester) {
		validateStudentSemesterForCreation(studentSemester);
		this.studentSemesterRepository.save(studentSemester);
		
		return studentSemester.getId();
	}

	private void validateStudentSemesterForCreation(StudentSemester studentSemester) {
		this.studentSemesterRepository.findByStudentAndSemester(studentSemester.getStudent(), studentSemester.getSemester())
			.ifPresent(ss -> {
				throw new IllegalStateException("이미 존재하는 StudentSemester입니다.");
			});
	}
	
	public Optional<StudentSemester> findByStudentAndSemester(Student student, Semester semester) {
		return this.studentSemesterRepository.findByStudentAndSemester(student, semester);
	}
	
	public List<StudentSemester> findAllByStudent(Student student) {
		return this.studentSemesterRepository.findAllByStudent(student);
	}
	
	public List<StudentSemester> findAllBySemester(Semester semester) {
		return this.studentSemesterRepository.findAllBySemester(semester);
	}
	
	public List<StudentSemester> findAllStudentSemesters() {
		return this.studentSemesterRepository.findAll();
	}
	
	public void deleteStudentSemester(StudentSemester studentSemester) {
		this.studentSemesterRepository.delete(studentSemester);
	}
	
	public void deleteByStudentAndSemester(Student student, Semester semester) {
		this.studentSemesterRepository.deleteByStudentAndSemester(student, semester);
	}
	
	public void deleteAllByStudent(Student student) {
		this.studentSemesterRepository.deleteAllByStudent(student);
	}
	
	public void deleteAllBySemester(Semester semester) {
		this.studentSemesterRepository.deleteAllBySemester(semester);
	}
	
	public void deleteAllStudentSemester() {
		this.studentSemesterRepository.deleteAll();
	}
}
