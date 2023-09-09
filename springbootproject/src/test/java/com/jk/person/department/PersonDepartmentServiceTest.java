package com.jk.person.department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.department.Department;
import com.jk.department.DepartmentRepository;
import com.jk.person.Address;
import com.jk.person.PersonRepository;
import com.jk.professor.Professor;
import com.jk.professor.ProfessorRepository;
import com.jk.professor.Rank;
import com.jk.staff.Staff;
import com.jk.staff.StaffRepository;
import com.jk.student.Student;
import com.jk.student.StudentRepository;

@Transactional
@SpringBootTest
public class PersonDepartmentServiceTest {

	@Autowired
	PersonDepartmentRepository personDepartmentRepository;
	
	@Autowired
    PersonDepartmentService personDepartmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
	PersonRepository personRepository;
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ProfessorRepository professorRepository;
   
    List<Department> departments;
    List<Student> students;
    List<Staff> staffs;
    List<Professor> professors;
 
    @BeforeEach
    void setUp() {
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
    
    @AfterEach
    void afterEach() {
    	PersonDepartment.clearPersonDepartments();
    	personDepartmentRepository.deleteAll();
        departmentRepository.deleteAll();
        personRepository.deleteAll();
    }
    
    @Test
    void PD_등록() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
    	
        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentProfessor);

        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        assertThat(foundPersonDepartments).hasSize(3);
        
    }
    
    @Test
    void PD_중복_등록_예외() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentService.registerPersonDepartment(personDepartmentProfessor);

        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        IllegalStateException e1 = assertThrows(IllegalStateException.class,
        		() -> this.personDepartmentService.registerPersonDepartment(personDepartmentStudent2));

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        IllegalStateException e2 = assertThrows(IllegalStateException.class,
        		() -> this.personDepartmentService.registerPersonDepartment(personDepartmentStaff2));

        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        IllegalStateException e3 = assertThrows(IllegalStateException.class,
        		() -> this.personDepartmentService.registerPersonDepartment(personDepartmentProfessor2));
        
        assertThat(e1.getMessage()).isEqualTo("이미 존재하는 PersonDepartment입니다.");
        assertThat(e2.getMessage()).isEqualTo("이미 존재하는 PersonDepartment입니다.");
        assertThat(e3.getMessage()).isEqualTo("이미 존재하는 PersonDepartment입니다.");
    }
    
    @Test
    void 사람으로_전체_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> findpersonDepartmentsStudent = this.personDepartmentService.findAllByPerson(savedStudents.get(0));
        List<PersonDepartment> findpersonDepartmentsStaff = this.personDepartmentService.findAllByPerson(savedStaffs.get(0));
        List<PersonDepartment> findpersonDepartmentsProffesor = this.personDepartmentService.findAllByPerson(savedProfessors.get(0));
        
        assertThat(findpersonDepartmentsStudent).contains(personDepartmentStudent1, personDepartmentStudent2, personDepartmentStudent3);
        assertThat(findpersonDepartmentsStudent).hasSize(3);
        assertThat(findpersonDepartmentsStaff).contains(personDepartmentStaff1, personDepartmentStaff2);
        assertThat(findpersonDepartmentsStaff).hasSize(2);
        assertThat(findpersonDepartmentsProffesor).contains(personDepartmentProfessor1, personDepartmentProfessor2);
        assertThat(findpersonDepartmentsProffesor).hasSize(2);
    }
    
    @Test
    void 없는_사람으로_전체_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> findpersonDepartmentsStudent = this.personDepartmentService.findAllByPerson(savedStudents.get(1));
        List<PersonDepartment> findpersonDepartmentsStaff = this.personDepartmentService.findAllByPerson(savedStaffs.get(1));
        List<PersonDepartment> findpersonDepartmentsProffesor = this.personDepartmentService.findAllByPerson(savedProfessors.get(1));
        
        assertThat(findpersonDepartmentsStudent).isEmpty();
        assertThat(findpersonDepartmentsStaff).isEmpty();
        assertThat(findpersonDepartmentsProffesor).isEmpty();
    }
    
    @Test
    void 학과로_전체_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> findPersonDepartments = this.personDepartmentService.findAllByDepartment(savedDepartments.get(3));
        
        assertThat(findPersonDepartments).contains(personDepartmentStudent2, personDepartmentProfessor1);
        assertThat(findPersonDepartments).hasSize(2);
    }
    
    @Test
    void 없는_학과로_전체_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> findPersonDepartments = this.personDepartmentService.findAllByDepartment(savedDepartments.get(2));
        
        assertThat(findPersonDepartments).isEmpty();
    }
    
    @Test
    void PD로_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);
        
        Optional<PersonDepartment> foundPersonDepartmentOptional = this.personDepartmentService.findByPersonAndDepartment(savedStudents.get(0), savedDepartments.get(0));
        
        assertThat(foundPersonDepartmentOptional).isPresent();
        assertThat(foundPersonDepartmentOptional.get().getPerson()).isEqualTo(savedStudents.get(0));
        assertThat(foundPersonDepartmentOptional.get().getDepartment()).isEqualTo(savedDepartments.get(0));
    }
    
    @Test
    void 없는_PD로_PD_조회() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);
        
        Optional<PersonDepartment> foundPersonDepartmentOptional = this.personDepartmentService.findByPersonAndDepartment(savedStudents.get(1), savedDepartments.get(0));
        
        assertThat(foundPersonDepartmentOptional).isEmpty();
    }
    
    @Test
    void 전체_PD_조회() {
        List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentService.findAllPersonDepartments();
        
        assertThat(foundPersonDepartments).contains(personDepartmentStudent, personDepartmentStaff, personDepartmentProfessor);
        assertThat(foundPersonDepartments).hasSize(3);
    }
    
    @Test
    void 빈_전체_PD_조회() {
		List<PersonDepartment> foundPersonDepartments = this.personDepartmentService.findAllPersonDepartments();
		
		assertThat(foundPersonDepartments).isEmpty();
    }
    
    @Test
    void PD_삭제() {
		List<Student> savedStudents = this.studentRepository.saveAll(students);
		List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
		List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
		List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

		PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);

		PersonDepartment personDepartmentToDelete = personDepartmentStaff;

		this.personDepartmentService.deletePersonDepartment(personDepartmentToDelete);
		List<PersonDepartment> foundPersonDepartment = this.personDepartmentRepository.findAll();

		assertThat(foundPersonDepartment).doesNotContain(personDepartmentToDelete);
		assertThat(foundPersonDepartment).hasSize(2);
    }
    
    @Test
    void PD_중복_삭제() {
		List<Student> savedStudents = this.studentRepository.saveAll(students);
		List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
		List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
		List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

		PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);

		PersonDepartment personDepartmentToDelete = personDepartmentStaff;

		this.personDepartmentService.deletePersonDepartment(personDepartmentToDelete);
		this.personDepartmentService.deletePersonDepartment(personDepartmentToDelete);
		List<PersonDepartment> foundPersonDepartment = this.personDepartmentRepository.findAll();

		assertThat(foundPersonDepartment).doesNotContain(personDepartmentToDelete);
		assertThat(foundPersonDepartment).hasSize(2);
    }
    
    @Test
    void 사람으로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByPerson(savedStudents.get(0));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(4);
        assertThat(afterDeletePersonDepartments).contains(personDepartmentStaff1, personDepartmentStaff2, personDepartmentProfessor1, personDepartmentProfessor2);
    }
    
    @Test
    void 사람으로_PD_중복_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByPerson(savedStudents.get(0));
        this.personDepartmentService.deleteAllByPerson(savedStudents.get(0));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(4);
        assertThat(afterDeletePersonDepartments).contains(personDepartmentStaff1, personDepartmentStaff2, personDepartmentProfessor1, personDepartmentProfessor2);
    }
    
    @Test
    void 없는_사람으로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(1));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByPerson(savedStudents.get(1));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(7);
        assertThat(afterDeletePersonDepartments).isEqualTo(foundPersonDepartments);
    }
    
    @Test
    void 학과로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByDepartment(savedDepartments.get(0));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(4);
        assertThat(afterDeletePersonDepartments).contains(personDepartmentStudent2, personDepartmentStudent3, personDepartmentStaff2, personDepartmentProfessor2);
    }
    
    @Test
    void 학과로_PD_중복_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByDepartment(savedDepartments.get(0));
        this.personDepartmentService.deleteAllByDepartment(savedDepartments.get(0));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(4);
        assertThat(afterDeletePersonDepartments).contains(personDepartmentStudent2, personDepartmentStudent3, personDepartmentStaff2, personDepartmentProfessor2);
    }
    
    @Test
    void 없는_학과로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteAllByDepartment(savedDepartments.get(1));
        
        List<PersonDepartment> afterDeletePersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(afterDeletePersonDepartments).hasSize(7);
        assertThat(afterDeletePersonDepartments).isEqualTo(foundPersonDepartments);
    }
    
    @Test
    void 사람과_학과로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(0));
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(3));
        
        assertThat(this.personDepartmentRepository.findAll()).hasSize(5);
        assertThat(this.personDepartmentRepository.findAll()).contains(personDepartmentStudent3, personDepartmentStaff1, personDepartmentStaff2, personDepartmentProfessor1, personDepartmentProfessor2);
    }
    
    @Test
    void 사람과_학과로_PD_중복_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(0));
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(3));
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(0));
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(0) ,savedDepartments.get(3));
        
        assertThat(this.personDepartmentRepository.findAll()).hasSize(5);
        assertThat(this.personDepartmentRepository.findAll()).contains(personDepartmentStudent3, personDepartmentStaff1, personDepartmentStaff2, personDepartmentProfessor1, personDepartmentProfessor2);
    }
    
    @Test
    void 없는_사람과_학과로_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);
        
        PersonDepartment personDepartmentStudent1 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent1);
        
        PersonDepartment personDepartmentStudent2 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(3));
        this.personDepartmentRepository.save(personDepartmentStudent2);
        
        PersonDepartment personDepartmentStudent3 = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentStudent3);

        PersonDepartment personDepartmentStaff1 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff1);

        PersonDepartment personDepartmentStaff2 = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(2));
        this.personDepartmentRepository.save(personDepartmentStaff2);

        PersonDepartment personDepartmentProfessor1 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor1);
        
        PersonDepartment personDepartmentProfessor2 = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(4));
        this.personDepartmentRepository.save(personDepartmentProfessor2);
        
        List<PersonDepartment> foundPersonDepartments = this.personDepartmentRepository.findAll();
        
        assertThat(foundPersonDepartments).hasSize(7);
        
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(2) ,savedDepartments.get(0));
        this.personDepartmentService.deleteByPersonAndDepartment(savedStudents.get(2) ,savedDepartments.get(3));
        
        assertThat(this.personDepartmentRepository.findAll()).hasSize(7);
        assertThat(this.personDepartmentRepository.findAll()).isEqualTo(foundPersonDepartments);
    }
    
    @Test
    void 전체_PD_삭제() {
    	List<Student> savedStudents = this.studentRepository.saveAll(students);
        List<Staff> savedStaffs = this.staffRepository.saveAll(staffs);
        List<Professor> savedProfessors = this.professorRepository.saveAll(professors);
        List<Department> savedDepartments = this.departmentRepository.saveAll(departments);

        PersonDepartment personDepartmentStudent = PersonDepartment.getInstance(savedStudents.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStudent);

        PersonDepartment personDepartmentStaff = PersonDepartment.getInstance(savedStaffs.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentStaff);

        PersonDepartment personDepartmentProfessor = PersonDepartment.getInstance(savedProfessors.get(0), savedDepartments.get(0));
        this.personDepartmentRepository.save(personDepartmentProfessor);
        
        this.personDepartmentService.deleteAllPersonDepartments();
        
        assertThat(this.personDepartmentRepository.findAll()).isEmpty();
    }
}
