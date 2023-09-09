package com.jk.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SemesterService {

	private SemesterRepository semesterRepository;
	
	public SemesterService(SemesterRepository semesterRepository) {
		this.semesterRepository = semesterRepository;
	}
	
	public String registerSemester(Semester semester) {
		validateSemesterForCreation(semester);
		this.semesterRepository.save(semester);
		
		return semester.getSemesterName();
	}

	private void validateSemesterForCreation(Semester semester) {
		this.semesterRepository
			.findBySemesterName(semester.getSemesterName())
			.ifPresent(s -> {
				throw new IllegalStateException("이미 존재하는 학기입니다.");
			});
	}
	
	public Optional<Semester> findBySemesterName(String semesterName) {
		return this.semesterRepository.findBySemesterName(semesterName);
	}
	
	public List<Semester> findAllSemesters() {
		return this.semesterRepository.findAll();
	}
	
	public Long deleteSemester(Semester semester) {
		this.semesterRepository.delete(semester);
		
		return semester.getId();
	}
	
	public void deleteBySemesterName(String semesterName) {
		this.semesterRepository.deleteBySemesterName(semesterName);
	}
	
	public void deleteAllSemesters() {
		this.semesterRepository.deleteAll();
	}
}
