package com.jk.subject;

public enum SubjectType {

	MAJOR_REQUIRED(1),
	MAJOR_ELECTIVE(2),
	GENERAL_REQUIRED(11),
	GENERAL_ELECTIVE(12);
	
	private final int value;
	
	SubjectType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
