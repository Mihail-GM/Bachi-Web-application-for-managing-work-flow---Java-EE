package com.example.service;

import java.util.List;

import com.example.model.Support;

public interface SupportService {
	 List<Support> findByPerrantId(int id);
	void addSupportAnswer(int employeeID, int asignEmpployeID, String Description, int perrantid, String date,
			int priority);
	void addSupport(int employeeID, int asignEmpployeID, String Description, String date, int priority);
	void replySupport(int employeeID, int asignEmpployeID, String Description, String date, int priority,
			int perrantID, int active);
	List<Support> findAllMessagesByPerrantId(int parrentID);
	
}
