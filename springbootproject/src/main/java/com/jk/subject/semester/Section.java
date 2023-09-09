package com.jk.subject.semester;

public enum Section {

	SECTION_01(1),
	SECTION_02(2),
	SECTION_03(3),
	SECTION_04(4),
	SECTION_05(5);
	
	private final int value;
	
	Section(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
