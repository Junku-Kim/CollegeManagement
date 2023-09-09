package com.jk.staff;

import java.util.List;
import java.util.Optional;

import com.jk.person.Person;

public class StaffUtils {

	public static Optional<Staff> filterStaffOptional(Optional<Person> personOptional) {
		return personOptional.filter(p -> p instanceof Staff)
							 .map(p -> (Staff) p);
	}
	
	public static List<Staff> fiterStaffs(List<Person> people) {
		return people.stream()
					 .filter(p -> p instanceof Staff)
					 .map(p -> (Staff) p)
					 .toList();
	}
}
