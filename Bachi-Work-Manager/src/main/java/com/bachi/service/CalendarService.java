package com.bachi.service;

import java.util.Date;
import java.util.List;

import com.bachi.model.Calendar;
import com.bachi.model.Project;

public interface CalendarService {
	void addEvent(String name, Date dateEvent,  String description, int employeeID,  int priorithy);




	public List<Calendar> findAll(int employeeID);


	


}
