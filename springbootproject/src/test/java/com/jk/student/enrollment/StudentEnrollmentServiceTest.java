package com.jk.student.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.person.Address;
import com.jk.semester.Semester;
import com.jk.semester.SemesterRepository;
import com.jk.student.Student;
import com.jk.student.StudentRepository;
import com.jk.subject.Subject;
import com.jk.subject.SubjectRepository;
import com.jk.subject.SubjectType;
import com.jk.subject.semester.Section;
import com.jk.subject.semester.SubjectSemester;
import com.jk.subject.semester.SubjectSemesterRepository;


@Transactional
@SpringBootTest
public class StudentEnrollmentServiceTest {

	@Autowired
	StudentEnrollmentRepository studentEnrollmentRepository;
	
	@Autowired
	StudentEnrollmentService studentEnrollmentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	SemesterRepository semesterRepository;
	
	@Autowired
	SubjectSemesterRepository subjectSemesterRepository;
	
	@AfterEach
	void afterEach() {
		this.studentEnrollmentRepository.deleteAll();
		this.studentRepository.deleteAll();
		this.subjectSemesterRepository.deleteAll();
		this.subjectRepository.deleteAll();
		this.semesterRepository.deleteAll();
	}
	
	static Stream<Arguments> studentEnrollmentProvider() {
		return Stream.of(
				StudentEnrollment.getInstance(
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
				SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("00000")
						.name("컴퓨터개론")
						.academicYear(1)
						.credit(3)
						.subjectType(SubjectType.GENERAL_REQUIRED)
						.build(),
						Semester.builder()
						.semesterName("2021-1")
						.build(), "090201", 20, Section.SECTION_01)), 
				
				StudentEnrollment.getInstance(
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
						.build(),
						SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("00000")
								.name("컴퓨터개론")
								.academicYear(1)
								.credit(3)
								.subjectType(SubjectType.GENERAL_REQUIRED)
								.build(),
								Semester.builder()
								.semesterName("2021-1")
								.build(), "090201", 30, Section.SECTION_02)), 
				
				StudentEnrollment.getInstance(
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
						SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("55555")
								.name("고급프로그래밍")
								.academicYear(3)
								.credit(3)
								.subjectType(SubjectType.MAJOR_ELECTIVE)
								.build(),
								Semester.builder()
								.semesterName("2023-1")
								.build(), "090201", 10, Section.SECTION_01))
				)
				.map(Arguments::of);
	}
	
	void setUp(StudentEnrollment studentEnrollment) {
		this.studentRepository.save(studentEnrollment.getStudent());
		this.subjectRepository.save(studentEnrollment.getSubjectSemester().getSubject());
		this.semesterRepository.save(studentEnrollment.getSubjectSemester().getSemester());
		this.subjectSemesterRepository.save(studentEnrollment.getSubjectSemester());
	}
	
	void setUpAndSave(StudentEnrollment studentEnrollment) {
		setUp(studentEnrollment);
		this.studentEnrollmentRepository.save(studentEnrollment);
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void SE_등록(StudentEnrollment studentEnrollment) {
		setUp(studentEnrollment);
		
		this.studentEnrollmentService.registerStudentEnrollment(studentEnrollment);
		
		List<StudentEnrollment> foundStudentEnrollments = this.studentEnrollmentRepository.findAll();
		
		assertThat(foundStudentEnrollments).contains(studentEnrollment);
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void SE_중복_등록_예외(StudentEnrollment studentEnrollment) {
		setUp(studentEnrollment);
		
		this.studentEnrollmentService.registerStudentEnrollment(studentEnrollment);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.studentEnrollmentService.registerStudentEnrollment(studentEnrollment));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 StudentEnrollment입니다.");
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void 학생과_SS로_SE조회(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		Optional<StudentEnrollment> foundStudentEnrollmentOptional = this.studentEnrollmentService
																		 .findAllByStudentAndSubjectSemester(studentEnrollment.getStudent(), studentEnrollment.getSubjectSemester());
		
		assertThat(foundStudentEnrollmentOptional).isPresent();
		assertThat(foundStudentEnrollmentOptional.get().getStudent()).isEqualTo(studentEnrollment.getStudent());
		assertThat(foundStudentEnrollmentOptional.get().getSubjectSemester()).isEqualTo(studentEnrollment.getSubjectSemester());
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void 학생으로_SE_전체_조회(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		List<StudentEnrollment> foundStudentEnrollments = this.studentEnrollmentService
															  .findAllByStudent(studentEnrollment.getStudent());
		
		assertThat(foundStudentEnrollments).extracting(StudentEnrollment::getStudent).containsOnly(studentEnrollment.getStudent());
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void SS로_SE_전체_조회(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		List<StudentEnrollment> foundStudentEnrollments = this.studentEnrollmentService
															  .findAllBySubjectSemester(studentEnrollment.getSubjectSemester());
		
		assertThat(foundStudentEnrollments).extracting(StudentEnrollment::getSubjectSemester).containsOnly(studentEnrollment.getSubjectSemester());
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void SE로_SE_삭제(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		Long deleteId = this.studentEnrollmentService.deleteStudentEnrollment(studentEnrollment);
		
		assertThat(studentEnrollment.getId()).isEqualTo(deleteId);
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void 학생과_SS로_SE_삭제(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		this.studentEnrollmentService
			.deleteByStudentAndSubjectSemester(studentEnrollment.getStudent(), studentEnrollment.getSubjectSemester());
		
		assertThat(this.studentEnrollmentRepository.findAll()).doesNotContain(studentEnrollment);
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void 학생으로_SE_전체_삭제(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		this.studentEnrollmentService
			.deleteAllByStudent(studentEnrollment.getStudent());

		assertThat(this.studentEnrollmentRepository.findAll()).extracting(StudentEnrollment::getStudent).doesNotContain(studentEnrollment.getStudent());
	}
	
	@ParameterizedTest
	@MethodSource("studentEnrollmentProvider")
	void SS으로_SE_전체_삭제(StudentEnrollment studentEnrollment) {
		setUpAndSave(studentEnrollment);
		
		this.studentEnrollmentService
			.deleteAllBySubjectSemester(studentEnrollment.getSubjectSemester());

		assertThat(this.studentEnrollmentRepository.findAll()).extracting(StudentEnrollment::getSubjectSemester).doesNotContain(studentEnrollment.getSubjectSemester());
	}
}
