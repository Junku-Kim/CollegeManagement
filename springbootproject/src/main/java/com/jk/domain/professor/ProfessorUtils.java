package com.jk.domain.professor;

import java.util.List;
import java.util.Optional;

import com.jk.domain.person.Person;

public class ProfessorUtils {
	
	public static Optional<Professor> filterProfessorOptional(Optional<Person> personOptional) {
		return personOptional.filter(p -> p instanceof Professor)
							 .map(p -> (Professor)p);
	}
	
	public static List<Professor> filterProfessors(List<Person> people) {
        return people.stream()
                      .filter(p -> p instanceof Professor)
                      .map(p -> (Professor) p)
                      .toList();
    }
}
