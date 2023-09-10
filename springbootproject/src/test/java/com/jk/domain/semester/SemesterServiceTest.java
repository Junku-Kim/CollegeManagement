package com.jk.domain.semester;

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

@Transactional
@SpringBootTest
class SemesterServiceTest {

	@Autowired
	SemesterRepository semesterRepository;
	
	@Autowired
	SemesterService semesterService;
	
	List<Semester> semesters;
	
	@BeforeEach
	void setUp() {
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
		this.semesterRepository.deleteAll();
	}
	
	@Test
	void 학기_등록() {
		Semester semesterToRegister = this.semesters.get(0);
		
		String findSemesterName = this.semesterService.registerSemester(semesterToRegister);
		
		assertThat(semesterToRegister.getSemesterName()).isEqualTo(findSemesterName);
	}
	
	@Test
	void 중복_학기_예외() {
		Semester semesterToRegister1 = this.semesters.get(0);
		Semester semesterToRegister2 = this.semesters.get(0);
		
		this.semesterService.registerSemester(semesterToRegister1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.semesterService.registerSemester(semesterToRegister2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 학기입니다.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"2021-1", "2021-2", "2022-1", "2022-2", "2023-1"})
	void 학기이름_학기_조회(String semesterName) {
		this.semesterRepository.saveAll(this.semesters);
		
		Optional<Semester> findSemesterOptional = this.semesterService.findBySemesterName(semesterName);
		
		assertThat(findSemesterOptional).isPresent();
		assertThat(findSemesterOptional.get().getSemesterName()).isEqualTo(semesterName);
	}
	
	@Test
	void 전체_학기_조회() {
		this.semesterRepository.saveAll(this.semesters);
		
		List<Semester> allSemesters = this.semesterService.findAllSemesters();
		
		assertThat(allSemesters).hasSize(this.semesters.size());
	}
	
	@Test
	void 학기_삭제() {
		Semester semester = this.semesterRepository.save(this.semesters.get(0));
		
		Long semesterId = semester.getId();
		Long deleteSemesterId = this.semesterService.deleteSemester(semester);
		
		assertThat(semesterId).isEqualTo(deleteSemesterId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"2021-1", "2021-2", "2022-1", "2022-2", "2023-1"})
	void 학기이름_학기_삭제(String semesterName) {
		this.semesterRepository.saveAll(this.semesters);
		
		this.semesterService.deleteBySemesterName(semesterName);
		
		List<Semester> remainSemesters = this.semesterRepository.findAll();
		
		assertThat(remainSemesters).extracting(Semester::getSemesterName).doesNotContain(semesterName);
	}
	
	@Test
	void 전체_학기_삭제() {
		List<Semester> semesters = this.semesterRepository.saveAll(this.semesters);
		
		assertThat(semesters).hasSize(this.semesters.size());
		
		this.semesterService.deleteAllSemesters();
		
		assertThat(this.semesterRepository.findAll()).isEmpty();
	}
}
