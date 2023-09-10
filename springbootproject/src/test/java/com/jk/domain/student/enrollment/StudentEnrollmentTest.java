package com.jk.domain.student.enrollment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.department.Department;
import com.jk.domain.person.Address;
import com.jk.domain.semester.Semester;
import com.jk.domain.student.Student;
import com.jk.domain.subject.Subject;
import com.jk.domain.subject.SubjectType;
import com.jk.domain.subject.semester.Section;
import com.jk.domain.subject.semester.SubjectSemester;

public class StudentEnrollmentTest {

    private List<Student> students;
	
    private List<Department> departments;
    
    private List<Subject> subjects;

	private List<Semester> semesters; 
	
	@BeforeEach
	public void SetUp() {
		this.students
		= Arrays.asList(
				Student.builder()
				.name("김준구")
				.loginId("20170665")
				.password("jk0320")
				.phoneNumber("010-7461-5281")
				.email("junku0320@gmail.com")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("서구")
						.roGil("정림서로")
						.detailAddress("162-18, 101-1304")
						.postalCode("12345")
						.build())
				.tuition(4000000)
				.academicYear(4)
				.build(),
				
				Student.builder()
				.name("김준구")
				.loginId("20230665")
				.password("jk0321")
				.phoneNumber("010-7462-5281")
				.email("junku0321@gmail.com")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("서구")
						.roGil("정림서로")
						.detailAddress("162-18, 101-804")
						.postalCode("12345")
						.build())
				.tuition(4000000)
				.academicYear(1)
				.build(),
				
				Student.builder()
				.name("김준팔")
				.loginId("20171665")
				.password("jk0328")
				.phoneNumber("010-7461-5288")
				.email("junku0328@gmail.com")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("서구")
						.roGil("정림서로")
						.detailAddress("162-18, 102-1304")
						.postalCode("12345")
						.build())
				.tuition(3000000)
				.academicYear(3)
				.build()
				);
		
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
	}
	
	@AfterEach
	public void afterEach() {
		StudentEnrollment.clearStudentEnrollments();
	}
	
	@Test
	public void 성적_변경() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1,  "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		StudentEnrollment updatedSe1 = se1.withGrade(Grade.A_PLUS);
		
		assertThat(updatedSe1.getGrade()).isEqualTo(Grade.A_PLUS);
		assertThat(updatedSe1.getStudent()).isEqualTo(student1);
		assertThat(updatedSe1.getSubjectSemester()).isEqualTo(subjectSemester1);
	}
	
	@Test
	public void 학생_등록_양방향_연결_확인() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		
		assertThat(StudentEnrollment.getStudentEnrollments()).contains(se1);
		assertThat(StudentEnrollment.getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getStudentEnrollments()).contains(se1);
		assertThat(se1.getStudent().getStudentEnrollments()).hasSize(1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getName()).isEqualTo(this.students.get(0).getName());
		assertThat(se1.getSubjectSemester().getLimitCapacity()).isEqualTo(5);
		assertThat(se1.getSubjectSemester().getSubject().getCredit()).isEqualTo(this.subjects.get(0).getCredit());
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo(this.departments.get(0).getMajor());
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo(this.semesters.get(0).getSemesterName());
	}
	
	@Test
	public void 학생_등록_중복_확인() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		
		assertThat(StudentEnrollment.getStudentEnrollments()).contains(se1);
		assertThat(StudentEnrollment.getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getStudentEnrollments()).contains(se1);
		assertThat(se1.getStudent().getStudentEnrollments()).hasSize(1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getName()).isEqualTo(this.students.get(0).getName());
		assertThat(se1.getSubjectSemester().getLimitCapacity()).isEqualTo(5);
		assertThat(se1.getSubjectSemester().getSubject().getCredit()).isEqualTo(this.subjects.get(0).getCredit());
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo(this.departments.get(0).getMajor());
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo(this.semesters.get(0).getSemesterName());
	}
	
	@Test
	public void 학생_등록_삭제() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		se1.removeStudentEnrollment();
		
		assertThat(StudentEnrollment.getStudentEnrollments()).isEmpty();
		assertThat(se1.getStudent()).isNull();
		assertThat(se1.getSubjectSemester()).isNull();
		assertThat(student1.getStudentEnrollments()).isEmpty();
		assertThat(subjectSemester1.getStudentEnrollments()).isEmpty();
	}
	
	@Test
	public void 학생_등록_중복_삭제() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		se1.removeStudentEnrollment();
		se1.removeStudentEnrollment();
		
		assertThat(StudentEnrollment.getStudentEnrollments()).isEmpty();
		assertThat(se1.getStudent()).isNull();
		assertThat(se1.getSubjectSemester()).isNull();
		assertThat(student1.getStudentEnrollments()).isEmpty();
		assertThat(subjectSemester1.getStudentEnrollments()).isEmpty();
	}
	
	@Test
	public void 학생_등록_연관관계_수정_후_유지() {
		Student student1 = this.students.get(0);
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		StudentEnrollment se1 = StudentEnrollment.getInstance(student1, subjectSemester1);
		Student updatedStudent = student1.withName("김준십");
		se1.removeStudentEnrollment();
		se1 = StudentEnrollment.getInstance(updatedStudent, subjectSemester1);
		
		assertThat(StudentEnrollment.getStudentEnrollments()).contains(se1);
		assertThat(StudentEnrollment.getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getStudentEnrollments()).contains(se1);
		assertThat(se1.getStudent().getStudentEnrollments()).hasSize(1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).contains(se1);
		assertThat(se1.getSubjectSemester().getStudentEnrollments()).hasSize(1);
		
		assertThat(se1.getStudent().getName()).isEqualTo(updatedStudent.getName());
		assertThat(se1.getSubjectSemester().getLimitCapacity()).isEqualTo(5);
		assertThat(se1.getSubjectSemester().getSubject().getCredit()).isEqualTo(this.subjects.get(0).getCredit());
		assertThat(se1.getSubjectSemester().getSubject().getDepartment().getMajor()).isEqualTo(this.departments.get(0).getMajor());
		assertThat(se1.getSubjectSemester().getSemester().getSemesterName()).isEqualTo(this.semesters.get(0).getSemesterName());
	}
}
