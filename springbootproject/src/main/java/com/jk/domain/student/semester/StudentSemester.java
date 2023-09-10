package com.jk.domain.student.semester;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jk.domain.semester.Semester;
import com.jk.domain.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@SequenceGenerator(
		name = "STUDENT_SEMESTER_ID_SEQ_GENERATOR",
		sequenceName = "STUDENT_SEMESTER_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StudentSemester{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "STUDENT_SEMESTER_ID_SEQ_GENERATOR")
	@Column(name = "STUDENT_SEMESTER_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEMESTER_ID")
	private Semester semester;
	
	public void setStudentAndSemester(Student student, Semester semester) {
		this.student = student;
		this.semester = semester;
		
		student.addStudentSemester(this);
		semester.addStudentSemester(this);
	}
	
	private static final Set<StudentSemester> STUDENT_SEMESTERS = new HashSet<>();
	
	public static Set<StudentSemester> getStudentSemesters() {
		return Collections.unmodifiableSet(STUDENT_SEMESTERS);
	}
	
	public static void clearStudentSemesters() {
		STUDENT_SEMESTERS.clear();
	}
	
	public static StudentSemester getInstance(Student student, Semester semester, int classYear, int scholarshipRanking, int scholarship, Double totalGrade) {
		for (StudentSemester ss : STUDENT_SEMESTERS) {
			if (ss.getStudent().equals(student) && ss.getSemester().equals(semester)) {
				return ss;
			}
		}
		
		StudentSemester studentSemester = StudentSemester.builder()
														 .classYear(classYear)
														 .scholarshipRanking(scholarshipRanking)
														 .scholarship(scholarship)
														 .totalGrade(totalGrade)
														 .build();
		studentSemester.setStudentAndSemester(student, semester);
		
		STUDENT_SEMESTERS.add(studentSemester);
		
		return studentSemester;
	}
	
	public void removeStudentSemester() {
		if (STUDENT_SEMESTERS.remove(this)) {
			this.getStudent().removeStudentSemester(this);
			this.student = null;
			this.semester = null;
		}
	}
	
	private int classYear;
	
	private int scholarshipRanking;
	
	private int scholarship;
	
	private Double totalGrade;

	@Builder
	public StudentSemester(Student student, Semester semester, int classYear, int scholarshipRanking, int scholarship,
			Double totalGrade) {
		this.student = student;
		this.semester = semester;
		this.classYear = classYear;
		this.scholarshipRanking = scholarshipRanking;
		this.scholarship = scholarship;
		this.totalGrade = totalGrade;
	}
	
	public StudentSemester withClassYear(int newClassYear) {
		return StudentSemester.builder()
							  .student(getStudent())
							  .semester(getSemester())
							  .classYear(newClassYear)
							  .scholarshipRanking(getScholarshipRanking())
							  .scholarship(getScholarship())
							  .totalGrade(getTotalGrade())
							  .build();
	}
	
	public StudentSemester withScholarshipRanking(int newScholarshipRanking) {
		return StudentSemester.builder()
				  			  .student(getStudent())
				  			  .semester(getSemester())
				  			  .classYear(getClassYear())
				  			  .scholarshipRanking(newScholarshipRanking)
				  			  .scholarship(getScholarship())
				  			  .totalGrade(getTotalGrade())
				  			  .build();
	}
	
	public StudentSemester withScholarship(int newScholarship) {
		return StudentSemester.builder()
							  .student(getStudent())
							  .semester(getSemester())
							  .classYear(getClassYear())
							  .scholarshipRanking(getScholarshipRanking())
							  .scholarship(newScholarship)
							  .totalGrade(getTotalGrade())
							  .build();
	}
	
	public StudentSemester withTotalGrade(Double newTotalGrade) {
		return StudentSemester.builder()
							  .student(getStudent())
							  .semester(getSemester())
							  .classYear(getClassYear())
							  .scholarshipRanking(getScholarshipRanking())
							  .scholarship(getScholarship())
							  .totalGrade(newTotalGrade)
							  .build();
	}

	@Override
	public String toString() {
		return "StudentSemester [student=" + student + ", semester=" + semester + ", classYear=" + classYear
				+ ", scholarshipRanking=" + scholarshipRanking + ", scholarship=" + scholarship + ", totalGrade="
				+ totalGrade + "]";
	}
}
