package com.bachi.service;

import java.util.List;

import com.bachi.model.TaskHistory;

public interface TaskHistoryService {
	public List<TaskHistory> findAll(String id);
	
	public void addTaskHistory(String taskID, int employeeID, float workedHours, String workDoneDescription, String workIssueDescription, 
			String startDate, String endDate, float completeBefore, float completeNow);

	List<TaskHistory> findAllByUser(int i);

	List<TaskHistory> findAllHistory();

	List<TaskHistory> findAllDataForGraphic(int id);
	
}
