package com.jk.professor;

import java.util.HashSet;
import java.util.Set;

import com.jk.employee.Employee;
import com.jk.person.Address;
import com.jk.subject.semester.SubjectSemester;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("PROFESSOR")
@Entity
public class Professor extends Employee {

	@Enumerated(EnumType.STRING)
	private Rank rank;
	
	@Override
	public String getDtype() {
		return  "PROFESSOR";
	}
	
	@OneToMany(mappedBy = "professor")
	private Set<SubjectSemester> subjectSemesters = new HashSet<>();
	
	public void addSubjectSemester(SubjectSemester subjectSemester) {
		if (this.subjectSemesters.add(subjectSemester)) {
			subjectSemester.setProfessor(this);
		}
	}
	
	public void removeSubjectSemester(SubjectSemester subjectSemester) {
		this.subjectSemesters.remove(subjectSemester);
	}
	
	@Builder
	public Professor(String name, String loginId, String password, String phoneNumber, String email, Address address, int salary, String location, String officeNumber, Rank rank) {
		super(name, loginId, password, phoneNumber, email, address, salary, location, officeNumber);
		this.rank = rank;
	}

	public Professor withName(String newName) {
	    return Professor.builder()
	            .name(newName)
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}
	
	public Professor withLoginId(String newLoginId) {
		return Professor.builder()
				        .name(getName())
						.loginId(newLoginId)
						.password(getPassword())
						.phoneNumber(getPhoneNumber())
						.email(getEmail())
						.address(getAddress())
						.salary(getSalary())
						.location(getLocation())
						.officeNumber(getOfficeNumber())
						.rank(getRank())
						.build();
	}

	public Professor withPassword(String newPassword) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(newPassword)
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withPhoneNumber(String newPhoneNumber) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(newPhoneNumber)
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withEmail(String newEmail) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(newEmail)
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withAddress(Address newAddress) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(newAddress)
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withSalary(int newSalary) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(newSalary)
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withLocation(String newLocation) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(newLocation)
	            .officeNumber(getOfficeNumber())
	            .rank(getRank())
	            .build();
	}

	public Professor withOfficeNumber(String newOfficeNumber) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(newOfficeNumber)
	            .rank(getRank())
	            .build();
	}

	public Professor withRank(Rank newRank) {
	    return Professor.builder()
	            .name(getName())
	            .loginId(getLoginId())
	            .password(getPassword())
	            .phoneNumber(getPhoneNumber())
	            .email(getEmail())
	            .address(getAddress())
	            .salary(getSalary())
	            .location(getLocation())
	            .officeNumber(getOfficeNumber())
	            .rank(newRank)
	            .build();
	}

	@Override
	public String toString() {
		return super.toString() + "Professor [rank=" + rank + "]";
	}
	
}
