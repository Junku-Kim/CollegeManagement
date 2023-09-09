package com.jk.subject;

import java.util.HashSet;
import java.util.Set;

import com.jk.department.Department;
import com.jk.subject.semester.SubjectSemester;

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
		name = "SUBJECT_ID_SEQ_GENERATOR",
		sequenceName = "SUBJECT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SUBJECT_ID_SEQ_GENERATOR")
	@Column(name = "SUBJECT_ID")
	private Long id;
	
	private String subjectCode;
	
	private String name;
	
	private int academicYear;
	
	private int credit;
	
	@Enumerated(EnumType.STRING)
	private SubjectType subjectType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;
	
	public void setDepartment(Department department) {
		if (this.department == null) {
			this.department = department;
			this.department.addSubject(this);
		} else if (!this.department.equals(department)) {
			this.department.removeSubject(this);
			this.department = department;
			this.department.addSubject(this);
		}
	}
	
	@OneToMany(mappedBy = "subject")
	private Set<SubjectSemester> subjectSemesters = new HashSet<>();
	
	public void addSubjectSemester(SubjectSemester subjectSemester) {
		if (this.subjectSemesters.add(subjectSemester)) {
			subjectSemester.getSemester().addSubjectSemester(subjectSemester);
		}
	}
	
	public void removeSubjectSemester(SubjectSemester subjectSemester) {
		if (this.subjectSemesters.remove(subjectSemester)) {
			if (subjectSemester.getSemester().getSubjectSemesters().contains(subjectSemester)) {
				subjectSemester.getSemester().removeSubjectSemester(subjectSemester);
				subjectSemester.removeSubjectSemester();
			}
		}
	}

	@Builder
	public Subject(String subjectCode, String name, int academicYear, int credit, SubjectType subjectType) {
		this.subjectCode = subjectCode;
		this.name = name;
		this.academicYear = academicYear;
		this.credit = credit;
		this.subjectType = subjectType;
	}
	
	public Subject withSubjectCode(String newSubjectCode) {
		return Subject.builder()
					  .subjectCode(newSubjectCode)
					  .name(getName())
					  .academicYear(getAcademicYear())
					  .credit(getCredit())
					  .subjectType(getSubjectType())
					  .build();
	}

	public Subject withName(String newName) {
	    return Subject.builder()
	                  .subjectCode(getSubjectCode())
	                  .name(newName)
	                  .academicYear(getAcademicYear())
	                  .credit(getCredit())
	                  .subjectType(getSubjectType())
	                  .build();
	}

	public Subject withAcademicYear(int newAcademicYear) {
	    return Subject.builder()
	                  .subjectCode(getSubjectCode())
	                  .name(getName())
	                  .academicYear(newAcademicYear)
	                  .credit(getCredit())
	                  .subjectType(getSubjectType())
	                  .build();
	}

	public Subject withCredit(int newCredit) {
	    return Subject.builder()
	                  .subjectCode(getSubjectCode())
	                  .name(getName())
	                  .academicYear(getAcademicYear())
	                  .credit(newCredit)
	                  .subjectType(getSubjectType())
	                  .build();
	}

	public Subject withSubjectType(SubjectType newSubjectType) {
	    return Subject.builder()
	                  .subjectCode(getSubjectCode())
	                  .name(getName())
	                  .academicYear(getAcademicYear())
	                  .credit(getCredit())
	                  .subjectType(newSubjectType)
	                  .build();
	}

	@Override
	public String toString() {
		return "Subject [subjectCode=" + subjectCode + ", name=" + name + ", academicYear=" + academicYear + ", credit="
				+ credit + ", subjectType=" + subjectType + "]";
	}
}
