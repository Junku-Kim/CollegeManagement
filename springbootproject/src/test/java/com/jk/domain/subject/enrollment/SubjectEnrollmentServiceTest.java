package com.jk.domain.subject.enrollment;

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

import com.jk.domain.schedule.Schedule;
import com.jk.domain.schedule.ScheduleRepository;
import com.jk.domain.semester.Semester;
import com.jk.domain.semester.SemesterRepository;
import com.jk.domain.subject.Subject;
import com.jk.domain.subject.SubjectRepository;
import com.jk.domain.subject.SubjectType;
import com.jk.domain.subject.semester.Section;
import com.jk.domain.subject.semester.SubjectSemester;
import com.jk.domain.subject.semester.SubjectSemesterRepository;

@Transactional
@SpringBootTest
public class SubjectEnrollmentServiceTest {

	@Autowired
	SubjectEnrollmentRepository subjectEnrollmentRepository;
	
	@Autowired
	SubjectEnrollmentService subjectEnrollmentService;
	
	@Autowired
	SubjectSemesterRepository subjectSemesterRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	SemesterRepository semesterRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@AfterEach
	void afterEach() {
		this.subjectEnrollmentRepository.deleteAll();
		this.scheduleRepository.deleteAll();
		this.subjectSemesterRepository.deleteAll();
		this.subjectRepository.deleteAll();
		this.semesterRepository.deleteAll();
	}
	
	static Stream<Arguments> subjectEnrollmentProvider() {
		
		return Stream.of(
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("00000")
						.name("컴퓨터개론")
						.subjectType(SubjectType.GENERAL_REQUIRED)
						.academicYear(1)
						.credit(3)
						.build(),
						Semester.builder()
						.semesterName("2021-1")
						.build(), "090705", 23, Section.SECTION_01),
						Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(9)
						.build()),
				
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("00000")
						.name("컴퓨터개론")
						.subjectType(SubjectType.GENERAL_REQUIRED)
						.academicYear(1)
						.credit(3)
						.build(),
						Semester.builder()
						.semesterName("2021-1")
						.build(), "090705", 23, Section.SECTION_01),
						Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(10)
						.build()),
				
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("00000")
						.name("컴퓨터개론")
						.subjectType(SubjectType.GENERAL_REQUIRED)
						.academicYear(1)
						.credit(3)
						.build(),
						Semester.builder()
						.semesterName("2021-1")
						.build(), "090705", 23, Section.SECTION_01),
						Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(11)
						.build()),
				
				SubjectEnrollment.getInstance(
						SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("00000")
								.name("컴퓨터개론")
								.subjectType(SubjectType.GENERAL_REQUIRED)
								.academicYear(1)
								.credit(3)
								.build(),
								Semester.builder()
								.semesterName("2021-1")
								.build(), "090705", 27, Section.SECTION_02),
						Schedule.builder()
						.dayOfWeek(2)
						.hourOfDay(12)
						.build()),
				
				SubjectEnrollment.getInstance(
						SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("00000")
								.name("컴퓨터개론")
								.subjectType(SubjectType.GENERAL_REQUIRED)
								.academicYear(1)
								.credit(3)
								.build(),
								Semester.builder()
								.semesterName("2021-1")
								.build(), "090705", 27, Section.SECTION_02),
						Schedule.builder()
						.dayOfWeek(2)
						.hourOfDay(13)
						.build()),
				
				SubjectEnrollment.getInstance(
						SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("00000")
								.name("컴퓨터개론")
								.subjectType(SubjectType.GENERAL_REQUIRED)
								.academicYear(1)
								.credit(3)
								.build(),
								Semester.builder()
								.semesterName("2021-1")
								.build(), "090705", 27, Section.SECTION_02),
						Schedule.builder()
						.dayOfWeek(4)
						.hourOfDay(16)
						.build()),
				
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
								Subject.builder()
								.subjectCode("11111")
								.name("이산수학")
								.academicYear(1)
								.credit(2)
								.subjectType(SubjectType.GENERAL_ELECTIVE)
								.build(),
								Semester.builder()
								.semesterName("2022-1")
								.build(), "090705", 10, Section.SECTION_02),
						Schedule.builder()
						.dayOfWeek(5)
						.hourOfDay(14)
						.build()),
				
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("11111")
						.name("이산수학")
						.academicYear(1)
						.credit(2)
						.subjectType(SubjectType.GENERAL_ELECTIVE)
						.build(),
						Semester.builder()
						.semesterName("2022-1")
						.build(), "090705", 10, Section.SECTION_02),
				Schedule.builder()
				.dayOfWeek(5)
				.hourOfDay(15)
				.build()),
				
				SubjectEnrollment.getInstance(SubjectSemester.getInstance(
						Subject.builder()
						.subjectCode("11111")
						.name("이산수학")
						.academicYear(1)
						.credit(2)
						.subjectType(SubjectType.GENERAL_ELECTIVE)
						.build(),
						Semester.builder()
						.semesterName("2022-1")
						.build(), "090705", 10, Section.SECTION_02),
				Schedule.builder()
				.dayOfWeek(5)
				.hourOfDay(16)
				.build())
				)
				.map(Arguments::of);
	}

	void setUp(SubjectEnrollment subjectEnrollment) {
		this.subjectRepository.save(subjectEnrollment.getSubjectSemester().getSubject());
		this.semesterRepository.save(subjectEnrollment.getSubjectSemester().getSemester());
		this.subjectSemesterRepository.save(subjectEnrollment.getSubjectSemester());
		this.scheduleRepository.save(subjectEnrollment.getSchedule());
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SE_등록(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		
		this.subjectEnrollmentService.registerSubjectEnrollment(subjectEnrollment);
		
		List<SubjectEnrollment> foundSubjectEnrollments = this.subjectEnrollmentRepository.findAll();
		
		assertThat(foundSubjectEnrollments).contains(subjectEnrollment);
		assertThat(foundSubjectEnrollments).hasSize(1);
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SE_중복_등록_예외(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		
		this.subjectEnrollmentService.registerSubjectEnrollment(subjectEnrollment);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.subjectEnrollmentService.registerSubjectEnrollment(subjectEnrollment));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 SubjectEnrollment입니다.");
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SS와_시간표로_SE조회(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		Optional<SubjectEnrollment> foundSubjectEnrollmentOptional = this.subjectEnrollmentService
																	.findBySubjectSemesterAndSchedule(subjectEnrollment.getSubjectSemester(), subjectEnrollment.getSchedule());

		assertThat(foundSubjectEnrollmentOptional).isPresent();
		assertThat(foundSubjectEnrollmentOptional.get().getSubjectSemester()).isEqualTo(subjectEnrollment.getSubjectSemester());
		assertThat(foundSubjectEnrollmentOptional.get().getSchedule()).isEqualTo(subjectEnrollment.getSchedule());
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SS로_SE_전체_조회(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		List<SubjectEnrollment> foundSubjectEnrollments = this.subjectEnrollmentService
															  .findAllBySubjectSemester(subjectEnrollment.getSubjectSemester());
		
		assertThat(foundSubjectEnrollments).extracting(SubjectEnrollment::getSubjectSemester).containsOnly(subjectEnrollment.getSubjectSemester());
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SE로_SE_삭제(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		Long deleteId = this.subjectEnrollmentService.deleteBySubjectEnrollment(subjectEnrollment);
		
		assertThat(subjectEnrollment.getId()).isEqualTo(deleteId);
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SS와_시간표로_SE_삭제(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		this.subjectEnrollmentService.deleteBySubjectSemesterAndSchedule(subjectEnrollment.getSubjectSemester(), subjectEnrollment.getSchedule());
		List<SubjectEnrollment> foundSubjectEnrollments = this.subjectEnrollmentRepository.findAll();
		
		assertThat(foundSubjectEnrollments).doesNotContain(subjectEnrollment);
	}
	
	@ParameterizedTest
	@MethodSource("subjectEnrollmentProvider")
	void SS로_SE_전체_삭제(SubjectEnrollment subjectEnrollment) {
		setUp(subjectEnrollment);
		this.subjectEnrollmentRepository.save(subjectEnrollment);
		
		this.subjectEnrollmentService.deleteAllBySubjectSemester(subjectEnrollment.getSubjectSemester());
		List<SubjectEnrollment> foundSubjectEnrollments = this.subjectEnrollmentRepository.findAll();
		
		assertThat(foundSubjectEnrollments).extracting(SubjectEnrollment::getSubjectSemester).doesNotContain(subjectEnrollment.getSubjectSemester());
	}
}
