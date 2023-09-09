package com.jk.subject.enrollment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.department.Department;
import com.jk.schedule.Schedule;
import com.jk.semester.Semester;
import com.jk.subject.Subject;
import com.jk.subject.SubjectType;
import com.jk.subject.semester.Section;
import com.jk.subject.semester.SubjectSemester;

public class SubjectEnrollmentTest {

	private List<Department> departments;

	private List<Subject> subjects;

	private List<Semester> semesters;
	
	private List<Schedule> schedules;
	
	@BeforeEach
	public void SetUp() {
		 this.departments
			= Arrays.asList(
					Department.builder()
					.major("컴퓨터공학과")
					.faculty("컴퓨터공학부")
					.college("공과대학")
					.build(),
					
					Department.builder()
					.major("전기공학과")
					.faculty("전기및전자공학부")
					.college("공과대학")
					.build(),
					
					Department.builder()
					.major("전자공학과")
					.faculty("전기및전자공학부")
					.college("공과대학")
					.build(),
					
					Department.builder()
					.major("경제학과")
					.faculty("경제및경영학부")
					.college("상경대학")
					.build(),
					
					Department.builder()
					.major("경영학과")
					.faculty("경제및경영학부")
					.college("상경대학")
					.build()
					);
	        
	        this.subjects
			= Arrays.asList(
					Subject.builder()
					.subjectCode("00000")
					.name("컴퓨터개론")
					.academicYear(1)
					.credit(3)
					.subjectType(SubjectType.GENERAL_REQUIRED)
					.build(),
					
					Subject.builder()
					.subjectCode("11111")
					.name("거시경제학")
					.academicYear(1)
					.credit(3)
					.subjectType(SubjectType.GENERAL_ELECTIVE)
					.build(),
					
					Subject.builder()
					.subjectCode("22222")
					.name("이산수학")
					.academicYear(1)
					.credit(3)
					.subjectType(SubjectType.GENERAL_REQUIRED)
					.build(),
					
					Subject.builder()
					.subjectCode("44444")
					.name("초급프로그래밍")
					.academicYear(2)
					.credit(2)
					.subjectType(SubjectType.MAJOR_REQUIRED)
					.build(),
					
					Subject.builder()
					.subjectCode("55555")
					.name("고급프로그래밍")
					.academicYear(3)
					.credit(3)
					.subjectType(SubjectType.MAJOR_ELECTIVE)
					.build()
					);
	        
	        this.semesters
			= new ArrayList<>(Arrays.asList(
					Semester.builder()
					.semesterName("2021-1")
					.build(),
					
					Semester.builder()
					.semesterName("2021-2")
					.build(),
					
					Semester.builder()
					.semesterName("2022-1")
					.build(),
					
					Semester.builder()
					.semesterName("2022-2")
					.build(),
					
					Semester.builder()
					.semesterName("2023-1")
					.build()
					));
		this.schedules
		= Arrays.asList(
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(9)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(10)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(11)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(3)
						.hourOfDay(15)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(3)
						.hourOfDay(16)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(4)
						.hourOfDay(13)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(4)
						.hourOfDay(14)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(5)
						.hourOfDay(9)
						.build()
		);
	}
	
	@AfterEach
	public void afterEach() {
		SubjectEnrollment.clearSubjectEnrollments();
	}
	
	@Test
	public void 과목_등록_양방향_연결_확인() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		Schedule schedule1 = this.schedules.get(0);
		
		SubjectEnrollment se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		
		assertThat(SubjectEnrollment.getSubjectEnrollments()).contains(se1);
		assertThat(SubjectEnrollment.getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).hasSize(1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubject().getSubjectCode()).isEqualTo("00000");
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo("컴퓨터공학과");
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo("2021-1");
		assertThat(se1.getSchedule().getDayOfWeek()).isEqualTo(this.schedules.get(0).getDayOfWeek());
	}
	
	@Test
	public void 과목_등록_중복_확인() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		Schedule schedule1 = this.schedules.get(0);
		
		SubjectEnrollment se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		
		assertThat(SubjectEnrollment.getSubjectEnrollments()).contains(se1);
		assertThat(SubjectEnrollment.getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).hasSize(1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubject().getSubjectCode()).isEqualTo("00000");
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo("컴퓨터공학과");
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo("2021-1");
		assertThat(se1.getSchedule().getDayOfWeek()).isEqualTo(this.schedules.get(0).getDayOfWeek());
	}
	
	@Test
	public void 과목_등록_삭제() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		Schedule schedule1 = this.schedules.get(0);
		
		SubjectEnrollment se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		se1.removeSubjectEnrollment();
		
		assertThat(SubjectEnrollment.getSubjectEnrollments()).isEmpty();
		assertThat(se1.getSubjectSemester()).isNull();
		assertThat(se1.getSchedule()).isNull();
		assertThat(subjectSemester1.getSubjectEnrollments()).isEmpty();
		assertThat(schedule1.getSubjectEnrollments()).isEmpty();
	}
	
	@Test
	public void 과목_등록_중복_삭제() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		Schedule schedule1 = this.schedules.get(0);
		
		SubjectEnrollment se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		se1.removeSubjectEnrollment();
		se1.removeSubjectEnrollment();
		
		assertThat(SubjectEnrollment.getSubjectEnrollments()).isEmpty();
		assertThat(se1.getSubjectSemester()).isNull();
		assertThat(se1.getSchedule()).isNull();
		assertThat(subjectSemester1.getSubjectEnrollments()).isEmpty();
		assertThat(schedule1.getSubjectEnrollments()).isEmpty();
	}
	
	@Test
	public void 과목_등록_연관관계_수정_후_유지() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1,  "010425", 5, Section.SECTION_01);
		Schedule schedule1 = this.schedules.get(0);
		
		SubjectEnrollment se1 = SubjectEnrollment.getInstance(subjectSemester1, schedule1);
		Schedule updatedSchedule = schedule1.withDayOfWeek(7);
		se1.removeSubjectEnrollment();
		se1 = SubjectEnrollment.getInstance(subjectSemester1, updatedSchedule);
		
		assertThat(SubjectEnrollment.getSubjectEnrollments()).contains(se1);
		assertThat(SubjectEnrollment.getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getSubjectEnrollments()).hasSize(1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).contains(se1);
		assertThat(se1.getSchedule().getSubjectEnrollments()).hasSize(1);
		
		assertThat(se1.getSubjectSemester().getSubject().getSubjectCode()).isEqualTo("00000");
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo("컴퓨터공학과");
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo("2021-1");
		assertThat(se1.getSchedule().getDayOfWeek()).isEqualTo(7);
	}
}
