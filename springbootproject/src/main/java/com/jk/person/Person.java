package com.jk.person;

import java.util.HashSet;
import java.util.Set;

import com.jk.person.department.PersonDepartment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(
		name = "PERSON_ID_SEQ_GENERATOR",
		sequenceName = "PERSON_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "PERSON_ID_SEQ_GENERATOR")
	@Column(name = "PERSON_ID")
	private Long id;
	
	private String name;
	
	private String loginId;
	
	private String password;
	
	private String phoneNumber;
	
	private String email;
	
	@Embedded
	private Address address;
	
	@Column(name = "DTYPE", insertable = false, updatable = false)
	private String dtype;
	
	public abstract String getDtype();

	@OneToMany(mappedBy = "person")
	private Set<PersonDepartment> personDepartments = new HashSet<>();
	
	public void addPersonDepartment(PersonDepartment personDepartment) {
		if (this.personDepartments.add(personDepartment)) {
	        personDepartment.getDepartment().addPersonDepartment(personDepartment);
		}
	}
	
	public void removePersonDepartment(PersonDepartment personDepartment) {
		if (this.personDepartments.remove(personDepartment)) {
			if (personDepartment.getDepartment().getPersonDepartments().contains(personDepartment)) {
				personDepartment.getDepartment().removePersonDepartment(personDepartment);
				personDepartment.removePersonDepartment();
			}
		}
	}
	
	public Person(String name, String loginId, String password, String phoneNumber, String email, Address address) {
		this.name = name;
		this.loginId = loginId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", loginId=" + loginId + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address;
	}
}