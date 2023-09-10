package com.jk.domain.subject.semester;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jk.domain.professor.Professor;
import com.jk.domain.semester.Semester;
import com.jk.domain.student.enrollment.StudentEnrollment;
import com.jk.domain.subject.Subject;
import com.jk.domain.subject.enrollment.SubjectEnrollment;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name = "SUBJECT_SEMESTER_ID_SEQ_GENERATOR",
		sequenceName = "SUBJECT_SEMESTER_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class SubjectSemester {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SUBJECT_SEMESTER_ID_SEQ_GENERATOR")
	@Column(name = "SUBJECT_SEMESTER_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID")
	private Subject subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEMESTER_ID")
	private Semester semester;
	
	private void setSubjectAndSemester(Subject subject, Semester semester) {
		this.subject = subject;
		this.semester = semester;
		
		subject.addSubjectSemester(this);
		semester.addSubjectSemester(this);
	}
	
	private static final Set<SubjectSemester> SUBJECT_SEMESTERS = new HashSet<>();
	
	public static Set<SubjectSemester> getSubjectSemesters() {
		return Collections.unmodifiableSet(SUBJECT_SEMESTERS);
	}
	
	public static void clearSubjectSemesters() {
		SUBJECT_SEMESTERS.clear();
	}
	
	public static SubjectSemester getInstance(Subject subject, Semester semester, String classroomLocation, int limitCapacity, Section section) {
		for (SubjectSemester ss : SUBJECT_SEMESTERS) {
			if (ss.getSubject().equals(subject) && ss.getSemester().equals(semester) && ss.getSection().equals(section)) {
				return ss;
			}
		}
		
		SubjectSemester subjectSemester = SubjectSemester.builder()
													     .classroomLocation(classroomLocation)
														 .limitCapacity(limitCapacity)
														 .section(section)
														 .build();
		subjectSemester.setSubjectAndSemester(subject, semester);
		
		SUBJECT_SEMESTERS.add(subjectSemester);
		
		return subjectSemester;
	}
	
	public void removeSubjectSemester() {
		if (SUBJECT_SEMESTERS.remove(this)) {
			this.getSubject().removeSubjectSemester(this);
			this.subject = null;
			this.semester = null;
		}
	}
	
	@OneToMany(mappedBy = "subjectSemester")
	private Set<SubjectEnrollment> subjectEnrollments = new HashSet<>();
	
	public void addSubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		if (this.subjectEnrollments.add(subjectEnrollment)) {
			subjectEnrollment.getSchedule().addSubjectEnrollment(subjectEnrollment);
		}
	}
	
	public void removeSubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		if (this.subjectEnrollments.remove(subjectEnrollment)) {
			if (subjectEnrollment.getSchedule().getSubjectEnrollments().contains(subjectEnrollment)) {
				subjectEnrollment.getSchedule().removeSubjectEnrollment(subjectEnrollment);
				subjectEnrollment.removeSubjectEnrollment();
			}
		}
	}
	
	@OneToMany(mappedBy = "subjectSemester")
	private Set<StudentEnrollment> studentEnrollments = new HashSet<>();
	
	public void addStudentEnrollment(StudentEnrollment studentEnrollment) {
		if (this.studentEnrollments.add(studentEnrollment)) {
			studentEnrollment.getStudent().addStudentEnrollment(studentEnrollment);
		}
	}
	
	public void removeStudentEnrollment(StudentEnrollment studentEnrollment) {
		if (this.studentEnrollments.remove(studentEnrollment)) {
			if (studentEnrollment.getStudent().getStudentEnrollments().contains(studentEnrollment)) {
				studentEnrollment.getStudent().removeStudentEnrollment(studentEnrollment);
				studentEnrollment.removeStudentEnrollment();
			}
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private Professor professor;
	
	public void setProfessor(Professor professor) {
		if (this.professor == null) {
			this.professor = professor;
			this.professor.addSubjectSemester(this);
		} else if (!this.professor.equals(professor)) {
			this.professor.removeSubjectSemester(this);
			this.professor = professor;
			this.professor.addSubjectSemester(this);
		}
	}
	
	private String classroomLocation;
	
	private int limitCapacity;
	
	@Enumerated(EnumType.STRING)
	private Section section;
	
	@Builder
	public SubjectSemester(Subject subject, Semester semester, String classroomLocation,
			int limitCapacity, Section section) {
		this.subject = subject;
		this.semester = semester;
		this.classroomLocation = classroomLocation;
		this.limitCapacity = limitCapacity;
		this.section = section;
	}
	
	public SubjectSemester withClassroomLocation(String newClassroomLocation) {
	    return SubjectSemester.builder()
	    					  .subject(getSubject())
				              .semester(getSemester())
	                          .classroomLocation(newClassroomLocation)
	                          .limitCapacity(getLimitCapacity())
	                          .section(getSection())
	                          .build();
	}

	public SubjectSemester withLimitCapacity(int newLimitCapacity) {
	    return SubjectSemester.builder()
	    					  .subject(getSubject())
	    					  .semester(getSemester())
	                          .classroomLocation(getClassroomLocation())
	                          .limitCapacity(newLimitCapacity)
	                          .section(getSection())
	                          .build();
	}

	public SubjectSemester withSection(Section newSection) {
	    return SubjectSemester.builder()
	    					  .subject(getSubject())
	    					  .semester(getSemester())
	                          .classroomLocation(getClassroomLocation())
	                          .limitCapacity(getLimitCapacity())
	                          .section(newSection)
	                          .build();
	}

}
