package com.jk.domain.schedule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ScheduleServiceTest {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleService scheduleService;
	
	List<Schedule> schedules;
	
	@BeforeEach
	void setUp() {
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
				.dayOfWeek(1)
				.hourOfDay(16)
				.build(),

				Schedule.builder()
				.dayOfWeek(2)
				.hourOfDay(12)
				.build(),

				Schedule.builder()
				.dayOfWeek(2)
				.hourOfDay(13)
				.build(),

				Schedule.builder()
				.dayOfWeek(4)
				.hourOfDay(17)
				.build(),

				Schedule.builder()
				.dayOfWeek(5)
				.hourOfDay(14)
				.build()
				);
	}
	
	@AfterEach
	void afterEach() {
		this.scheduleRepository.deleteAll();
	}
	
	@Test
	void 시간표_등록() {
		Schedule scheduleToResister = this.schedules.get(0);
		
		Long registerScheduleId = this.scheduleService.registerSchedule(scheduleToResister);
		
		assertThat(scheduleToResister.getId()).isEqualTo(registerScheduleId);
	}
	
	@Test
	void 중복_시간표_등록_예외() {
		Schedule scheduleToResister = this.schedules.get(0);
		
		this.scheduleService.registerSchedule(scheduleToResister);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> this.scheduleService.registerSchedule(scheduleToResister));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 Schedule입니다.");
	}
	
	@Test
	void 요일과_시간으로_시간표_조회() {
		List<Schedule> savedSchedules = this.scheduleRepository.saveAll(this.schedules);
		
		Optional<Schedule> foundScheduleOptional = this.scheduleService.findByDayOfWeekAndHourOfDay(1, 9);
		
		assertThat(foundScheduleOptional).isPresent();
		assertThat(foundScheduleOptional.get().getId()).isEqualTo(savedSchedules.get(0).getId());
		assertThat(foundScheduleOptional.get().getDayOfWeek()).isEqualTo(savedSchedules.get(0).getDayOfWeek());
		assertThat(foundScheduleOptional.get().getHourOfDay()).isEqualTo(savedSchedules.get(0).getHourOfDay());
	}
	
	@Test
	void 없는_요일과_시간으로_시간표_조회() {
		this.scheduleRepository.saveAll(this.schedules);
		
		Optional<Schedule> foundScheduleOptional = this.scheduleService.findByDayOfWeekAndHourOfDay(7, 9);
		
		assertThat(foundScheduleOptional).isNotPresent();
	}
	
	@Test
	void 전체_시간표_조회() {
		this.scheduleRepository.saveAll(this.schedules);
		
		List<Schedule> foundSchedules = this.scheduleService.findAllSchedules();
		
		assertThat(foundSchedules).hasSize(this.schedules.size());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
	void 시간표_삭제(int index) {
		List<Schedule> foundSchedules = this.scheduleRepository.saveAll(this.schedules);
		
		Long deleteScheduleId = this.scheduleService.deleteSchdule(this.schedules.get(index));
		
		List<Schedule> afterDeleteSchedules = this.scheduleRepository.findAll();
		
		assertThat(foundSchedules.get(index).getId()).isEqualTo(deleteScheduleId);
		assertThat(afterDeleteSchedules).extracting(Schedule::getId).doesNotContain(deleteScheduleId);
		assertThat(afterDeleteSchedules).hasSize(this.schedules.size() - 1);
	}
	
	@Test
	void 요일과_시간으로_시간표_삭제() {
		this.scheduleRepository.saveAll(this.schedules);
		this.scheduleService.deleteByDayOfWeekAndHourOfDay(1, 9);
		this.scheduleService.deleteByDayOfWeekAndHourOfDay(1, 9);
		this.scheduleService.deleteByDayOfWeekAndHourOfDay(1, 10);
		
		List<Schedule> foundSchedules = this.scheduleRepository.findAll();
		
		assertThat(foundSchedules).hasSize(this.schedules.size() - 2);
	}
	
	@Test
	void 시간표_전체_삭제() {
		this.scheduleRepository.saveAll(this.schedules);
		
		this.scheduleService.deletedAllSchedules();
		
		assertThat(this.scheduleRepository.findAll()).isEmpty();
	}
}
