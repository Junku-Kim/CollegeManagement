package com.jk.domain.semester;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SemesterTest {

	@Test
	public void 학기이름_변경() {
		Semester semester = Semester.builder().semesterName("2023-1").build();
		Semester updateSemester = semester.withSemesterName("2023년 1학기");
		
		assertThat(updateSemester.getSemesterName()).isEqualTo("2023년 1학기");
	}
}
