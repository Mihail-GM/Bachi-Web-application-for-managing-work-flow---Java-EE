package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Calendar;



@Repository("calendarRepository")


public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	Calendar findByName(String name);
}
