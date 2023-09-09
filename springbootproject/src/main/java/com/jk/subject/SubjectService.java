package com.jk.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.department.Department;

@Transactional
@Service
public class SubjectService {

	private final SubjectRepository subjectRepository;
	
	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	public String registerSubject(Subject subject) {
		validateSubjectForCreation(subject);
		this.subjectRepository.save(subject);
		
		return subject.getSubjectCode();
	}

	private void validateSubjectForCreation(Subject subject) {
		this.subjectRepository
			.findBySubjectCode(subject.getSubjectCode())
			.ifPresent(s -> {
				throw new IllegalStateException("이미 존재하는 과목입니다.");
			});
	}
	
	public Optional<Subject> findAllBySubjectCode(String subjectCode) {
		return this.subjectRepository.findBySubjectCode(subjectCode);
	}
	
	public List<Subject> findAllByName(String name) {
		return this.subjectRepository.findAllByName(name);
	}
	
	public List<Subject> findAllByAcademicYear(int academicYear) {
		return this.subjectRepository.findAllByAcademicYear(academicYear);
	}
	
	public List<Subject> findAllByCredit(int credit) {
		return this.subjectRepository.findAllByCredit(credit);
	}
	
	public List<Subject> findAllBySubejectType(SubjectType subjectType) {
		return this.subjectRepository.findAllBySubjectType(subjectType);
	}
	
	public List<Subject> findAllByDepartment(Department department) {
		return this.subjectRepository.findAllByDepartment(department);
	}
	
	public List<Subject> findAllSubjects() {
		return this.subjectRepository.findAll();
	}
	
	public Long deleteSubject(Subject subject) {
		this.subjectRepository.delete(subject);
		
		return subject.getId();
	}
	
	public void deleteBySubjectCode(String subjectCode) {
		this.subjectRepository.deleteBySubjectCode(subjectCode);
	}
	
	public void deleteAllByDepartment(Department department) {
		this.subjectRepository.deleteAllByDepartment(department);
	}
	
	public void deleteAllSubjects() {
		this.subjectRepository.deleteAll();
	}
} 
