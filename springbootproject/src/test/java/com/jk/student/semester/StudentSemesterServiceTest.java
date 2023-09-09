package com.jk.student.semester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.person.Address;
import com.jk.semester.Semester;
import com.jk.semester.SemesterRepository;
import com.jk.student.Student;
import com.jk.student.StudentRepository;

@Transactional
@SpringBootTest
public class StudentSemesterServiceTest {

	@Autowired
	StudentSemesterRepository studentSemesterRepository;
	
	@Autowired
	StudentSemesterService studentSemesterService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SemesterRepository semesterRepository;
	
	List<Student> students;
	
	List<Semester> semesters;
	
	@BeforeEach
	void setUp() {
		
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

		this.semesters
		= Arrays.asList(
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
				);
	}
	
	@AfterEach
	void afterEach() {
		this.studentSemesterRepository.deleteAll();
		this.studentRepository.deleteAll();
		this.semesterRepository.deleteAll();
	}
	
	@Test
	void SS_등록() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterService.registerStudentSemester(studentSemester);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).contains(studentSemester);
		assertThat(foundStudentSemesters).hasSize(1);
	}
	
	@Test
	void SS_중복_등록_예외() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterService.registerStudentSemester(studentSemester);	
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.studentSemesterService.registerStudentSemester(studentSemester));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 StudentSemester입니다.");
	}
	
	@Test
	void 학생으로_전체_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllByStudent(savedStudent.get(0));
		
		assertThat(foundStudentSemesters).contains(studentSemester1, studentSemester2);
		assertThat(foundStudentSemesters).hasSize(2);
	}
	
	@Test
	void 없는_학생으로_전체_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllByStudent(savedStudent.get(1));
		
		assertThat(foundStudentSemesters).isEmpty();
	}
	
	@Test
	void 학기로_전체_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllBySemester(savedSemester.get(0));
		
		assertThat(foundStudentSemesters).contains(studentSemester1, studentSemester3);
		assertThat(foundStudentSemesters).hasSize(2);
	}
	
	@Test
	void 없는_학기로_전체_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllBySemester(savedSemester.get(2));
		
		assertThat(foundStudentSemesters).isEmpty();
	}
	
	@Test
	void SS로_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		Optional<StudentSemester> foundStudentSemesterOptional = this.studentSemesterService.findByStudentAndSemester(savedStudent.get(2), savedSemester.get(0));
		
		assertThat(foundStudentSemesterOptional).isPresent();
		assertThat(foundStudentSemesterOptional.get().getStudent()).isEqualTo(savedStudent.get(2));
		assertThat(foundStudentSemesterOptional.get().getSemester()).isEqualTo(savedSemester.get(0));
	}
	
	@Test
	void 없는_SS로_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		Optional<StudentSemester> foundStudentSemesterOptional = this.studentSemesterService.findByStudentAndSemester(savedStudent.get(1), savedSemester.get(0));
		
		assertThat(foundStudentSemesterOptional).isNotPresent();
	}
	
	@Test
	void 전체_SS_조회() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllStudentSemesters();
		
		assertThat(foundStudentSemesters).contains(studentSemester1, studentSemester2, studentSemester3);
		assertThat(foundStudentSemesters).hasSize(3);
	}
	
	@Test
	void 빈_전체_SS_조회() {
		List<StudentSemester> foundStudentSemesters = this.studentSemesterService.findAllStudentSemesters();
		
		assertThat(foundStudentSemesters).isEmpty();
	}
	
	@Test
	void SS_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		StudentSemester studentSemesterToDelete = studentSemester2;
		
		this.studentSemesterService.deleteStudentSemester(studentSemesterToDelete);
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).doesNotContain(studentSemesterToDelete);
		assertThat(foundStudentSemesters).hasSize(2);
	}
	
	@Test
	void SS_중복_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		StudentSemester studentSemesterToDelete = studentSemester2;
		
		this.studentSemesterService.deleteStudentSemester(studentSemesterToDelete);
		this.studentSemesterService.deleteStudentSemester(studentSemesterToDelete);
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).doesNotContain(studentSemesterToDelete);
		assertThat(foundStudentSemesters).hasSize(2);
	}
	
	@Test
	void 학생으로_SS_전체_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllByStudent(savedStudent.get(0));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(1);
		assertThat(afterDeleteStudentSemesters).contains(studentSemester3);
	}
	
	@Test
	void 학생으로_SS_전체_중복_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllByStudent(savedStudent.get(0));
		this.studentSemesterService.deleteAllByStudent(savedStudent.get(0));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(1);
		assertThat(afterDeleteStudentSemesters).contains(studentSemester3);
	}
	
	@Test
	void 없는_학생으로_SS_전체_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllByStudent(savedStudent.get(1));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(3);
		assertThat(afterDeleteStudentSemesters).isEqualTo(foundStudentSemesters);
	}
	
	@Test
	void 학기로_SS_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllBySemester(savedSemester.get(0));
		
		List<StudentSemester> afterDeleteStudentSemester = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemester).hasSize(1);
		assertThat(afterDeleteStudentSemester).contains(studentSemester2);
	}
	
	@Test
	void 학기로_SS_중복_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllBySemester(savedSemester.get(0));
		this.studentSemesterService.deleteAllBySemester(savedSemester.get(0));
		
		List<StudentSemester> afterDeleteStudentSemester = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemester).hasSize(1);
		assertThat(afterDeleteStudentSemester).contains(studentSemester2);
	}
	
	@Test
	void 없는_학기로_SS_중복_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllBySemester(savedSemester.get(2));
		
		List<StudentSemester> afterDeleteStudentSemester = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemester).hasSize(3);
		assertThat(afterDeleteStudentSemester).isEqualTo(foundStudentSemesters);
	}
	
	@Test
	void 학생과_학기로_SS_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteByStudentAndSemester(savedStudent.get(0), savedSemester.get(0));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(2);
		assertThat(afterDeleteStudentSemesters).contains(studentSemester2, studentSemester3);
	}
	
	@Test
	void 학생과_학기로_SS_중복_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteByStudentAndSemester(savedStudent.get(0), savedSemester.get(0));
		this.studentSemesterService.deleteByStudentAndSemester(savedStudent.get(0), savedSemester.get(0));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(2);
		assertThat(afterDeleteStudentSemesters).contains(studentSemester2, studentSemester3);
	}
	
	@Test
	void 없는_학생과_학기로_SS_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteByStudentAndSemester(savedStudent.get(1), savedSemester.get(1));
		
		List<StudentSemester> afterDeleteStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(afterDeleteStudentSemesters).hasSize(3);
		assertThat(afterDeleteStudentSemesters).isEqualTo(foundStudentSemesters);
	}
	
	@Test
	void 전체_SS_삭제() {
		List<Student> savedStudent = this.studentRepository.saveAll(this.students);
		List<Semester> savedSemester = this.semesterRepository.saveAll(this.semesters);
		
		StudentSemester studentSemester1 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester1);
		StudentSemester studentSemester2 = StudentSemester.getInstance(savedStudent.get(0), savedSemester.get(1), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester2);	
		StudentSemester studentSemester3 = StudentSemester.getInstance(savedStudent.get(2), savedSemester.get(0), 2, 3, 2000000, 4.13);
		this.studentSemesterRepository.save(studentSemester3);
		
		List<StudentSemester> foundStudentSemesters = this.studentSemesterRepository.findAll();
		
		assertThat(foundStudentSemesters).hasSize(3);
		
		this.studentSemesterService.deleteAllStudentSemester();
		
		assertThat(this.studentSemesterRepository.findAll()).isEmpty();
	}
}
