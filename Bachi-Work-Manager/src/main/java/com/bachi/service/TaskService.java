package com.bachi.service;

import java.util.Date;
import java.util.List;

import com.bachi.model.TaskHistory;
import com.bachi.model.Tasks;

public interface TaskService {
	//void addTask(String name, long user, Date startDate, Date endDate, int status, int clientId,int totalHours);
	public List<Tasks> findAll();
	

	
	

	public void addReport(String name, int Project_ID, String Description, String start_date, String end_date, int Status_ID,
			int Priority_ID, float Total_Hours, int Employee_ID, float Complete);

	public void delete(String id);

	

	public void updateProgres(String id, float complete);
	
	public void updateRecordHistory(String taskID, String name, int Project_ID, String Description, String start_date, String end_date, int Status_ID,
			int Priority_ID, float Total_Hours, int Employee_ID, float Complete);





	List<Tasks> findAllPerUser(int id);
	
	
}
