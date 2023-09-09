package com.jk.schedule;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ScheduleService {

	public final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	
	public Long registerSchedule(Schedule schedule) {
		validateScheduleForCreation(schedule);
		this.scheduleRepository.save(schedule);
		
		return schedule.getId();
	}

	private void validateScheduleForCreation(Schedule schedule) {
		this.scheduleRepository
			.findByDayOfWeekAndHourOfDay(schedule.getDayOfWeek(), schedule.getHourOfDay())
			.ifPresent(s -> {
				throw new IllegalStateException("이미 존재하는 Schedule입니다.");
			});
	}
	
	public Optional<Schedule> findByDayOfWeekAndHourOfDay(int dayOfWeek, int hourOfDay) {
		return this.scheduleRepository.findByDayOfWeekAndHourOfDay(dayOfWeek, hourOfDay);
	}
	
	public List<Schedule> findAllSchedules() {
		return this.scheduleRepository.findAll();
	}
	
	public Long deleteSchdule(Schedule schedule) {
		this.scheduleRepository.delete(schedule);
		
		return schedule.getId();
	}
	
	public void deleteByDayOfWeekAndHourOfDay(int dayOfWeek, int hourOfDay) {
		this.scheduleRepository.deleteByDayOfWeekAndHourOfDay(dayOfWeek, hourOfDay);
	}
	
	public void deletedAllSchedules() {
		this.scheduleRepository.deleteAll();
	}
}
