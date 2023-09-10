package com.jk.domain.staff;

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

import com.jk.domain.department.Department;
import com.jk.domain.department.DepartmentService;
import com.jk.domain.person.Address;

@Transactional
@SpringBootTest
public class StaffServiceTest {

	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	DepartmentService departmentService;
	
	List<Staff> staffs;
	
	List<Department> departments;
	
	@BeforeEach
	void setUp() {
		
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
		this.staffService.deleteAll();
		this.departmentService.deleteAllDepartment();
	}
	
	@Test
	void 조교등록() {
		Staff staffToResister = this.staffs.get(0);
		
		String resisterLoginId = this.staffService.registerStaff(staffToResister);
		
		assertThat(staffToResister.getLoginId()).isEqualTo(resisterLoginId);
	}
	
	@Test
	void 중복_조교_예외() {
		Staff staff1 = this.staffs.get(0);
		Staff staff2 = this.staffs.get(0);

		this.staffService.registerStaff(staff1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.staffService.registerStaff(staff2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 조교입니다.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"staff000", "staff111", "staff222"})
	void 로그인_아이디_조교_조회(String loginId) {
		this.staffRepository.saveAll(this.staffs);

		Optional<Staff> foundStaff = StaffUtils.filterStaffOptional(this.staffService.findByLoginId(loginId));

		assertThat(foundStaff.get().getLoginId()).isEqualTo(loginId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"김조교", "이조교"})
    void 이름_조교_조회(String name) {
		this.staffRepository.saveAll(this.staffs);

        List<Staff> foundStaffs = StaffUtils.fiterStaffs(this.staffService.findAllByName(name));

        assertThat(foundStaffs).extracting(Staff::getName).containsOnly(name);
    }
	
	@Test
    void 전체_조교_조회() {
		this.staffRepository.saveAll(this.staffs);

        List<Staff> findStaffs = StaffUtils.fiterStaffs(this.staffService.findAll());

        assertThat(findStaffs).hasSize(this.staffs.size());
    }
	
	@Test
	void 조교삭제() {
		Staff staff = this.staffRepository.save(this.staffs.get(0));
		
		Long staffId = staff.getId();
		Long deleteStaffId = this.staffService.deleteStaff(staff);
		
		assertThat(staffId).isEqualTo(deleteStaffId);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"staff000", "staff111", "staff222"})
	void 로그인_아이디_조교_삭제(String loginId) {
		this.staffRepository.saveAll(this.staffs);
		
		this.staffService.deleteByLoginId(loginId);
		
		List<Staff> resultStaffs = StaffUtils.fiterStaffs(this.staffRepository.findAll());
		
		assertThat(resultStaffs).extracting(Staff::getLoginId).doesNotContain(loginId);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4})
	void 학과_등록(int index) {
		List<Department> departments = this.departments;
		
		this.staffService.registerDepartment(departments.get(index));
		
		List<Department> resultDepartments = this.departmentService.findAllDepartments();
		
		assertThat(resultDepartments).extracting(Department::getId).contains(departments.get(index).getId());
	}
}
