package com.jk.domain.student.semester;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.person.Address;
import com.jk.domain.semester.Semester;
import com.jk.domain.student.Student;

public class StudentSemesterTest {

	private List<Student> students;
	private List<Semester> semesters;
	
	@BeforeEach
	public void setUp() {
		this.students
		= new ArrayList<>(Arrays.asList(
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
				));
		
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
	public void clear() {
		StudentSemester.clearStudentSemesters();
	}
	
	@Test
	public void 학년_변경() {
		Student student = this.students.get(0);
		Semester semester = this.semesters.get(0);
		StudentSemester studentSemester = StudentSemester.getInstance(student, semester, 2, 5, 1000000, 3.3);
		
		StudentSemester updateStudentSemester = studentSemester.withClassYear(3);
		
		assertThat(updateStudentSemester.getClassYear()).isEqualTo(3);
		assertThat(updateStudentSemester.getStudent()).isEqualTo(student);
		assertThat(updateStudentSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 장학금_등수_변경() {
		Student student = this.students.get(0);
		Semester semester = this.semesters.get(0);
		StudentSemester studentSemester = StudentSemester.getInstance(student, semester, 2, 5, 1000000, 3.3);
		
		StudentSemester updateStudentSemester = studentSemester.withScholarshipRanking(2);
		
		assertThat(updateStudentSemester.getScholarshipRanking()).isEqualTo(2);
		assertThat(updateStudentSemester.getStudent()).isEqualTo(student);
		assertThat(updateStudentSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 장학금_변경() {
		Student student = this.students.get(0);
		Semester semester = this.semesters.get(0);
		StudentSemester studentSemester = StudentSemester.getInstance(student, semester, 2, 5, 1000000, 3.3);
		
		StudentSemester updateStudentSemester = studentSemester.withScholarship(500000);
		
		assertThat(updateStudentSemester.getScholarship()).isEqualTo(500000);
		assertThat(updateStudentSemester.getStudent()).isEqualTo(student);
		assertThat(updateStudentSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 평균_학점_변경() {
		Student student = this.students.get(0);
		Semester semester = this.semesters.get(0);
		StudentSemester studentSemester = StudentSemester.getInstance(student, semester, 2, 5, 1000000, 3.3);
		
		StudentSemester updateStudentSemester = studentSemester.withTotalGrade(3.5);
		
		assertThat(updateStudentSemester.getTotalGrade()).isEqualTo(3.5);
		assertThat(updateStudentSemester.getStudent()).isEqualTo(student);
		assertThat(updateStudentSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void SS_양방향_연결_확인() {
		Student student = this.students.get(0);
		Semester semester1 = this.semesters.get(0);
		Semester semester2 = this.semesters.get(1);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		StudentSemester studentSemester2 = StudentSemester.getInstance(student, semester2, 1, 2, 2000000, 3.5);
		
		assertThat(StudentSemester.getStudentSemesters()).contains(studentSemester1, studentSemester2);
		assertThat(StudentSemester.getStudentSemesters()).hasSize(2);
		
		assertThat(student.getStudentSemesters()).contains(studentSemester1, studentSemester2);
		assertThat(student.getStudentSemesters()).hasSize(2);
		assertThat(studentSemester1.getStudent().getStudentSemesters()).contains(studentSemester1, studentSemester2);
		assertThat(studentSemester1.getStudent().getStudentSemesters()).hasSize(2);
		
		assertThat(semester1.getStudentSemesters()).contains(studentSemester1);
		assertThat(semester1.getStudentSemesters()).hasSize(1);
		
		assertThat(semester2.getStudentSemesters()).contains(studentSemester2);
		assertThat(semester2.getStudentSemesters()).hasSize(1);
	}
	
	@Test
	public void SS_중복_등록_확인() {
		Student student = this.students.get(0);
		Semester semester1 = this.semesters.get(0);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		
		assertThat(StudentSemester.getStudentSemesters()).contains(studentSemester1);
		assertThat(StudentSemester.getStudentSemesters()).hasSize(1);
		
		assertThat(studentSemester1.getStudent().getStudentSemesters()).contains(studentSemester1);
		assertThat(studentSemester1.getStudent().getStudentSemesters()).hasSize(1);
		
		assertThat(studentSemester1.getSemester().getStudentSemesters()).contains(studentSemester1);
		assertThat(studentSemester1.getSemester().getStudentSemesters()).hasSize(1);
	}
	
	@Test
	public void SS_삭제() {
		Student student = this.students.get(0);
		Semester semester1 = this.semesters.get(0);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		
		studentSemester1.removeStudentSemester();
		
		assertThat(StudentSemester.getStudentSemesters()).isEmpty();
		assertThat(studentSemester1.getStudent()).isNull();
		assertThat(studentSemester1.getSemester()).isNull();
		assertThat(student.getStudentSemesters()).isEmpty();
		assertThat(semester1.getStudentSemesters()).isEmpty();
	}
	
	@Test
	public void SS_중복_삭제() {
		Student student = this.students.get(0);
		Semester semester1 = this.semesters.get(0);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		
		studentSemester1.removeStudentSemester();
		studentSemester1.removeStudentSemester();
		
		assertThat(StudentSemester.getStudentSemesters()).isEmpty();
		assertThat(studentSemester1.getStudent()).isNull();
		assertThat(studentSemester1.getSemester()).isNull();
		assertThat(student.getStudentSemesters()).isEmpty();
		assertThat(semester1.getStudentSemesters()).isEmpty();
	}
	
	@Test
	public void SS_연관관계_수정_후_유지() {
		Student student = this.students.get(0);
		Semester semester1 = this.semesters.get(0);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(student, semester1, 1, 2, 2000000, 3.5);
		
		Student updateStudent = student.withName("고현성");
		Semester updateSemester = semester1.withSemesterName("2023년 1학기");
		studentSemester1.removeStudentSemester();
		studentSemester1 = StudentSemester.getInstance(updateStudent, updateSemester, 4, 1, 4000000, 4.5);
		
		assertThat(studentSemester1.getStudent().getName()).isEqualTo("고현성");
		assertThat(studentSemester1.getStudent().getStudentSemesters()).contains(studentSemester1);
		assertThat(studentSemester1.getStudent().getStudentSemesters()).hasSize(1);
		assertThat(studentSemester1.getSemester().getSemesterName()).isEqualTo("2023년 1학기");
		assertThat(studentSemester1.getSemester().getStudentSemesters()).contains(studentSemester1);
		assertThat(studentSemester1.getSemester().getStudentSemesters()).hasSize(1);
		
		assertThat(StudentSemester.getStudentSemesters()).hasSize(1);
		assertThat(StudentSemester.getStudentSemesters()).contains(studentSemester1);
		
		assertThat(updateStudent.getName()).isEqualTo("고현성");
		assertThat(updateStudent.getStudentSemesters()).hasSize(1);
		assertThat(updateStudent.getStudentSemesters()).contains(studentSemester1);
		
		assertThat(student.getStudentSemesters()).isEmpty();
		
		assertThat(updateSemester.getSemesterName()).isEqualTo("2023년 1학기");
		assertThat(updateSemester.getStudentSemesters()).hasSize(1);
		assertThat(updateSemester.getStudentSemesters()).contains(studentSemester1);
		
		assertThat(semester1.getStudentSemesters()).isEmpty();
	}
}
