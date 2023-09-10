package com.jk.domain.schedule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ScheduleTest {

	private List<Schedule> schedules;
	
	@BeforeEach
	public void SetUp() {
		this.schedules
		= Arrays.asList(
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(9)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(10)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(1)
						.hourOfDay(11)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(3)
						.hourOfDay(15)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(3)
						.hourOfDay(16)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(4)
						.hourOfDay(13)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(4)
						.hourOfDay(14)
						.build(),
						
				Schedule.builder()
						.dayOfWeek(5)
						.hourOfDay(9)
						.build()
		);
	}
	
	@Test
	public void 요일_변경() {
		Schedule schedule = this.schedules.get(6);
		Schedule newSchedule = schedule.withDayOfWeek(2);
		
		assertThat(newSchedule.getDayOfWeek()).isEqualTo(2);
	}
	
	@Test
	public void 시간_변경() {
		Schedule schedule = this.schedules.get(6);
		Schedule newSchedule = schedule.withHourOfDay(8);
		
		assertThat(newSchedule.getHourOfDay()).isEqualTo(8);
	}
	
	@Test
	public void 부적절한_시간표_등록_예외() {
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
				() -> Schedule.builder().dayOfWeek(0).hourOfDay(10).build());
		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
				() -> Schedule.builder().dayOfWeek(8).hourOfDay(10).build());
		IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class,
				() -> Schedule.builder().dayOfWeek(1).hourOfDay(-1).build());
		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class,
				() -> Schedule.builder().dayOfWeek(1).hourOfDay(24).build());
		
		assertThat(e1.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e2.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e3.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e4.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
	}
	
	@Test
	public void 부적절한_시간표_변경_예외() {
		Schedule schedule = this.schedules.get(0);
		
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
				() -> schedule.withDayOfWeek(0));
		IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
				() -> schedule.withDayOfWeek(8));
		IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class,
				() -> schedule.withHourOfDay(-1));
		IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class,
				() -> schedule.withHourOfDay(24));
		
		assertThat(e1.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e2.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e3.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
		assertThat(e4.getMessage()).isEqualTo("유효하지 않은 요일 또는 시간입니다.");
	}
}
