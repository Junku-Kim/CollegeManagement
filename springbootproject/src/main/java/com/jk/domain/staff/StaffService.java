package com.jk.domain.staff;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.department.Department;
import com.jk.domain.department.DepartmentService;
import com.jk.domain.employee.EmployeeService;
import com.jk.domain.person.PersonRepository;

@Transactional
@Service
public class StaffService extends EmployeeService{
	
	private final StaffRepository staffRepository;
	
	private final DepartmentService departmentService;
	
	public StaffService(PersonRepository personRepository, StaffRepository staffRepository, DepartmentService departmentService) {
		super(personRepository);
		this.staffRepository = staffRepository;
		this.departmentService = departmentService;
	}
	
	public String registerStaff(Staff staff) {
		validateStaffForCreation(staff);
		this.staffRepository.save(staff);
		
		return staff.getLoginId();
	}
	
	private void validateStaffForCreation(Staff staff) {
		this.staffRepository.findByLoginId(staff.getLoginId())
			.ifPresent(s -> {
				throw new IllegalStateException("이미 존재하는 조교입니다.");
			});
	}
	
	public Long deleteStaff(Staff staff) {
		this.staffRepository.delete(staff);
		
		return staff.getId();
	}
	
	public String registerDepartment(Department department) {
		departmentService.registerDepartment(department);
		
		return department.getMajor();
	}
}
