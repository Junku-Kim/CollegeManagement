package com.jk.subject.semester;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.department.Department;
import com.jk.person.Address;
import com.jk.professor.Professor;
import com.jk.professor.Rank;
import com.jk.semester.Semester;
import com.jk.subject.Subject;
import com.jk.subject.SubjectType;

public class SubjectSemesterTest {

	private List<Department> departments;
	
	private List<Subject> subjects;
	
	private List<Semester> semesters;
	
	private List<Professor> professors;
	
	@BeforeEach
	public void SetUp() {
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
	public void afterEach() {
		SubjectSemester.clearSubjectSemesters();
	}
	
	@Test
	public void 교실위치_변경() {
		Subject subject = this.subjects.get(0);
		subject.setDepartment(this.departments.get(0));
		Semester semester = this.semesters.get(0);
		SubjectSemester subjectSemester = SubjectSemester.getInstance(subject, semester, "090425", 25, Section.SECTION_01);
		
		SubjectSemester updateSubjectSemester = subjectSemester.withClassroomLocation("040201");
		
		assertThat(updateSubjectSemester.getClassroomLocation()).isEqualTo("040201");
		assertThat(updateSubjectSemester.getSubject()).isEqualTo(subject);
		assertThat(updateSubjectSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 정원_변경() {
		Subject subject = this.subjects.get(0);
		subject.setDepartment(this.departments.get(0));
		Semester semester = this.semesters.get(0);
		SubjectSemester subjectSemester = SubjectSemester.getInstance(subject, semester, "090425", 25, Section.SECTION_01);
		
		SubjectSemester updateSubjectSemester = subjectSemester.withLimitCapacity(30);
		
		assertThat(updateSubjectSemester.getLimitCapacity()).isEqualTo(30);
		assertThat(updateSubjectSemester.getSubject()).isEqualTo(subject);
		assertThat(updateSubjectSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 분반_변경() {
		Subject subject = this.subjects.get(0);
		subject.setDepartment(this.departments.get(0));
		Semester semester = this.semesters.get(0);
		SubjectSemester subjectSemester = SubjectSemester.getInstance(subject, semester, "090425", 25, Section.SECTION_01);
		
		SubjectSemester updateSubjectSemester = subjectSemester.withSection(Section.SECTION_02);
		
		assertThat(updateSubjectSemester.getSection()).isEqualTo(Section.SECTION_02);
		assertThat(updateSubjectSemester.getSubject()).isEqualTo(subject);
		assertThat(updateSubjectSemester.getSemester()).isEqualTo(semester);
	}
	
	@Test
	public void 과목_학기_양방향_연결_확인() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Subject subject2 = this.subjects.get(1);
		subject2.setDepartment(this.departments.get(1));
		Semester semester1 = this.semesters.get(0);
		Semester semester2 = this.semesters.get(1);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		SubjectSemester subjectSemester2 = SubjectSemester.getInstance(subject1, semester2, "020425", 10, Section.SECTION_02);
		SubjectSemester subjectSemester3 = SubjectSemester.getInstance(subject2, semester1, "030425", 15, Section.SECTION_03);
		SubjectSemester subjectSemester4 = SubjectSemester.getInstance(subject2, semester2, "040425", 20, Section.SECTION_04);
		
		assertThat(SubjectSemester.getSubjectSemesters()).contains(subjectSemester1, subjectSemester2, subjectSemester3, subjectSemester4);
		assertThat(SubjectSemester.getSubjectSemesters()).hasSize(4);
		
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).contains(subjectSemester1, subjectSemester2);
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).hasSize(2);
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).contains(subjectSemester1, subjectSemester3);
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).hasSize(2);
		
		assertThat(subjectSemester2.getSubject().getSubjectSemesters()).contains(subjectSemester1, subjectSemester2);
		assertThat(subjectSemester2.getSubject().getSubjectSemesters()).hasSize(2);
		assertThat(subjectSemester2.getSemester().getSubjectSemesters()).contains(subjectSemester2, subjectSemester4);
		assertThat(subjectSemester2.getSemester().getSubjectSemesters()).hasSize(2);
		
		assertThat(subjectSemester3.getSubject().getSubjectSemesters()).contains(subjectSemester3, subjectSemester4);
		assertThat(subjectSemester3.getSubject().getSubjectSemesters()).hasSize(2);
		assertThat(subjectSemester3.getSemester().getSubjectSemesters()).contains(subjectSemester1, subjectSemester3);
		assertThat(subjectSemester3.getSemester().getSubjectSemesters()).hasSize(2);
		
		assertThat(subjectSemester4.getSubject().getSubjectSemesters()).contains(subjectSemester3, subjectSemester4);
		assertThat(subjectSemester4.getSubject().getSubjectSemesters()).hasSize(2);
		assertThat(subjectSemester4.getSemester().getSubjectSemesters()).contains(subjectSemester2, subjectSemester4);
		assertThat(subjectSemester4.getSemester().getSubjectSemesters()).hasSize(2);
	}
	
	@Test
	public void 과목_학기_중복_확인() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		assertThat(SubjectSemester.getSubjectSemesters()).contains(subjectSemester1);
		assertThat(SubjectSemester.getSubjectSemesters()).hasSize(1);
		
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).contains(subjectSemester1);
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).hasSize(1);
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).contains(subjectSemester1);
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).hasSize(1);
	}
	
	@Test
	public void 과목_학기_삭제() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		subjectSemester1.removeSubjectSemester();
		
		assertThat(SubjectSemester.getSubjectSemesters()).isEmpty();
		assertThat(subjectSemester1.getSubject()).isNull();
		assertThat(subjectSemester1.getSemester()).isNull();
		assertThat(subject1.getSubjectSemesters()).isEmpty();
		assertThat(semester1.getSubjectSemesters()).isEmpty();
	}
	
	@Test
	public void 과목_학기_중복_삭제() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		subjectSemester1.removeSubjectSemester();
		subjectSemester1.removeSubjectSemester();
		
		assertThat(SubjectSemester.getSubjectSemesters()).isEmpty();
		assertThat(subjectSemester1.getSubject()).isNull();
		assertThat(subjectSemester1.getSemester()).isNull();
		assertThat(subject1.getSubjectSemesters()).isEmpty();
		assertThat(semester1.getSubjectSemesters()).isEmpty();
	}
	
	@Test
	public void 과목_학기_연관관계_수정_후_유지_() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		Subject updateSubject = subject1.withCredit(2);
		Semester updateSemester = semester1.withSemesterName("23년도 1학기");
		subjectSemester1.removeSubjectSemester();
		subjectSemester1 = SubjectSemester.getInstance(updateSubject, updateSemester, "010425", 5, Section.SECTION_01);
		
		assertThat(SubjectSemester.getSubjectSemesters()).contains(subjectSemester1);
		assertThat(SubjectSemester.getSubjectSemesters()).hasSize(1);
		
		assertThat(subjectSemester1.getSubject().getCredit()).isEqualTo(2);
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).contains(subjectSemester1);
		assertThat(subjectSemester1.getSubject().getSubjectSemesters()).hasSize(1);
		
		assertThat(subjectSemester1.getSemester().getSemesterName()).isEqualTo("23년도 1학기");
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).contains(subjectSemester1);
		assertThat(subjectSemester1.getSemester().getSubjectSemesters()).hasSize(1);
		
		assertThat(updateSubject.getCredit()).isEqualTo(2);
		assertThat(updateSubject.getSubjectSemesters()).contains(subjectSemester1);
		assertThat(updateSubject.getSubjectSemesters()).hasSize(1);
		
		assertThat(subject1.getSubjectSemesters()).isEmpty();
		
		assertThat(updateSemester.getSemesterName()).isEqualTo("23년도 1학기");
		assertThat(updateSemester.getSubjectSemesters()).contains(subjectSemester1);
		assertThat(updateSemester.getSubjectSemesters()).hasSize(1);
		
		assertThat(semester1.getSubjectSemesters()).isEmpty();
	}
	
	@Test
	public void 교수_필드_수정_후_연관관계_유지() {
		Subject subject1 = this.subjects.get(0);
		subject1.setDepartment(this.departments.get(0));
		Semester semester1 = this.semesters.get(0);
		
		SubjectSemester subjectSemester1 = SubjectSemester.getInstance(subject1, semester1, "010425", 5, Section.SECTION_01);
		
		Professor professor = this.professors.get(0);
		
		subjectSemester1.setProfessor(professor);
		
		assertThat(subjectSemester1.getProfessor().getLoginId()).isEqualTo(professor.getLoginId());
		assertThat(professor.getSubjectSemesters()).contains(subjectSemester1);
		
		Professor updatedProfessor = professor.withName("김준십");
		
		subjectSemester1.setProfessor(updatedProfessor);
		
		assertThat(subjectSemester1.getProfessor().getLoginId()).isEqualTo(updatedProfessor.getLoginId());
		assertThat(professor.getSubjectSemesters()).doesNotContain(subjectSemester1);
		assertThat(updatedProfessor.getSubjectSemesters()).contains(subjectSemester1);
	}
}
