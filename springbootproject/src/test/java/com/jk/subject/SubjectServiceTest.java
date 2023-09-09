package com.jk.subject;

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
import org.springframework.transaction.annotation.Transactional;

import com.jk.department.Department;
import com.jk.department.DepartmentRepository;

@Transactional
@SpringBootTest
public class SubjectServiceTest {

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	List<Subject> subjects;
	List<Department> departments;
	
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
	}
	
	@AfterEach
	void afterEach() {
		this.subjectRepository.deleteAll();
		this.departmentRepository.deleteAll();
	}
	
	@Test
	void 과목_등록() {
		Subject subjectToRegister = this.subjects.get(0);
		
		String findSubjectCode = this.subjectService.registerSubject(subjectToRegister);
		
		assertThat(subjectToRegister.getSubjectCode()).isEqualTo(findSubjectCode);
	}
	
	@Test
	void 종복_과목_예외() {
		Subject subject1 = this.subjects.get(0);
		Subject subject2 = this.subjects.get(0);
		
		this.subjectService.registerSubject(subject1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.subjectService.registerSubject(subject2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 과목입니다.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"00000", "11111", "22222", "33333", "44444", "55555"})
	void 학수번호_과목_조회(String subjectCode) {
		this.subjectRepository.saveAll(this.subjects);
		
		Optional<Subject> findSubjectOptional =  this.subjectService.findAllBySubjectCode(subjectCode);
		
		assertThat(findSubjectOptional).isPresent();
		assertThat(findSubjectOptional.get().getSubjectCode()).isEqualTo(subjectCode);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"컴퓨터개론", "이산수학", "데이터베이스", "미시경제학", "초급프로그래밍", "고급프로그래밍"})
	void 과목이름_과목_조회(String name) {
		this.subjectRepository.saveAll(this.subjects);
		
		List<Subject> findSubjects = this.subjectService.findAllByName(name);
		
		assertThat(findSubjects).extracting(Subject::getName).containsOnly(name);
	}
	
	@ParameterizedTest
	@EnumSource(SubjectType.class)
	void 전공_교양_과목_조회(SubjectType subjectType) {
		this.subjectRepository.saveAll(this.subjects);
		
		List<Subject> findSubjects = this.subjectService.findAllBySubejectType(subjectType);
		
		assertThat(findSubjects).extracting(Subject::getSubjectType).containsOnly(subjectType);
	}
	
	@ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void 학년_과목_조회(int academicYear) {
        this.subjectRepository.saveAll(this.subjects);

        List<Subject> findSubjects = this.subjectService.findAllByAcademicYear(academicYear);
        
        assertThat(findSubjects).extracting(Subject::getAcademicYear).containsOnly(academicYear);
    }
	
	@ParameterizedTest
    @ValueSource(ints = {2, 3})
	void 학점_과목_조회(int credit) {
		this.subjectRepository.saveAll(this.subjects);
		
		List<Subject> findSubjects = this.subjectService.findAllByCredit(credit);
		
		assertThat(findSubjects).extracting(Subject::getCredit).containsOnly(credit);
	}
	
	@Test
	void 학과_과목_조회() {
		this.departmentRepository.saveAll(this.departments);
		
		for (int i = 0; i < this.subjects.size(); i++) {
			if (i % 2 == 0) {
				this.subjects.get(i).setDepartment(this.departments.get(0));
			} else if (i % 2 == 1) {
				this.subjects.get(i).setDepartment(this.departments.get(2));
			}
		}
		
		this.subjectRepository.saveAll(this.subjects);
		
		List<Subject> foundSubjects = this.subjectService.findAllByDepartment(this.departments.get(0));
		
		assertThat(foundSubjects).extracting(Subject::getDepartment).containsOnly(this.departments.get(0));
	}
	
	@Test
	void 전체_과목_조회() {
		this.subjectRepository.saveAll(this.subjects);
		
		List<Subject> allSubjects = this.subjectService.findAllSubjects();
		
		assertThat(allSubjects).hasSize(this.subjects.size()); 
	}
	
	@Test
	void 과목_삭제() {
		Subject subject = this.subjectRepository.save(this.subjects.get(0));
		
		Long subjectId = subject.getId();
		Long deleteSubjectId = this.subjectService.deleteSubject(subject);
		
		assertThat(subjectId).isEqualTo(deleteSubjectId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"00000", "11111", "22222", "33333", "44444", "55555"})
	void 학수번호_과목_삭제(String subjectCode) {
		this.subjectRepository.saveAll(this.subjects);
		
		this.subjectService.deleteBySubjectCode(subjectCode);
		
		List<Subject> remainSubjects = this.subjectRepository.findAll();
		
		assertThat(remainSubjects).extracting(Subject::getSubjectCode).doesNotContain(subjectCode);
	}
	
	@Test
	void 학과로_과목_전체_삭제() {
		this.departmentRepository.saveAll(this.departments);
		
		for (int i = 0; i < this.subjects.size(); i++) {
			if (i % 2 == 0) {
				this.subjects.get(i).setDepartment(this.departments.get(0));
			} else if (i % 2 == 1) {
				this.subjects.get(i).setDepartment(this.departments.get(2));
			}
		}
		
		this.subjectRepository.saveAll(this.subjects);
		
		this.subjectService.deleteAllByDepartment(this.departments.get(0));
		
		assertThat(this.subjectRepository.findAll()).extracting(Subject::getDepartment).doesNotContain(this.departments.get(0));
	}
	
	@Test
	void 전체_과목_삭제() {
		List<Subject> subjects = this.subjectRepository.saveAll(this.subjects);
		
		assertThat(subjects).hasSize(this.subjects.size());
		
		this.subjectService.deleteAllSubjects();
		
		assertThat(this.subjectRepository.findAll()).isEmpty();
	}
}
