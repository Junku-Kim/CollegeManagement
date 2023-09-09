package com.jk.subject.semester;

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
import com.jk.professor.Professor;
import com.jk.professor.ProfessorRepository;
import com.jk.professor.Rank;
import com.jk.semester.Semester;
import com.jk.semester.SemesterRepository;
import com.jk.subject.Subject;
import com.jk.subject.SubjectRepository;
import com.jk.subject.SubjectType;

@Transactional
@SpringBootTest
public class SubjectSemesterServiceTest {
	
	@Autowired
	SubjectSemesterRepository subjectSemesterRepository;
	
	@Autowired
	SubjectSemesterService subjectSemesterService;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	SemesterRepository semesterRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	List<Subject> subjects;
	
	List<Semester> semesters;
	
	List<Professor> professors;
	
	@BeforeEach
	void setUp() {
		this.subjects
		= Arrays.asList(
				Subject.builder()
				.subjectCode("00000")
				.name("컴퓨터개론")
				.subjectType(SubjectType.GENERAL_REQUIRED)
				.academicYear(1)
				.credit(3)
				.build(),
				
				Subject.builder()
				.subjectCode("11111")
				.name("이산수학")
				.academicYear(1)
				.credit(2)
				.subjectType(SubjectType.GENERAL_ELECTIVE)
				.build(),
				
				Subject.builder()
				.subjectCode("22222")
				.name("데이터베이스")
				.academicYear(4)
				.credit(3)
				.subjectType(SubjectType.MAJOR_ELECTIVE)
				.build(),
				
				Subject.builder()
				.subjectCode("33333")
				.name("미시경제학")
				.academicYear(2)
				.credit(3)
				.subjectType(SubjectType.MAJOR_ELECTIVE)
				.build(),
				
				Subject.builder()
				.subjectCode("44444")
				.name("초급프로그래밍")
				.subjectType(SubjectType.MAJOR_REQUIRED)
				.academicYear(2)
				.credit(2)
				.build(),
				
				Subject.builder()
				.subjectCode("55555")
				.name("고급프로그래밍")
				.subjectType(SubjectType.MAJOR_ELECTIVE)
				.academicYear(3)
				.credit(3)
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
		this.subjectSemesterRepository.deleteAll();
		this.subjectRepository.deleteAll();
		this.semesterRepository.deleteAll();
	}

	@Test
	void SS_등록() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).contains(subjectSemester);
		assertThat(foundSubjectSemesters).hasSize(1);
	}
	
	@Test
	void SS_중북_등록_예외() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.subjectSemesterService.registerSubjectSemester(subjectSemester));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 SubjectSemester입니다.");
	}
	
	@Test
	void 과목으로_전체_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySubject(savedSubjects.get(0));
		
		assertThat(foundSubjectSemesters).contains(subjectSemester1, subjectSemester2);
		assertThat(foundSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 없는_과목으로_전체_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySubject(savedSubjects.get(3));
		
		assertThat(foundSubjectSemesters).isEmpty();
	}
	
	@Test
	void 학기로_전체_SS_조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySemester(savedSemesters.get(0));
		
		assertThat(foundSubjectSemesters).contains(subjectSemester1, subjectSemester2, subjectSemester3);
		assertThat(foundSubjectSemesters).hasSize(3);
	}
	
	@Test
	void 없는_학기로_전체_SS_조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySemester(savedSemesters.get(2));
		
		assertThat(foundSubjectSemesters).isEmpty();
	}
	
	@Test
	void 과목_학기로_전체_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySubjectAndSemester(savedSubjects.get(0), savedSemesters.get(0));
		
		assertThat(foundSubjectSemesters).contains(subjectSemester1, subjectSemester2);
		assertThat(foundSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 없는_과목_학기로_전체_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllBySubjectAndSemester(savedSubjects.get(2), savedSemesters.get(0));
		
		assertThat(foundSubjectSemesters).isEmpty();
	}
	
	@Test
	void SS로_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		Optional<SubjectSemester> foundSubjectSemesterOptional = this.subjectSemesterService.findBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_02);
		
		assertThat(foundSubjectSemesterOptional).isPresent();
		assertThat(foundSubjectSemesterOptional.get().getSubject()).isEqualTo(savedSubjects.get(0));
		assertThat(foundSubjectSemesterOptional.get().getSemester()).isEqualTo(savedSemesters.get(0));
		assertThat(foundSubjectSemesterOptional.get().getSection()).isEqualTo(Section.SECTION_02);
	}
	
	@Test
	void 없는_SS로_SS조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		Optional<SubjectSemester> foundSubjectSemesterOptional = this.subjectSemesterService.findBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_03);
		
		assertThat(foundSubjectSemesterOptional).isNotPresent();
	}
	
	@Test
	void 교수로_SS_조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		List<Professor> savedProfessors = this.professorRepository.saveAll(this.professors);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		
		List<SubjectSemester> subjectSemesters = Arrays.asList(subjectSemester1, subjectSemester2, subjectSemester3, subjectSemester4);
		
		for (int i = 0; i < subjectSemesters.size(); i++) {
			if (i % 2 == 0) {
				subjectSemesters.get(i).setProfessor(savedProfessors.get(0));
			} else if (i % 2 == 1) {
				subjectSemesters.get(i).setProfessor(savedProfessors.get(1));
			}
		}
		
		this.subjectSemesterRepository.saveAll(subjectSemesters);
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllByProfessor(savedProfessors.get(0));
		
		assertThat(foundSubjectSemesters).extracting(SubjectSemester::getProfessor).containsOnly(savedProfessors.get(0));
	}
	
	@Test
	void 전체_SS_조회() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllSubjectSemesters();
		
		assertThat(foundSubjectSemesters).contains(subjectSemester1, subjectSemester2, subjectSemester3, subjectSemester4);
		assertThat(foundSubjectSemesters).hasSize(4);
	}
	
	@Test
	void 빈_전체_SS_조회() {
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterService.findAllSubjectSemesters();
		
		assertThat(foundSubjectSemesters).isEmpty();
	}
	
	@Test
	void SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		SubjectSemester subjectSemesterToDelete = subjectSemester2;
		
		this.subjectSemesterService.deleteSubjectSemester(subjectSemesterToDelete);
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).doesNotContain(subjectSemesterToDelete);
		assertThat(foundSubjectSemesters).hasSize(3);
	}
	
	@Test
	void SS_중복_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		SubjectSemester subjectSemesterToDelete = subjectSemester2;
		
		this.subjectSemesterService.deleteSubjectSemester(subjectSemesterToDelete);
		this.subjectSemesterService.deleteSubjectSemester(subjectSemesterToDelete);
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).doesNotContain(subjectSemesterToDelete);
		assertThat(foundSubjectSemesters).hasSize(3);
	}
	
	@Test
	void 과목으로_SS_전체_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubject(savedSubjects.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester3, subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 과목으로_SS_전체_중복_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubject(savedSubjects.get(0));
		this.subjectSemesterService.deleteAllBySubject(savedSubjects.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester3, subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 없는_과목으로_SS_전체_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubject(savedSubjects.get(3));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).isEqualTo(foundSubjectSemesters);
		assertThat(afterDeleteSubjectSemesters).hasSize(4);
	}
	
	@Test
	void 학기로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySemester(savedSemesters.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(1);
	}
	
	@Test
	void 학기로_SS__중복_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySemester(savedSemesters.get(0));
		this.subjectSemesterService.deleteAllBySemester(savedSemesters.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(1);
	}
	
	@Test
	void 없는_학기로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySemester(savedSemesters.get(2));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).isEqualTo(foundSubjectSemesters);
		assertThat(afterDeleteSubjectSemesters).hasSize(4);
	}
	
	@Test
	void 과목과_학기로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubjectAndSemester(savedSubjects.get(0), savedSemesters.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester3, subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 과목과_학기로_SS_중복_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubjectAndSemester(savedSubjects.get(0), savedSemesters.get(0));
		this.subjectSemesterService.deleteAllBySubjectAndSemester(savedSubjects.get(0), savedSemesters.get(0));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).contains(subjectSemester3, subjectSemester4);
		assertThat(afterDeleteSubjectSemesters).hasSize(2);
	}
	
	@Test
	void 없는_과목과_학기로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllBySubjectAndSemester(savedSubjects.get(0), savedSemesters.get(1));
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).isEqualTo(foundSubjectSemesters);
	}
	
	@Test
	void SS로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_01);
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).doesNotContain(subjectSemester1);
		assertThat(afterDeleteSubjectSemesters).hasSize(3);
	}
	
	@Test
	void SS로_SS_중복_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_01);
		this.subjectSemesterService.deleteBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_01);
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).doesNotContain(subjectSemester1);
		assertThat(afterDeleteSubjectSemesters).hasSize(3);
	}
	
	@Test
	void 없는_SS로_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteBySubjectAndSemesterAndSection(savedSubjects.get(0), savedSemesters.get(0), Section.SECTION_03);
		
		List<SubjectSemester> afterDeleteSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(afterDeleteSubjectSemesters).isEqualTo(foundSubjectSemesters);
		assertThat(afterDeleteSubjectSemesters).hasSize(4);
	}
	
	@Test
	void 교수로_SS_전체_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		List<Professor> savedProfessors = this.professorRepository.saveAll(this.professors);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		
		List<SubjectSemester> subjectSemesters = Arrays.asList(subjectSemester1, subjectSemester2, subjectSemester3, subjectSemester4);
		
		for (int i = 0; i < subjectSemesters.size(); i++) {
			if (i % 2 == 0) {
				subjectSemesters.get(i).setProfessor(savedProfessors.get(0));
			} else if (i % 2 == 1) {
				subjectSemesters.get(i).setProfessor(savedProfessors.get(1));
			}
		}
		
		this.subjectSemesterRepository.saveAll(subjectSemesters);
		
		this.subjectSemesterService.deleteAllByProfessor(savedProfessors.get(0));
		
		assertThat(this.subjectSemesterRepository.findAll()).extracting(SubjectSemester::getProfessor).doesNotContain(savedProfessors.get(0));
	}
	
	@Test
	void 전체_SS_삭제() {
		List<Subject> savedSubjects = this.subjectRepository.saveAll(this.subjects);
		List<Semester> savedSemesters = this.semesterRepository.saveAll(this.semesters);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester1);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(savedSubjects.get(0), savedSemesters.get(0), "090705", 23, Section.SECTION_02);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester2);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(savedSubjects.get(1), savedSemesters.get(0), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester3);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(savedSubjects.get(2), savedSemesters.get(1), "090705", 23, Section.SECTION_01);
		this.subjectSemesterService.registerSubjectSemester(subjectSemester4);
		
		List<SubjectSemester> foundSubjectSemesters = this.subjectSemesterRepository.findAll();
		
		assertThat(foundSubjectSemesters).hasSize(4);
		
		this.subjectSemesterService.deleteAllSubjectSemesters();
		
		assertThat(this.subjectSemesterRepository.findAll()).isEmpty();
	}
}
