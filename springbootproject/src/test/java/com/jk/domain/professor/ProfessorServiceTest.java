package com.jk.domain.professor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jk.domain.person.Address;
import com.jk.domain.person.Person;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class ProfessorServiceTest {

	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	ProfessorService professorService;
	
	List<Professor> professors;
	
	@BeforeEach
	void setUp() {
		this.professors
		= Arrays.asList(
				Professor.builder()
				.name("김교수")
				.loginId("professor000")
				.password("pw000")
				.phoneNumber("010-0000-0000")
				.email("professor000@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("대덕구")
						.roGil("신탄진로")
						.detailAddress("789-8, 8층")
						.postalCode("67898")
						.build()
						)
				.salary(5000000)
				.location("090703")
				.officeNumber("042-000-0000")
				.rank(Rank.ASSISTANT_PROFESSOR)
				.build(),
				
				Professor.builder()
				.name("이교수")
				.loginId("professor111")
				.password("pw111")
				.phoneNumber("010-2222-2222")
				.email("professor222@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("대덕구")
						.roGil("신탄진로")
						.detailAddress("789-7, 7층")
						.postalCode("67897")
						.build()
						)
				.salary(6000000)
				.location("110703")
				.officeNumber("042-111-1111")
				.rank(Rank.ASSOCIATE_PROFESSOR)
				.build(),
				
				Professor.builder()
				.name("박교수")
				.loginId("professor222")
				.password("pw222")
				.phoneNumber("010-2222-2222")
				.email("professor222@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("대덕구")
						.roGil("신탄진로")
						.detailAddress("789-6, 6층")
						.postalCode("67896")
						.build()
						)
				.salary(7000000)
				.location("090703")
				.officeNumber("042-222-2222")
				.rank(Rank.FULL_PROFESSOR)
				.build(),
				
				Professor.builder()
				.name("김교수")
				.loginId("professor333")
				.password("pw333")
				.phoneNumber("010-3333-3333")
				.email("professor333@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("대덕구")
						.roGil("신탄진로")
						.detailAddress("789-5, 5층")
						.postalCode("67895")
						.build()
						)
				.salary(3500000)
				.location("090721")
				.officeNumber("042-333-3333")
				.rank(Rank.PART_TIME_LECTURER)
				.build(),
				
				Professor.builder()
				.name("최교수")
				.loginId("professor444")
				.password("pw444")
				.phoneNumber("010-4444-4444")
				.email("professor444@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("대덕구")
						.roGil("신탄진로")
						.detailAddress("789-4, 4층")
						.postalCode("67894")
						.build()
						)
				.salary(7000000)
				.location("110703")
				.officeNumber("042-444-4444")
				.rank(Rank.FULL_PROFESSOR)
				.build()
				);
	}
	
	
	@AfterEach
	void afterEach() {
		this.professorRepository.deleteAll();
	}
	
	@Test
	void 교수등록() {
		Professor professorToResister = this.professors.get(0);
		
		String resisterLoginId = this.professorService.registerProfessor(professorToResister);
		
		assertThat(professorToResister.getLoginId()).isEqualTo(resisterLoginId);
	}
	
	@Test
	void 중복_교수_예외() {
		Professor professor1 = this.professors.get(0);
		Professor professor2 = this.professors.get(0);

		this.professorService.registerProfessor(professor1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.professorService.registerProfessor(professor2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 교수입니다.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"professor000", "professor111", "professor222", "professor333", "professor444"})
	void 로그인_아이디_교수_조회(String loginId) {
		this.professorRepository.saveAll(this.professors);
		
		Optional<Person> findPersonOptional = this.professorService.findByLoginId(loginId);
		Optional<Professor> findProfessorOptional = ProfessorUtils.filterProfessorOptional(findPersonOptional);
		
		assertThat(findProfessorOptional.get().getLoginId()).isEqualTo(loginId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"김교수", "이교수", "박교수", "최교수"})
    void 이름_교수_조회(String name) {
		this.professorRepository.saveAll(this.professors);
		
		List<Professor> findProfessors = ProfessorUtils.filterProfessors(this.professorService.findAllByName(name));

        assertThat(findProfessors).extracting(Professor::getName).containsOnly(name);
    }

	@ParameterizedTest
	@EnumSource(value = Rank.class, names = {"ASSISTANT_PROFESSOR", "ASSOCIATE_PROFESSOR", "FULL_PROFESSOR", "PART_TIME_LECTURER"})
	void 직급_교수_조회(Rank rank) {
		this.professorRepository.saveAll(this.professors);

		List<Professor> findProfessors = this.professorService
											 .findAllByRankProfessor(rank);
		
		assertThat(findProfessors).extracting(Professor::getRank).containsOnly(rank);
	}
	
	@Test
    void 전체_교수_조회() {
		this.professorRepository.saveAll(this.professors);

        List<Professor> findProfessors = ProfessorUtils.filterProfessors(this.professorService.findAll());

        assertThat(findProfessors).hasSize(5);
    }
	
	@Test
	void 교수삭제() {
		Professor professor = this.professorRepository.save(this.professors.get(0));
		
		Long professorId = professor.getId();
		Long deleteProfessorId = this.professorService.deleteProfessor(professor);
		
		assertThat(professorId).isEqualTo(deleteProfessorId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"professor000", "professor111", "professor222", "professor333", "professor444"})
	void 로그인_아이디_교수_삭제(String loginId) {
		this.professorRepository.saveAll(this.professors);
		
		this.professorService.deleteByLoginId(loginId);
		
		List<Professor> resultProfessors = ProfessorUtils.filterProfessors(this.professorRepository.findAll());
		
		assertThat(resultProfessors).extracting(Professor::getLoginId).doesNotContain(loginId);
	}
	
	@Test
	void 전체_교수_삭제() {
		List<Professor> professors = this.professorRepository.saveAll(this.professors);
		
		assertThat(professors).hasSize(this.professors.size());
		
		this.professorService.deleteAll();
		
		assertThat(this.professorRepository.findAll()).isEmpty();
	}
}
