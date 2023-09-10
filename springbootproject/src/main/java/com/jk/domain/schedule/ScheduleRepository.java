package com.jk.domain.schedule;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

	Optional<Schedule> findByDayOfWeekAndHourOfDay(int dayOfWeek, int hourOfDay);
	
	void deleteByDayOfWeekAndHourOfDay(int dayOfWeek, int hourOfDay);
}
