package com.bachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bachi.model.Calendar;



@Repository("calendarRepository")


public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	Calendar findByName(String name);
}
