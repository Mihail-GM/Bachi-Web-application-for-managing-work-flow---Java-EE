 package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;


@Entity
@Table(name = "projects")
public class Project {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_project")
	private int id_project;
	
	@Column(name = "Project_Name")
	private String name;
	
    @Column(name = "Project_Manager_ID")
	private int  project_ID;
	
	@Column(name = "StartDate")
	@NotEmpty(message = "*Моля посочете начална дата" )
	private String start_date;
	
	@Column(name = "EndDate")
	private String end_date;
	
	@Column(name = "Status_ID")
	private String status_id;
	
	@Column(name = "Client_ID")
	private String client_id;
	
	
	@Column(name = "TotalHours")
	private float total_Hour_Worked;


private String status;
public String nameEmp;
	
	public Project(int int1, String string, String objStatus) {
		// TODO Auto-generated constructor stub
		this.id_project = int1;
		this.name = string;
        this.status = objStatus;
	}




	public Project(int id, String projectName, String empName, String startDate, 
			String endDate, String statusProject, float totalHours) {
		// TODO Auto-generated constructor stub
		this.id_project = id;
		this.name = projectName;
        this.status = statusProject;
        this.nameEmp = empName;
        this.start_date = startDate;
        this.end_date = endDate;
        this.total_Hour_Worked = totalHours;
        
        
	}




	public int getId_project() {
		return id_project;
	}


	public void setId_project(int id_project) {
		this.id_project = id_project;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getClient_id() {
		return client_id;
	}


	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}


	public float getTotal_Hour_Worked() {
		return total_Hour_Worked;
	}


	public void setTotal_Hour_Worked(float total_Hour_Worked) {
		this.total_Hour_Worked = total_Hour_Worked;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}




	public String getNameProject() {
		return nameEmp;
	}




	public void setNameProject(String nameProject) {
		this.nameEmp = nameProject;
	}

	
	
}
