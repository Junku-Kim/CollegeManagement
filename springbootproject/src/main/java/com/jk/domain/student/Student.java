package com.jk.domain.student;

import java.util.HashSet;
import java.util.Set;

import com.jk.domain.person.Address;
import com.jk.domain.person.Person;
import com.jk.domain.student.enrollment.StudentEnrollment;
import com.jk.domain.student.semester.StudentSemester;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("STUDENT")
@Entity
public class Student extends Person {

	private int tuition;
	
	private int academicYear;
	
	@Override
	public String getDtype() {
		return  "STUDENT";
	}
	
	@OneToMany(mappedBy = "student")
	private Set<StudentSemester> studentSemesters = new HashSet<>();
	
	public void addStudentSemester(StudentSemester studentSemester) {
		if (this.studentSemesters.add(studentSemester)) {
			studentSemester.getSemester().addStudentSemester(studentSemester);
		}
	}
	
	public void removeStudentSemester(StudentSemester studentSemester) {
		if (this.studentSemesters.remove(studentSemester)) {
			if (studentSemester.getSemester().getStudentSemesters().contains(studentSemester)) {
				studentSemester.getSemester().removeStudentSemester(studentSemester);
				studentSemester.removeStudentSemester();
			}
		}
	}
	
	@OneToMany(mappedBy = "student")
	private Set<StudentEnrollment> studentEnrollments = new HashSet<>();

	public void addStudentEnrollment(StudentEnrollment studentEnrollment) {
		if (this.studentEnrollments.add(studentEnrollment)) {
			studentEnrollment.getSubjectSemester().addStudentEnrollment(studentEnrollment);
		}
	}
	
	public void removeStudentEnrollment(StudentEnrollment studentEnrollment) {
		if (this.studentEnrollments.remove(studentEnrollment)) {
			if (studentEnrollment.getSubjectSemester().getStudentEnrollments().contains(studentEnrollment)) {
				studentEnrollment.getSubjectSemester().removeStudentEnrollment(studentEnrollment);
				studentEnrollment.removeStudentEnrollment();
			}
		}
	}
	
	@Builder
	public Student(String name, String loginId, String password, String phoneNumber, String email, Address address, int tuition, int academicYear) {
		super(name, loginId, password, phoneNumber, email, address);
		this.tuition = tuition;
		this.academicYear = academicYear;
	}

	public Student withName(String newName) {
		return new Student(newName, getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), getTuition(), getAcademicYear());
	}
	
	public Student withPassword(String newPassword) {
        return new Student(getName(), getLoginId(), newPassword, getPhoneNumber(), getEmail(), getAddress(), getTuition(), getAcademicYear());
    }

    public Student withPhoneNumber(String newPhoneNumber) {
        return new Student(getName(), getLoginId(), getPassword(), newPhoneNumber, getEmail(), getAddress(), getTuition(), getAcademicYear());
    }

    public Student withEmail(String newEmail) {
        return new Student(getName(), getLoginId(), getPassword(), getPhoneNumber(), newEmail, getAddress(), getTuition(), getAcademicYear());
    }

    public Student withAddress(Address newAddress) {
        return new Student(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), newAddress, getTuition(), getAcademicYear());
    }
    
    public Student withTuition(int newTuition) {
        return new Student(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), newTuition, getAcademicYear());
    }

    public Student withAcademicYear(int newAcademicYear) {
        return new Student(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), getTuition(), newAcademicYear);
    }
    
	@Override
	public String toString() {
		return super.toString() + "Student [tuition=" + tuition + ", academicYear=" + academicYear;
	}
}
