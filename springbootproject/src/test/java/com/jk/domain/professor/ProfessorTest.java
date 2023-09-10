package com.jk.domain.professor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.person.Address;

public class ProfessorTest {

	private List<Professor> professors;
	
	@BeforeEach
	public void setUp() {
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
	
	@Test
	public void 이름_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withName("정교수");
		
		assertThat(newProfessor.getName()).isEqualTo("정교수");
	}
	
	@Test
	public void 비밀번호_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withPassword("newpw444");
		
		assertThat(newProfessor.getPassword()).isEqualTo("newpw444");
	}
	
	@Test
	public void 전화번호_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withPhoneNumber("010-7777-7777");
		
		assertThat(newProfessor.getPhoneNumber()).isEqualTo("010-7777-7777");
	}
	
	@Test
	public void 이메일_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withEmail("newprofessor444@jku.kr");
		
		assertThat(newProfessor.getEmail()).isEqualTo("newprofessor444@jku.kr");
	}
	
	@Test
    public void 주소_변경() {
    	Professor professor = this.professors.get(4);
    	Address newAddress = Address.builder()
    							 .siDo("서울특별시")
    							 .siGunGu("강남구")
    							 .roGil("테헤란로")
    							 .detailAddress("23-45, 305호")
    							 .postalCode("04212")
    							 .build();
    	
    	Professor newProfessor = professor.withAddress(newAddress);
    	
    	assertThat(newProfessor.getAddress()).isEqualTo(newAddress);
    }
	
	@Test
	public void 월급_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withSalary(9000000);
		
		assertThat(newProfessor.getSalary()).isEqualTo(9000000);
	}
	
	@Test
	public void 교내_위치_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withLocation("110713");
		
		assertThat(newProfessor.getLocation()).isEqualTo("110713");
	}
	
	@Test
	public void 교내_전화번호_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withOfficeNumber("042-777-7777");
		
		assertThat(newProfessor.getOfficeNumber()).isEqualTo("042-777-7777");
	}
	
	@Test
	public void 직급_변경() {
		Professor professor = this.professors.get(4);
		Professor newProfessor = professor.withRank(Rank.EMERITUS_PROFESSOR);
		
		assertThat(newProfessor.getRank()).isEqualTo(Rank.EMERITUS_PROFESSOR);
	}
}
