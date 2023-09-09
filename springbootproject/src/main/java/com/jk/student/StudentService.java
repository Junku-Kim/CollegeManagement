package com.jk.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.person.PersonRepository;
import com.jk.person.PersonService;


@Transactional
@Service
public class StudentService extends PersonService{

	private final StudentRepository studentRepository;
	
	public StudentService(PersonRepository personRepository, StudentRepository studentRepository) {
		super(personRepository);
		this.studentRepository = studentRepository;
	}

	public String registerStudent(Student student) {
		validateStudentForCreation(student);
		this.studentRepository.save(student);
		
		return student.getLoginId();
	}
	
	private void validateStudentForCreation(Student student) {
		this.studentRepository.findByLoginId(student.getLoginId())
				.ifPresent(s -> {
					throw new IllegalStateException("이미 존재하는 학생입니다.");
				});
	}
	
	public Long deleteStudent(Student student) {
		this.studentRepository.delete(student);
		
		return student.getId();
	}
	
	public void deleteByLoginIdStudent(String loginId) {
		this.studentRepository.deleteByLoginId(loginId);
	}
}
