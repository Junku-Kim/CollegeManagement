package com.jk.subject.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.professor.Professor;
import com.jk.semester.Semester;
import com.jk.subject.Subject;

@Transactional
@Service
public class SubjectSemesterService {

	private final SubjectSemesterRepository subjectSemesterRepository;
	
	public SubjectSemesterService(SubjectSemesterRepository subjectSemesterRepository) {
		this.subjectSemesterRepository = subjectSemesterRepository;
	}
	
	public Long registerSubjectSemester(SubjectSemester subjectSemester) {
		validateSubjectSemesterForCreation(subjectSemester);
		this.subjectSemesterRepository.save(subjectSemester);
		
		return subjectSemester.getId();
	}

	private void validateSubjectSemesterForCreation(SubjectSemester subjectSemester) {
		this.subjectSemesterRepository
			.findBySubjectAndSemesterAndSection(subjectSemester.getSubject(), subjectSemester.getSemester(), subjectSemester.getSection())
			.ifPresent(ss -> {
				throw new IllegalStateException("이미 존재하는 SubjectSemester입니다.");
			});
	}

	public List<SubjectSemester> findAllBySemester(Semester semester) {
		return this.subjectSemesterRepository.findAllBySemester(semester);
	}

	public List<SubjectSemester> findAllBySubject(Subject subject) {
		return this.subjectSemesterRepository.findAllBySubject(subject);
	}
	
	public List<SubjectSemester> findAllBySubjectAndSemester(Subject subject, Semester semester) {
		return this.subjectSemesterRepository.findAllBySubjectAndSemester(subject, semester);
	}

	public Optional<SubjectSemester> findBySubjectAndSemesterAndSection(Subject subject, Semester semester, Section section) {
		return this.subjectSemesterRepository.findBySubjectAndSemesterAndSection(subject, semester, section);
	}
	
	public List<SubjectSemester> findAllByProfessor(Professor professor) {
		return this.subjectSemesterRepository.findAllByProfessor(professor);
	}

	public List<SubjectSemester> findAllSubjectSemesters() {
		return this.subjectSemesterRepository.findAll();
	}

	public Long deleteSubjectSemester(SubjectSemester subjectSemester) {
		this.subjectSemesterRepository.delete(subjectSemester);

		return subjectSemester.getId();
	}

	public void deleteAllBySemester(Semester semester) {
		this.subjectSemesterRepository.deleteAllBySemester(semester);
	}

	public void deleteAllBySubject(Subject subject) {
		this.subjectSemesterRepository.deleteAllBySubject(subject);
	}

	public void deleteAllBySubjectAndSemester(Subject subject, Semester semester) {
		this.subjectSemesterRepository.deleteAllBySubjectAndSemester(subject, semester);
	}
	
	public void deleteBySubjectAndSemesterAndSection(Subject subject, Semester semester, Section section) {
		this.subjectSemesterRepository.deleteBySubjectAndSemesterAndSection(subject, semester, section);
	}
	
	public void deleteAllByProfessor(Professor professor) {
		this.subjectSemesterRepository.deleteAllByProfessor(professor);
	}

	public void deleteAllSubjectSemesters() {
		this.subjectSemesterRepository.deleteAll();
	}
}
