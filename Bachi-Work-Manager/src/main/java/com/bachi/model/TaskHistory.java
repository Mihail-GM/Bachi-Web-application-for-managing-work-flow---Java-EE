package com.bachi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tasks_history")
public class TaskHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_history")
	private int idHistory;
	
	@Column(name = "Task_ID")
	private String idTask;
	
	@Column(name = "employee_id")
	private String idEmployee;
	
    @Column(name = "Worked_Hours")
    @NotEmpty(message = "*Моля посочете проект" )
	private float  workHours;
	
    @Column(name = "Work_Done_Desctiption")
	private String workDoneDescription;
    
    @Column(name = "Work_Done_Issues")
	private String workDoneIssue;
    
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	
	@Column(name = "Complete_Before")
	private Float completeBefore;
	
	
	@Column(name = "Complete_Now")
	private Float completeNow;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_task_task_history_fk", nullable = false)
    private Tasks task;
	
	
	
	private String nameEmployee;
	 
	
	public TaskHistory() {
		
	}


	public TaskHistory(int idHistory, String idEmployeeobj,  float workHours, String workDoneDescription, String workDoneIssue,
			String startDate, String endDate, Float completeBefore, Float completeNow) {

		this.nameEmployee = idEmployeeobj;
		this.idHistory = idHistory;
		this.workHours = workHours;
		this.workDoneDescription = workDoneDescription;
		this.workDoneIssue = workDoneIssue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.completeBefore = completeBefore;
		this.completeNow = completeNow;
	}

	
	

	public int getIDHistory() {
		return idHistory;
	}


	public void setIDHistory (int name) {
		this.idHistory = name;
	}


	public String getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}


	public Tasks getTask() {
		return task;
	}


	public void setTask(Tasks task) {
		this.task = task;
	}


	public TaskHistory(int int1, int int2, int int3, float float1, String string, String string2, String string3,
			String string4, float float2, float float3) {
		// TODO Auto-generated constructor stub
	}




	public TaskHistory(float worked, String starDate, float completeBefore, float completenow) {
		// TODO Auto-generated constructor stub
		this.workHours = worked;
		this.startDate = starDate;
		this.completeBefore = completeBefore;
		this.completeNow = completenow;
	}


	public String getIdTask() {
		return idTask;
	}


	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}


	public float getWorkHours() {
		return workHours;
	}


	public void setWorkHours(float workHours) {
		this.workHours = workHours;
	}


	public String getWorkDoneDescription() {
		return workDoneDescription;
	}


	public void setWorkDoneDescription(String workDoneDescription) {
		this.workDoneDescription = workDoneDescription;
	}


	public String getWorkDoneIssue() {
		return workDoneIssue;
	}


	public void setWorkDoneIssue(String workDoneIssue) {
		this.workDoneIssue = workDoneIssue;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public Float getCompleteBefore() {
		return completeBefore;
	}


	public void setCompleteBefore(Float completeBefore) {
		this.completeBefore = completeBefore;
	}


	public Float getCompleteNow() {
		return completeNow;
	}


	public void setCompleteNow(Float completeNow) {
		this.completeNow = completeNow;
	}


	public String getNameEmployee() {
		return nameEmployee;
	}


	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}
	



	
	
	
	
}
