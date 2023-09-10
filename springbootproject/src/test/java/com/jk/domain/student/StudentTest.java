package com.jk.domain.student;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.domain.person.Address;

public class StudentTest {
	
    private List<Student> students;
    
    @BeforeEach
    public void setUp() {
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
    }
    
    @Test
    public void 이름_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withName("새로운 김준구");
    	
    	assertThat(newStudent.getName()).isEqualTo("새로운 김준구");
    }
    
    @Test
    public void 비밀번호_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withPassword("newpw111");
    	
    	assertThat(newStudent.getPassword()).isEqualTo("newpw111");
    }
    
    @Test
    public void 전화번호_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withPhoneNumber("010-4444-4444");
    	
    	assertThat(newStudent.getPhoneNumber()).isEqualTo("010-4444-4444");
    }
    
    @Test
    public void 이메일_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withEmail("jku123@gmail.com");
    	
    	assertThat(newStudent.getEmail()).isEqualTo("jku123@gmail.com");
    }

    @Test
    public void 주소_변경() {
    	Student student = this.students.get(0);
    	Address newAddress = Address.builder()
    							 .siDo("서울특별시")
    							 .siGunGu("강남구")
    							 .roGil("테헤란로")
    							 .detailAddress("23-45, 305호")
    							 .postalCode("04212")
    							 .build();
    	
    	Student newStudent = student.withAddress(newAddress);
    	
    	assertThat(newStudent.getAddress()).isEqualTo(newAddress);
    }
    
    @Test
    public void 대학교_학비_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withTuition(4500000);
    	
    	assertThat(newStudent.getTuition()).isEqualTo(4500000);
    }

    @Test
    public void 학년_변경() {
    	Student student = this.students.get(0);
    	Student newStudent = student.withAcademicYear(5);
    	
    	assertThat(newStudent.getAcademicYear()).isEqualTo(5);
    }
}
