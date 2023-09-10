package com.jk.domain.department;

import java.util.HashSet;
import java.util.Set;

import com.jk.domain.person.department.PersonDepartment;
import com.jk.domain.subject.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SequenceGenerator(
		name = "DEPARTMENT_ID_SEQ_GENERATOR",
		sequenceName = "DEPARTMENT_ID_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DEPARTMENT_ID_SEQ_GENERATOR")
	@Column(name = "DEPARTMENT_ID")
	private Long id;
	
	private String major;
	
	private String faculty;
	
	private String college;
	
	@OneToMany(mappedBy = "department")
	private Set<PersonDepartment> personDepartments = new HashSet<>();
	
	public void addPersonDepartment(PersonDepartment personDepartment) {
		if (this.personDepartments.add(personDepartment)) {
			personDepartment.getPerson().addPersonDepartment(personDepartment);
		}
	}
	
	public void removePersonDepartment(PersonDepartment personDepartment) {
		if (this.personDepartments.remove(personDepartment)) {
			if (personDepartment.getPerson().getPersonDepartments().contains(personDepartment)) {
				personDepartment.getPerson().removePersonDepartment(personDepartment);
				personDepartment.removePersonDepartment();
			}
		}
	}
	
	@OneToMany(mappedBy = "department")
	private Set<Subject> subjects = new HashSet<>();
	
	public void addSubject(Subject subject) {
		if (this.subjects.add(subject)) {
			subject.setDepartment(this);
		}
	}
	
	public void removeSubject(Subject subject) {
		this.subjects.remove(subject);
	}
	
	@Builder
	public Department(String major, String faculty, String college) {
		this.major = major;
		this.faculty = faculty;
		this.college = college;
	}

	public Department withMajor(String newMajor) {
		return Department.builder()
						 .major(newMajor)
						 .faculty(getFaculty())
						 .college(getCollege())
						 .build();
	}
	
	public Department withFaculty(String newFaculty) {
		return Department.builder()
				 .major(getMajor())
				 .faculty(newFaculty)
				 .college(getCollege())
				 .build();
    }

    public Department withCollege(String newCollege) {
    	return Department.builder()
				 .major(getMajor())
				 .faculty(getFaculty())
				 .college(newCollege)
				 .build();
    }

	@Override
	public String toString() {
		return "Department [id=" + id + ", major=" + major + ", faculty=" + faculty + ", college=" + college;
	}
}