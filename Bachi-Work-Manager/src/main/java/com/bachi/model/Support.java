package com.bachi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "support")
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ticket")
	private int ticketID;
	
	
	@Column(name = "Employee_ID")
	private String employeeOwnerID;
	
	@Column(name = "Assign_TO_ID")
	private String asignEmployeeID;
	
    @Column(name = "Description")
	private String  description;
	
    @Column(name = "perrant_ticket_id")
	private int parrentID;
    

    @Column(name = "date")
	private String date;
    
	@Column(name = "Priority_ID")
	private int priotityID;
	
	@Column(name = "active")
	private int active;
	
	private String priorithy;
	

	public Support() {
		
	}

	public Support( String employeeOwnerID, String asignEmployeeID, String description, int parrentID,
			String date, int priotityID) {
	
		
		this.employeeOwnerID = employeeOwnerID;
		this.asignEmployeeID = asignEmployeeID;
		this.description = description;
		this.parrentID = parrentID;
		this.date = date;
		this.priotityID = priotityID;
	}
	
	
	

	public Support(int ticketID, String employeeOwnerID, String asignEmployeeID, String description, int parrentID,
			String date, int priotityID, String priority, int active) {
	
		this.ticketID = ticketID;
		this.employeeOwnerID = employeeOwnerID;
		this.asignEmployeeID = asignEmployeeID;
		this.description = description;
		this.parrentID = parrentID;
		this.date = date;
		this.priotityID = priotityID;
		this.priorithy = priority;
		this.active = active;
	}
	
	

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getEmployeeOwnerID() {
		return employeeOwnerID;
	}

	public void setEmployeeOwnerID(String employeeOwnerID) {
		this.employeeOwnerID = employeeOwnerID;
	}

	public String getAsignEmployeeID() {
		return asignEmployeeID;
	}

	public void setAsignEmployeeID(String asignEmployeeID) {
		this.asignEmployeeID = asignEmployeeID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParrentID() {
		return parrentID;
	}

	public void setParrentID(int parrentID) {
		this.parrentID = parrentID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPriotityID() {
		return priotityID;
	}

	public void setPriotityID(int priotityID) {
		this.priotityID = priotityID;
	}

	public String getPriorithy() {
		return priorithy;
	}

	public void setPriorithy(String priorithy) {
		this.priorithy = priorithy;
	}
	
	
	
	
	
	
	
	
	
	
	
}
