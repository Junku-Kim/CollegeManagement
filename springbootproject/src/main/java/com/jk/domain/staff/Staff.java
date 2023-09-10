package com.jk.domain.staff;

import com.jk.domain.employee.Employee;
import com.jk.domain.person.Address;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("STAFF")
@Getter
@Entity
public class Staff extends Employee{

	@Override
	public String getDtype() {
		return  "STAFF";
	}
	
	@Builder
	public Staff(String name, String loginId, String password, String phoneNumber, String email, Address address,int salary, String location, String officeNumber) {
		super(name, loginId, password, phoneNumber, email, address, salary, location, officeNumber);
	}

	public Staff withName(String newName) {
        return new Staff(newName, getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), getSalary(), getLocation(), getOfficeNumber());
    }

    public Staff withPassword(String newPassword) {
        return new Staff(getName(), getLoginId(), newPassword, getPhoneNumber(), getEmail(), getAddress(), getSalary(), getLocation(), getOfficeNumber());
    }

    public Staff withPhoneNumber(String newPhoneNumber) {
        return new Staff(getName(), getLoginId(), getPassword(), newPhoneNumber, getEmail(), getAddress(), getSalary(), getLocation(), getOfficeNumber());
    }

    public Staff withEmail(String newEmail) {
        return new Staff(getName(), getLoginId(), getPassword(), getPhoneNumber(), newEmail, getAddress(), getSalary(), getLocation(), getOfficeNumber());
    }

    public Staff withAddress(Address newAddress) {
        return new Staff(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), newAddress, getSalary(), getLocation(), getOfficeNumber());
    }

    public Staff withSalary(int newSalary) {
        return new Staff(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), newSalary, getLocation(), getOfficeNumber());
    }

    public Staff withLocation(String newLocation) {
        return new Staff(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), getSalary(), newLocation, getOfficeNumber());
    }

    public Staff withOfficeNumber(String newOfficeNumber) {
        return new Staff(getName(), getLoginId(), getPassword(), getPhoneNumber(), getEmail(), getAddress(), getSalary(), getLocation(), newOfficeNumber);
    }

	@Override
	public String toString() {
		return super.toString() + "Staff []";
	}
	
	
}
