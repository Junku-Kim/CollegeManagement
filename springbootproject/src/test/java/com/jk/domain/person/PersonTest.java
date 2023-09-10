package com.jk.domain.person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.department.Department;
import com.jk.domain.person.department.PersonDepartment;
import com.jk.domain.professor.Professor;
import com.jk.domain.professor.Rank;
import com.jk.domain.staff.Staff;
import com.jk.domain.student.Student;

public class PersonTest {
	
	private List<Department> departments;
    private List<Student> students;
    private List<Staff> staffs;
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
	public void PD_삭제() {
		Department department = this.departments.get(0);
	    Student student1 = this.students.get(0);
	    Staff staff1 = this.staffs.get(0);
	    Professor professor1 = this.professors.get(0);

	    PersonDepartment personDepartment1 = PersonDepartment.getInstance(student1, department);
	    PersonDepartment personDepartment2 = PersonDepartment.getInstance(staff1, department);
	    PersonDepartment personDepartment3 = PersonDepartment.getInstance(professor1, department);

	    student1.removePersonDepartment(personDepartment1);
	    staff1.removePersonDepartment(personDepartment2);
	    professor1.removePersonDepartment(personDepartment3);
	    
	    assertThat(department.getPersonDepartments()).hasSize(0);
	    assertThat(student1.getPersonDepartments()).hasSize(0);
	    assertThat(staff1.getPersonDepartments()).hasSize(0);
	    assertThat(professor1.getPersonDepartments()).hasSize(0);
	}
	
	@Test
	public void PD_삭제_추가() {
		Department department = this.departments.get(0);
	    Student student1 = this.students.get(0);
	    Staff staff1 = this.staffs.get(0);
	    Professor professor1 = this.professors.get(0);

	    PersonDepartment personDepartment1 = PersonDepartment.getInstance(student1, department);
	    PersonDepartment personDepartment2 = PersonDepartment.getInstance(staff1, department);
	    PersonDepartment personDepartment3 = PersonDepartment.getInstance(professor1, department);

	    student1.removePersonDepartment(personDepartment1);
	    staff1.removePersonDepartment(personDepartment2);
	    professor1.removePersonDepartment(personDepartment3);
	    
	    student1.addPersonDepartment(personDepartment1);
	    staff1.addPersonDepartment(personDepartment2);
	    professor1.addPersonDepartment(personDepartment3);
	    
	    assertThat(department.getPersonDepartments()).contains(personDepartment1 ,personDepartment2 , personDepartment3);
	    assertThat(department.getPersonDepartments()).hasSize(3);
	    
	    assertThat(student1.getPersonDepartments()).contains(personDepartment1);
	    assertThat(student1.getPersonDepartments()).hasSize(1);
	    
	    assertThat(staff1.getPersonDepartments()).contains(personDepartment2);
	    assertThat(staff1.getPersonDepartments()).hasSize(1);
	    
	    assertThat(professor1.getPersonDepartments()).contains(personDepartment3);
	    assertThat(professor1.getPersonDepartments()).hasSize(1);
	}
}
