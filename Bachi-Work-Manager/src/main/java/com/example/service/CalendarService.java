package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.Calendar;
import com.example.model.Project;

public interface CalendarService {
	void addEvent(String name, Date dateEvent,  String description, int employeeID,  int priorithy);




	public List<Calendar> findAll(int employeeID);


	


}
