package com.jk.domain.student.enrollment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jk.domain.student.Student;
import com.jk.domain.subject.semester.SubjectSemester;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(
		name = "STUDENT_ENROLLMENT_ID_SEQ_GENERATOR",
		sequenceName = "STUDENT_ENROLLMENT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudentEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "STUDENT_ENROLLMENT_ID_SEQ_GENERATOR")
	@Column(name = "STUDENT_ENROLLMENT_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_SEMESTER_ID")
	private SubjectSemester subjectSemester;
	
	public void setStudentAndSubjectSemester(Student student, SubjectSemester subjectSemester) {
		this.student = student;
		this.subjectSemester = subjectSemester;
		
		student.addStudentEnrollment(this);
		subjectSemester.addStudentEnrollment(this);
	}
	
	private static final Set<StudentEnrollment> STUDENT_ENROLLMENTS = new HashSet<>();
	
	public static Set<StudentEnrollment> getStudentEnrollments() {
		return Collections.unmodifiableSet(STUDENT_ENROLLMENTS);
	}
	
	public static void clearStudentEnrollments() {
		STUDENT_ENROLLMENTS.clear();
	}
	
	public static StudentEnrollment getInstance(Student student, SubjectSemester subjectSemester) {
		for (StudentEnrollment se : STUDENT_ENROLLMENTS) {
			if (se.getStudent().equals(student) && se.getSubjectSemester().equals(subjectSemester)) {
				return se;
			}
		}
		
		StudentEnrollment studentEnrollment = new StudentEnrollment();
		studentEnrollment.setStudentAndSubjectSemester(student, subjectSemester);
		
		STUDENT_ENROLLMENTS.add(studentEnrollment);
		
		return studentEnrollment;
	}
	
	public void removeStudentEnrollment() {
		if (STUDENT_ENROLLMENTS.remove(this)) {
			this.getStudent().removeStudentEnrollment(this);
			this.student = null;
			this.subjectSemester = null;
		}
	}
	
	@Enumerated(EnumType.STRING)
	private Grade grade;

	@Builder
	public StudentEnrollment(Student student, SubjectSemester subjectSemester, Grade grade) {
		this.student = student;
		this.subjectSemester = subjectSemester;
		this.grade = grade;
	}
	
	public StudentEnrollment withGrade(Grade newGrade) {
		return StudentEnrollment.builder()
								.student(getStudent())
								.subjectSemester(getSubjectSemester())
								.grade(newGrade)
								.build();
	}

	@Override
	public String toString() {
		return "StudentEnrollment [student=" + student + ", subjectSemester=" + subjectSemester + ", grade=" + grade
				+ "]";
	}
}