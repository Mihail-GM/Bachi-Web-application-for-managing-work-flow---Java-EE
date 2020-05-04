package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tasks")
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_task")
	private int id_task;
	
	@Column(name = "Task_Name")
	private String name;
	
    @Column(name = "Project_ID")
    @NotEmpty(message = "*Моля посочете проект" )
	private int  project_ID;
	
    @Column(name = "Description")
	private String description;
    
	@Column(name = "start_date")
	private String start_date;
	
	@Column(name = "end_date")
	private String end_date;
	
	@Column(name = "Status_ID")
	@NotEmpty(message = "*Моля посочете статус" )
	private String status_id;
	
	@Column(name = "Priority_ID")
	@NotEmpty(message = "*Моля посочете приоритет" )
	private int pririthyID;
	
	
	@Column(name = "Total_Hours")
	private float totalHours;
	
	@Column(name = "Employee_ID")
	private int empID;
	
	@Column(name = "Complete")
	private float complete;
	

	 @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "task")
	private TaskHistory historys;
	
	 
	 

	public Tasks() {
		
	}



	public Tasks(int id_task, String name, int project_ID,  String descrip,  String start_date, String end_date, String status_id,
			int pririthyID, float totalHours, int empID, float complete) {
		super();
		this.id_task = id_task;
		this.name = name;
		this.project_ID = project_ID;
		this.description = descrip;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status_id = status_id;
		this.pririthyID = pririthyID;
		this.totalHours = totalHours;
		this.empID = empID;
		this.complete = complete;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getId_task() {
		return id_task;
	}

	public void setId_task(int id_task) {
		this.id_task = id_task;
	}

	

	public int getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(int project_ID) {
		this.project_ID = project_ID;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public int getPririthyID() {
		return pririthyID;
	}

	public void setPririthyID(int pririthyID) {
		this.pririthyID = pririthyID;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public float getComplete() {
		return complete;
	}

	public void setComplete(float complete) {
		this.complete = complete;
	}



	public TaskHistory getHistorys() {
		return historys;
	}



	public void setHistorys(TaskHistory historys) {
		this.historys = historys;
	}
	
	
	
	
	
}
