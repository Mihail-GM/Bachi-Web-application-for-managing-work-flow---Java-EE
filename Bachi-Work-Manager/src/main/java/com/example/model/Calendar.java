package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_event")
	private int eventID;
	
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Date_Event")
	private String dateEvent;
	
    @Column(name = "Description")
	private String  description;
	
    @Column(name = "Employee_ID")
	private int employeeID;
    
	@Column(name = "Priority_ID")
	private int priotityID;
	
	
	private String priorithyName;
	
	

	public Calendar() {
	
	}

	public Calendar(int eventID, String name, String dateEvent, String description, int employeeID, int priotityID, String priorithyName) {
		super();
		this.eventID = eventID;
		this.name = name;
		this.dateEvent = dateEvent;
		this.description = description;
		this.employeeID = employeeID;
		this.priotityID = priotityID;
		this.priorithyName = priorithyName;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getPriotityID() {
		return priotityID;
	}

	public void setPriotityID(int priotityID) {
		this.priotityID = priotityID;
	}

	public String getPriorithyName() {
		return priorithyName;
	}

	public void setPriorithyName(String priorithyName) {
		this.priorithyName = priorithyName;
	}
	

	
	
}
