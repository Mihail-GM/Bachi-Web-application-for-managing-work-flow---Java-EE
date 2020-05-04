package com.bachi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bachi.model.Calendar;
import com.bachi.model.Project;
import com.bachi.repository.ProjectRepository;

@Service("calendarService")
public class CalendarServiceIml implements CalendarService {

	  @Autowired
	    private DataSource dataSource;
	    @Autowired
	    private ProjectRepository prRepository;
	    @Autowired
	    private JdbcTemplate template;
	    
	    
	    
	    
	    //добавяне на проект
	    @Override
		public void addEvent(String name, Date dateEvent, String description, int employeeID,  int priorithy) {

			 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	         jdbcTemplate.update("INSERT INTO `calendar` (`Name`, `Date_Event`, `Description`, `Employee_ID`, `Priority_ID`) VALUES (?,?,?,?,?)" ,name, dateEvent,  description,  employeeID,   priorithy );
	         
		}
	    
	    

	    
	  

		@Override
		public List<Calendar> findAll(int employeeID) {
			String sql = "SELECT c.* , s.`Priority_Descruption` FROM `calendar` c LEFT JOIN `priority` s ON c.`Priority_ID` = s.`id_Priority` WHERE c.`Employee_ID` = "+employeeID+"";
	        RowMapper<Calendar> rm = new RowMapper<Calendar>() {
	            @Override
	            public Calendar mapRow(ResultSet resultSet, int i) throws SQLException {
	            	Calendar project = new Calendar(resultSet.getInt("id_event"),
	            			resultSet.getString("Name"),
	                		resultSet.getString("Date_Event"), 
	                		resultSet.getString("Description"), 
	                		resultSet.getInt("Employee_ID"), 
	                		resultSet.getInt("Priority_ID"),
	                		resultSet.getString("Priority_Descruption"));
	                		
	                	
	             
	             
	            

	                return project;
	            }
	        };

	   
			return template.query(sql, rm);
		}


		
		  
		
		
}
