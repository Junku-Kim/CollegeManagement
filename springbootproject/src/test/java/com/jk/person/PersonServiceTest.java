package com.jk.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.professor.Professor;
import com.jk.professor.Rank;
import com.jk.staff.Staff;
import com.jk.student.Student;

@Transactional
@SpringBootTest
public class PersonServiceTest {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonService personService;
	
	List<Professor> professors;
	
	List<Staff> staffs;
	
	List<Student> students;
	
	List<Person> persons;
	
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
		
		this.staffs
		= Arrays.asList(
				Staff.builder()
				.name("김조교")
				.loginId("staff000")
				.password("pw000")
				.phoneNumber("010-0000-0000")
				.email("staff000@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("유성구")
						.roGil("봉명로")
						.detailAddress("5478, 3층")
						.postalCode("67890")
						.build()
						)
				.salary(2000000)
				.location("090203")
				.officeNumber("042-000-0000")
				.build(),
				
				Staff.builder()
				.name("김조교")
				.loginId("staff111")
				.password("pw111")
				.phoneNumber("010-1111-1111")
				.email("staff111@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("중구")
						.roGil("유등천로")
						.detailAddress("123-1, 4층")
						.postalCode("12345")
						.build()
						)
				.salary(2500000)
				.location("090321")
				.officeNumber("042-111-1111")
				.build(),
				
				Staff.builder()
				.name("이조교")
				.loginId("staff222")
				.password("pw222")
				.phoneNumber("010-2222-2222")
				.email("staff222@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("서구")
						.roGil("둔산로")
						.detailAddress("789-2, 10동 501호")
						.postalCode("54321")
						.build()
						)
				.salary(3000000)
				.location("110207")
				.officeNumber("042-222-2222")
				.build()
				);
		
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
		
		this.persons = Stream.of(
				this.staffs.stream(),
				this.professors.stream(),
				this.students.stream()
				)
				.flatMap(Function.identity())
				.map(person -> (Person) person)
				.toList();
		
		this.personRepository.saveAll(persons);
	}
	
	@AfterEach
	void afterEach() {
		this.personRepository.deleteAll();
	}
	
	@ParameterizedTest
	@MethodSource("loginIdProvider")
	void login_ID로_사람_찾기(String loginId) {
		Optional<Person> foundPersonOptional = this.personService.findByLoginId(loginId);
		
		assertThat(foundPersonOptional.get().getLoginId()).isEqualTo(loginId);
	}
	
	 static Stream<Arguments> loginIdProvider() {
		return 	Stream.of(
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
											
											Staff.builder()
											.name("김조교")
											.loginId("staff000")
											.password("pw000")
											.phoneNumber("010-0000-0000")
											.email("staff000@jku.kr")
											.address(Address.builder()
													.siDo("대전광역시")
													.siGunGu("유성구")
													.roGil("봉명로")
													.detailAddress("5478, 3층")
													.postalCode("67890")
													.build()
													)
											.salary(2000000)
											.location("090203")
											.officeNumber("042-000-0000")
											.build(),
											
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
											.build()
								)
								.map(p -> (Person) p)
								.map(Person::getLoginId)
								.map(Arguments::of);
				
	}
	
	@ParameterizedTest
	@MethodSource("nameProvider")
	void name으로_사람_전체_찾기(String name) {
		List<Person> foundPersons = this.personService.findAllByName(name);
		
		assertThat(foundPersons).extracting(Person::getName).containsOnly(name);
	}
	
	 static Stream<Arguments> nameProvider() {
		return Stream.of(
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
				
				Staff.builder()
				.name("김조교")
				.loginId("staff000")
				.password("pw000")
				.phoneNumber("010-0000-0000")
				.email("staff000@jku.kr")
				.address(Address.builder()
						.siDo("대전광역시")
						.siGunGu("유성구")
						.roGil("봉명로")
						.detailAddress("5478, 3층")
						.postalCode("67890")
						.build()
						)
				.salary(2000000)
				.location("090203")
				.officeNumber("042-000-0000")
				.build(),
				
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
				.build()
				)
				.map(p -> (Person) p)
				.map(Person::getName)
				.map(Arguments::of);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"STAFF", "PROFESSOR", "STUDENT"})
	void dtype으로_사람_전체_찾기(String dtype) {
		List<Person> foundPersons = personService.findAllByDtype(dtype);
		
		assertThat(foundPersons).extracting(Person::getDtype).containsOnly(dtype);
	}
	
	@Test
	void 사람_전체_찾기() {
		List<Person> foundPersons = personService.findAll();
		
		assertThat(foundPersons).hasSize(this.persons.size());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void loginId로_사람_삭제(int index) {
		this.personService.deleteByLoginId(this.persons.get(index).getLoginId());
		List<Person> foundPersons = this.personRepository.findAll();
		
		assertThat(foundPersons).hasSize(this.persons.size() - 1);
		assertThat(foundPersons).extracting(Person::getLoginId).doesNotContain(this.persons.get(index).getLoginId());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"STAFF", "PROFESSOR", "STUDENT"})
	void dtpye으로_사람_전체_삭제(String dtype) {
		this.personService.deleteAllByDtype(dtype);
		List<Person> foundPersons = this.personRepository.findAll();
		
		assertThat(foundPersons).extracting(Person::getDtype).doesNotContain(dtype);
	}
	
	@Test
	void 사람_전체_삭제() {
		this.personService.deleteAll();
		List<Person> foundPersons = this.personRepository.findAll();
		
		assertThat(foundPersons).isEmpty();
	}
}
