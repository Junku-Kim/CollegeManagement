package com.jk.domain.department;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DepartmentTest {

	private List<Department> departments;
	
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
	}
	
	@Test
	public void 학과_변경() {
		Department department = this.departments.get(0);
		Department newDepartment = department.withMajor("전산학과");
		
		assertThat(newDepartment.getMajor()).isEqualTo("전산학과");
	}
	
	@Test
	public void 학부_변경() {
		Department department = this.departments.get(0);
		Department newDepartment = department.withFaculty("전산학부");
		
		assertThat(newDepartment.getFaculty()).isEqualTo("전산학부");
	}
	
	@Test
	public void 단과대학_변경() {
		Department department = this.departments.get(0);
		Department newDepartment = department.withCollege("자연과학대학");
		
		assertThat(newDepartment.getCollege()).isEqualTo("자연과학대학");
	}
}
