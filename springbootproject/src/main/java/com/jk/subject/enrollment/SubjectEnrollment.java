package com.jk.subject.enrollment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jk.schedule.Schedule;
import com.jk.subject.semester.SubjectSemester;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name = "SUBJECT_ENROLLMENT_ID_SEQ_GENERATOR",
		sequenceName = "SUBJECT_ENROLLMENT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class SubjectEnrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SUBJECT_ENROLLMENT_ID_SEQ_GENERATOR")
	@Column(name = "SUBJECT_ENROLLMENT_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_SEMESTER_ID")
	private SubjectSemester subjectSemester;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHEDULE_ID")
	private Schedule schedule;
	
	public void setSubjectSemesterAndSchedule(SubjectSemester subjectSemester, Schedule schedule) {
		this.subjectSemester = subjectSemester;
		this.schedule = schedule;
		
		subjectSemester.addSubjectEnrollment(this);
		schedule.addSubjectEnrollment(this);
	}
	
	private static final Set<SubjectEnrollment> SUBJECT_ENROLLMENTS = new HashSet<>();
	
	public static Set<SubjectEnrollment> getSubjectEnrollments() {
		return Collections.unmodifiableSet(SUBJECT_ENROLLMENTS);
	}
	
	public static void clearSubjectEnrollments() {
		SUBJECT_ENROLLMENTS.clear();
	}
	
	public static SubjectEnrollment getInstance(SubjectSemester subjectSemester, Schedule schedule) {
		for (SubjectEnrollment ss : SUBJECT_ENROLLMENTS) {
			if (ss.getSubjectSemester().equals(subjectSemester) && ss.getSchedule().equals(schedule)) {
				return ss;
			}
		}
		
		SubjectEnrollment subjectEnrollment = new SubjectEnrollment();
		subjectEnrollment.setSubjectSemesterAndSchedule(subjectSemester, schedule);
		
		SUBJECT_ENROLLMENTS.add(subjectEnrollment);
		
		return subjectEnrollment;
	}
	
	public void removeSubjectEnrollment() {
		if (SUBJECT_ENROLLMENTS.remove(this)) {
			this.getSubjectSemester().removeSubjectEnrollment(this);
			this.subjectSemester = null;
			this.schedule = null;
		}
	}
}
