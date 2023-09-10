package com.jk.domain.student;

import java.util.List;
import java.util.Optional;

import com.jk.domain.person.Person;

public class StudentUtils {

	public static Optional<Student> filterStudentOptional(Optional<Person> personOptional) {
		return personOptional.filter(p -> p instanceof Student)
							 .map(p -> (Student) p);
	}
	
	public static List<Student> filterStudents(List<Person> people) {
		return people.stream()
					 .filter(p -> p instanceof Student)
					 .map(p -> (Student) p)
					 .toList();
	}
}
