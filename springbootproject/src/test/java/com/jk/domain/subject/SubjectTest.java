package com.jk.domain.subject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.department.Department;

public class SubjectTest {
	
	private List<Department> departments;
	
	private List<Subject> subjects;
	
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
	}
	
	@Test
	public void 학과코드_변경() {
		Subject subject = this.subjects.get(0);
		Subject updateSubject = subject.withSubjectCode("77777");
		
		assertThat(updateSubject.getSubjectCode()).isEqualTo("77777");
	}
	
	@Test
	public void 과목이름_변경() {
		Subject subject = this.subjects.get(0);
		Subject updateSubject = subject.withName("미시경제학");
		
		assertThat(updateSubject.getName()).isEqualTo("미시경제학");
	}
	
	@Test
	public void 학년_변경() {
		Subject subject = this.subjects.get(0);
		Subject updateSubject = subject.withAcademicYear(2);
		
		assertThat(updateSubject.getAcademicYear()).isEqualTo(2);
	}
	
	@Test
	public void 학점_변경() {
		Subject subject = this.subjects.get(0);
		Subject updateSubject = subject.withCredit(2);
		
		assertThat(updateSubject.getCredit()).isEqualTo(2);
	}
	
	@Test
	public void 과목타입_변경() {
		Subject subject = this.subjects.get(0);
		Subject updateSubject = subject.withSubjectType(SubjectType.MAJOR_ELECTIVE);
		
		assertThat(updateSubject.getSubjectType()).isEqualTo(SubjectType.MAJOR_ELECTIVE);
	}
	
	@Test
	public void 학과_연결() {
		Subject subject 
		= Subject.builder()
				 .subjectCode("00000")
				 .name("컴퓨터개론")
				 .academicYear(1)
				 .credit(3)
				 .build();
		
		Department department
		= Department.builder()
					.major("컴퓨터공학과")
					.faculty("컴퓨터공학부")
					.college("공과대학")
					.build();
		
		subject.setDepartment(department);
		
		assertThat(subject.getDepartment()).isEqualTo(department);
		assertThat(department.getSubjects()).contains(subject);
		assertThat(department.getSubjects()).hasSize(1);
	}
	
	@Test
	public void 동일_학과_재연결() {
		Subject subject 
		= Subject.builder()
				 .subjectCode("00000")
				 .name("컴퓨터개론")
				 .academicYear(1)
				 .credit(3)
				 .build();
		
		Department department
		= Department.builder()
					.major("컴퓨터공학과")
					.faculty("컴퓨터공학부")
					.college("공과대학")
					.build();

		subject.setDepartment(department);
		subject.setDepartment(department);
		
		assertThat(subject.getDepartment()).isEqualTo(department);
		assertThat(department.getSubjects()).contains(subject);
		assertThat(department.getSubjects()).hasSize(1);
	}
	
	@Test
	public void 다른_학과_재연결() {
		Subject subject 
		= Subject.builder()
				 .subjectCode("00000")
				 .name("컴퓨터개론")
				 .academicYear(1)
				 .credit(3)
				 .build();
		
		Department department1
		= Department.builder()
					.major("컴퓨터공학과")
					.faculty("컴퓨터공학부")
					.college("공과대학")
					.build();
		
		Department department2
		= Department.builder()
					.major("전산학과")
					.faculty("컴퓨터공학부")
					.college("공과대학")
					.build();
		
		subject.setDepartment(department1);
		subject.setDepartment(department2);
	
		assertThat(subject.getDepartment()).isEqualTo(department2);
		assertThat(department1.getSubjects()).isEmpty();
		assertThat(department2.getSubjects()).contains(subject);
		assertThat(department2.getSubjects()).hasSize(1);
	}
	
	@Test
	public void 학과에_여러_과목_연결() {
		Subject subject1 = this.subjects.get(0);
		Subject subject2 = this.subjects.get(1);
		Department department = this.departments.get(0);
		
		subject1.setDepartment(department);
		subject2.setDepartment(department);
		
		assertThat(subject1.getDepartment()).isEqualTo(department);
		assertThat(subject2.getDepartment()).isEqualTo(department);
		assertThat(department.getSubjects()).contains(subject1, subject2);
		assertThat(department.getSubjects()).hasSize(2);
	}
	
	@Test
	public void 학과_필드_수정_후_연관관계_유지() {
		Subject subject = this.subjects.get(0);
		Department department = this.departments.get(0);
		
		subject.setDepartment(department);
		
		assertThat(subject.getDepartment().getMajor()).isEqualTo(department.getMajor());
		assertThat(department.getSubjects()).contains(subject);
		
		Department updatedDepartment = department.withMajor("전산학과");
		
		subject.setDepartment(updatedDepartment);
		
		assertThat(subject.getDepartment().getMajor()).isEqualTo(updatedDepartment.getMajor());
		assertThat(department.getSubjects()).doesNotContain(subject);
		assertThat(updatedDepartment.getSubjects()).contains(subject);
		
	}
}
