package com.jk.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.person.Address;

@Transactional
@SpringBootTest
public class StudentServiceTest {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentService studentService;

	List<Student> students;
	
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
	}

	@AfterEach
	void afterEach() {
		this.studentRepository.deleteAll();
	}
	
	@Test
	void 학생등록() {
		Student studentToRegister = this.students.get(0);
		
		String registerLoginId = this.studentService.registerStudent(studentToRegister);
		
		assertThat(studentToRegister.getLoginId()).isEqualTo(registerLoginId);
	}
	
	@Test
	void 중복_학생_예외() {
		Student student1 = this.students.get(0);
		Student student2 = this.students.get(0);
		
		this.studentService.registerStudent(student1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
							() -> this.studentService.registerStudent(student2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 학생입니다.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"20170665", "20230665", "20171665"})
	void 로그인_아이디_학생_조회(String loginId) {
		this.studentRepository.saveAll(this.students);
		
		Optional<Student> foundStudent = StudentUtils.filterStudentOptional(this.studentService.findByLoginId(loginId));
		
		assertThat(foundStudent.get().getLoginId()).isEqualTo(loginId);
	}

	@ParameterizedTest
	@ValueSource(strings = {"김준구", "김준팔"})
	void 이름_학생_조회(String name) {
		this.studentRepository.saveAll(this.students);
		
		List<Student> findStudents = StudentUtils.filterStudents(this.studentService.findAllByName(name));
		
		assertThat(findStudents).extracting(Student::getName).containsOnly(name);
	}
	
	@Test
	void 전체_학생_조회() {
		this.studentRepository.saveAll(this.students);
		
		List<Student> findStudents = StudentUtils.filterStudents(this.studentService.findAll());
		
		assertThat(findStudents).hasSize(this.students.size());
	}
	
	@Test
	void 학생삭제() {
		Student student = this.studentRepository.save(this.students.get(0));
		
		Long studentId = student.getId();
		Long deleteStudentId = this.studentService.deleteStudent(student);
		
		assertThat(studentId).isEqualTo(deleteStudentId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"20170665", "20230665", "20171665"})
	void 로그인_아이디_학생_삭제(String loginId) {
		this.studentRepository.saveAll(this.students);
		
		this.studentService.deleteByLoginIdStudent(loginId);
		
		List<Student> resultStudents = StudentUtils.filterStudents(this.studentRepository.findAll());
		
		assertThat(resultStudents).extracting(Student::getLoginId).doesNotContain(loginId);
	}
	
	@Test
	void 전체_학생_삭제() {
		List<Student> students = this.studentRepository.saveAll(this.students);
		
		assertThat(students).hasSize(this.students.size());
		
		this.studentService.deleteAll();
		
		assertThat(this.studentRepository.findAll()).isEmpty();
	}
}