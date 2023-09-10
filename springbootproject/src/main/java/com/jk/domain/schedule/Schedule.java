package com.jk.domain.schedule;

import java.util.HashSet;
import java.util.Set;

import com.jk.domain.subject.enrollment.SubjectEnrollment;

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
		name = "SCHEDULE_ID_SEQ_GENERATOR",
		sequenceName = "ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Schedule {

	private static final int MAX_DAY_OF_WEEK = 7;
	private static final int MIN_DAY_OF_WEEK = 1;
	private static final int MAX_HOUR_OF_DAY = 23;
	private static final int MIN_HOUR_OF_DAY = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SCHEDULE_ID_SEQ_GENERATOR")
	@Column(name = "SCHEDULE_ID")
	private Long id;
	
	private int dayOfWeek;
	
	private int hourOfDay;
	
	@OneToMany(mappedBy = "schedule")
	private Set<SubjectEnrollment> subjectEnrollments = new HashSet<>();
	
	public void addSubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		if (this.subjectEnrollments.add(subjectEnrollment)) {
			subjectEnrollment.getSubjectSemester().addSubjectEnrollment(subjectEnrollment);
		}
	}
	
	public void removeSubjectEnrollment(SubjectEnrollment subjectEnrollment) {
		if (this.subjectEnrollments.remove(subjectEnrollment)) {
			if (subjectEnrollment.getSubjectSemester().getSubjectEnrollments().contains(subjectEnrollment)) {
				subjectEnrollment.getSubjectSemester().removeSubjectEnrollment(subjectEnrollment);
				subjectEnrollment.removeSubjectEnrollment();
			}
		}
	}

	@Builder
	public Schedule(int dayOfWeek, int hourOfDay) {
		if (dayOfWeek < MIN_DAY_OF_WEEK || dayOfWeek > MAX_DAY_OF_WEEK || hourOfDay < MIN_HOUR_OF_DAY || hourOfDay > MAX_HOUR_OF_DAY) {
			throw new IllegalArgumentException("유효하지 않은 요일 또는 시간입니다.");
		} 
		
		this.dayOfWeek = dayOfWeek;
		this.hourOfDay = hourOfDay;
	}

	public Schedule withDayOfWeek(int newDayOfWeek) {
		return Schedule.builder()
					   .dayOfWeek(newDayOfWeek)
					   .hourOfDay(getHourOfDay())
					   .build();
	}
	
	public Schedule withHourOfDay(int newHourOfDay) {
		return Schedule.builder()
					   .dayOfWeek(getDayOfWeek())
					   .hourOfDay(newHourOfDay)
					   .build();
	}
	
	@Override
	public String toString() {
		return "Schedule [dayOfWeek=" + dayOfWeek + ", hourOfDay=" + hourOfDay + "]";
	}
}
