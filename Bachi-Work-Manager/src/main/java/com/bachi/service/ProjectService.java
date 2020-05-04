package com.bachi.service;

import java.util.Date;
import java.util.List;

import com.bachi.model.Project;


public interface ProjectService {
	void addProject(String name, int user, Date startDate, Date endDate, int status,  int totalHours);




	public List<Project> findAll();




	List<Project> findAllByUser(int id);


	


}
