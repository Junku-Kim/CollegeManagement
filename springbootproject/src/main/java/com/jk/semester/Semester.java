package com.jk.semester;

import java.util.HashSet;
import java.util.Set;

import com.jk.student.semester.StudentSemester;
import com.jk.subject.semester.SubjectSemester;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name = "SEMESTER_ID_SEQ_GENERATOR",
		sequenceName = "SEMESTER_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Semester {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEMESTER_ID_SEQ_GENERATOR")
	@Column(name = "SEMESTER_ID")
	private Long id;
	
	private String semesterName;

	@OneToMany(mappedBy = "semester")
	private Set<StudentSemester> studentSemesters = new HashSet<>();
	
	public void addStudentSemester(StudentSemester studentSemester) {
		if (this.studentSemesters.add(studentSemester)) {
			studentSemester.getStudent().addStudentSemester(studentSemester);
		}
	}
	
	public void removeStudentSemester(StudentSemester studentSemester) {
		if (this.studentSemesters.remove(studentSemester)) {
			if (studentSemester.getStudent().getStudentSemesters().contains(studentSemester)) {
				studentSemester.getStudent().removeStudentSemester(studentSemester);
				studentSemester.removeStudentSemester();
			}
		}
	}
	
	@OneToMany(mappedBy = "semester")
	private Set<SubjectSemester> subjectSemesters = new HashSet<>();
	
	public void addSubjectSemester(SubjectSemester subjectSemester) {
		if (this.subjectSemesters.add(subjectSemester)) {
			subjectSemester.getSubject().addSubjectSemester(subjectSemester);
		}
	}
	
	public void removeSubjectSemester(SubjectSemester subjectSemester) {
		if (this.subjectSemesters.remove(subjectSemester)) {
			if (subjectSemester.getSubject().getSubjectSemesters().contains(subjectSemester)) {
				subjectSemester.getSubject().removeSubjectSemester(subjectSemester);
				subjectSemester.removeSubjectSemester();
			}
		}
	}
	
	@Builder
	public Semester(String semesterName) {
		this.semesterName = semesterName;
	}
	
	public Semester withSemesterName(String newSemesterName) {
		return Semester.builder()
					   .semesterName(newSemesterName)
					   .build();
	}
}
