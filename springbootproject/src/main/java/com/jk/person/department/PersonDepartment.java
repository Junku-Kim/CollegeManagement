package com.jk.person.department;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jk.department.Department;
import com.jk.person.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name = "PERSON_DEPARTMENT_ID_SEQ_GENERATOR",
		sequenceName = "PERSON_DEPARTMENT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PersonDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "PERSON_DEPARTMENT_ID_SEQ_GENERATOR")
	@Column(name = "PERSON_DEPARTMENT_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;
	
	public void setPersonAndDepartment(Person person, Department department) {
		this.person = person;
		this.department = department;
		
		person.addPersonDepartment(this);
		department.addPersonDepartment(this);
	}
	
	private static final Set<PersonDepartment> PERSON_DEPARTMENTS = new HashSet<>();
	
	public static Set<PersonDepartment> getPersonDepartments() {
		return Collections.unmodifiableSet(PERSON_DEPARTMENTS);
	}
	
	public static void clearPersonDepartments() {
		PERSON_DEPARTMENTS.clear();
	}
	
	public static PersonDepartment getInstance(Person person, Department department) {
		for (PersonDepartment pd : PERSON_DEPARTMENTS) {
			if (pd.getPerson().equals(person) && pd.getDepartment().equals(department)) {
				return pd;
			}
		}
		
		PersonDepartment personDepartment = new PersonDepartment();
		personDepartment.setPersonAndDepartment(person, department);
		
		PERSON_DEPARTMENTS.add(personDepartment);
		
		return personDepartment;
	}
	
	public void removePersonDepartment() {
		if (PERSON_DEPARTMENTS.remove(this)) {
			this.getPerson().removePersonDepartment(this);
			this.person = null;
			this.department = null;
		}
	}

	@Override
	public String toString() {
		return "PersonDepartment [person=" + person + ", department=" + department + "]";
	}
}