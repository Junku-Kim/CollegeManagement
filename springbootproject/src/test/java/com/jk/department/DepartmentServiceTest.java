package com.jk.department;

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
public class DepartmentServiceTest {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentService departmentService;
	
	List<Department> departments;
	
	@BeforeEach
	void SetUp() {
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
		this.departmentRepository.deleteAll();
	}
	
	@Test
    void 학과_등록() {
        Department departmentToRegister = this.departments.get(0);
        
        String registerMajor = this.departmentService.registerDepartment(departmentToRegister);
        
        assertThat(departmentToRegister.getMajor()).isEqualTo(registerMajor);
    }
	
	@Test
    void 중복_학과_예외() {
        Department department1 = this.departments.get(0);
        Department department2 = this.departments.get(0);
        
        this.departmentService.registerDepartment(department1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> this.departmentService.registerDepartment(department2));
        
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 학과입니다.");
    }
    
	@ParameterizedTest
    @ValueSource(strings = {"컴퓨터공학과", "전기공학과", "전자공학과", "경제학과", "경영학과"})
    void 전공_학과_조회(String major) {
        this.departmentRepository.saveAll(this.departments);
    	
        Optional<Department> findDepartmentOptional = this.departmentService.findByMajor(major);
        
        assertThat(findDepartmentOptional).isPresent();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"컴퓨터공학부", "전기및전자공학부", "경제및경영학부"})
    void 학부_학과_조회(String faculty) {
    	this.departmentRepository.saveAll(this.departments);
        
        List<Department> findDepartments = this.departmentService.findAllByFaculty(faculty);
        
        assertThat(findDepartments).extracting(Department::getFaculty).containsOnly(faculty);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"공과대학", "상경대학"})
    void 단과대학_학과_조회(String college) {
    	this.departmentRepository.saveAll(this.departments);
        
        List<Department> foundDepartments = this.departmentService.findAllByCollege(college);
        
        assertThat(foundDepartments).extracting(Department::getCollege).containsOnly(college);
    }
    
    @Test
    void 전체_학과_조회() {
    	this.departmentRepository.saveAll(this.departments);
        
        List<Department> foundDepartments = this.departmentService.findAllDepartments();
        
        assertThat(foundDepartments).hasSize(this.departments.size());
    }
    
    @Test
    void 학과삭제() {
    	Department department = this.departmentRepository.save(this.departments.get(0));
    	Long departmentId = department.getId();
    	
    	Long deleteDepartmentId = this.departmentService.deleteDepartment(department);
    	
    	assertThat(departmentId).isEqualTo(deleteDepartmentId);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"컴퓨터공학과", "전기공학과", "전자공학과", "경제학과", "경영학과"})
    void 학과_이름_삭제(String major) {
    	this.departmentRepository.saveAll(this.departments);
    	
    	this.departmentService.deleteByMajor(major);
    	
    	List<Department> resultDepartments = this.departmentRepository.findAll();
    	
    	assertThat(resultDepartments).extracting(Department::getMajor).doesNotContain(major);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"컴퓨터공학부", "전기및전자공학부", "경제및경영학부"})
    void 학부_이름_삭제(String faculty) {
    	this.departmentRepository.saveAll(this.departments);
    	
    	this.departmentService.deleteAllByFaculty(faculty);
    	
    	List<Department> resultDepartments = this.departmentRepository.findAll();
    	
    	assertThat(resultDepartments).extracting(Department::getFaculty).doesNotContain(faculty);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"공과대학", "상경대학"})
    void 단과대학_이름_삭제(String college) {
    	this.departmentRepository.saveAll(this.departments);
    	
    	this.departmentService.deleteAllByCollege(college);
    	
    	List<Department> resultDepartments = this.departmentRepository.findAll();
    	
    	assertThat(resultDepartments).extracting(Department::getCollege).doesNotContain(college);
    }
    
    @Test
    void 학과_전체_삭제() {
    	List<Department> departments = this.departmentRepository.saveAll(this.departments);
    	
    	assertThat(departments).hasSize(5);
    	
    	this.departmentService.deleteAllDepartment();
    	
    	assertThat(this.departmentRepository.findAll()).isEmpty();;
    }
}
