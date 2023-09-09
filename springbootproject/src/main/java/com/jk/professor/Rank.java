package com.jk.professor;

public enum Rank {

	ASSISTANT_PROFESSOR(1),
	ASSOCIATE_PROFESSOR(2),
	FULL_PROFESSOR(3),
	EMERITUS_PROFESSOR(4),
	ADJUNCT_PROFESSOR(11),
	VISITING_PROFESSOR(12),
	PART_TIME_LECTURER(13);
	
	private final int value;
	
	Rank(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
