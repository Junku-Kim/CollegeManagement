package com.jk.staff;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jk.person.Address;

public class StaffTest {

	private List<Staff> staffs;
	
	@BeforeEach
	public void setUp() {
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
	}
	
	@Test
	public void 이름_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withName("정조교");
		
		assertThat(newStaff.getName()).isEqualTo("정조교");
	}
	
	@Test
	public void 비밀번호_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withPassword("newpw444");
		
		assertThat(newStaff.getPassword()).isEqualTo("newpw444");
	}
	
	@Test
	public void 전화번호_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withPhoneNumber("010-7777-7777");
		
		assertThat(newStaff.getPhoneNumber()).isEqualTo("010-7777-7777");
	}
	
	@Test
	public void 이메일_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withEmail("newstaff444@jku.kr");
		
		assertThat(newStaff.getEmail()).isEqualTo("newstaff444@jku.kr");
	}
	
	@Test
    public void 주소_변경() {
    	Staff staff = this.staffs.get(2);
    	Address newAddress = Address.builder()
    							 .siDo("서울특별시")
    							 .siGunGu("강남구")
    							 .roGil("테헤란로")
    							 .detailAddress("23-45, 305호")
    							 .postalCode("04212")
    							 .build();
    	
    	Staff newStaff = staff.withAddress(newAddress);
    	
    	assertThat(newStaff.getAddress()).isEqualTo(newAddress);
    }
	
	@Test
	public void 월급_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withSalary(9000000);
		
		assertThat(newStaff.getSalary()).isEqualTo(9000000);
	}
	
	@Test
	public void 교내_위치_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withLocation("110713");
		
		assertThat(newStaff.getLocation()).isEqualTo("110713");
	}
	
	@Test
	public void 교내_전화번호_변경() {
		Staff staff = this.staffs.get(2);
		Staff newStaff = staff.withOfficeNumber("042-777-7777");
		
		assertThat(newStaff.getOfficeNumber()).isEqualTo("042-777-7777");
	}
}
