package com.bachi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.bachi.model.TaskHistory;
import com.bachi.repository.TaskRepository;

@Service("taskHistoryService")
public class TaskHistoryServiceIml implements TaskHistoryService {
	
	
	@Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate template;
    
    
    @Override
	public  List<TaskHistory>  findAllHistory() {
		String sql = "SELECT t.* , e.name FROM `tasks_history` t LEFT JOIN `employee` e ON t.`employee_id` = e.id_Employee  ";
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
         
        RowMapper<TaskHistory> rm = new RowMapper<TaskHistory>() {
            @Override
            public TaskHistory mapRow(ResultSet resultSet, int i) throws SQLException {
            	TaskHistory taskHistory = new TaskHistory(resultSet.getInt("id_history"),
            			resultSet.getString("name"), 
            			resultSet.getFloat("Worked_Hours"), 
                		
                		resultSet.getString("Work_Done_Desctiption"), 
                		resultSet.getString("Work_Done_Issues"), 
                		resultSet.getString("start_date"),
                		resultSet.getString("end_date"), 
                		resultSet.getFloat("Complete_Before"), 
                		resultSet.getFloat("Complete_Now"));


                return taskHistory;
            }
        };
    	return template.query(sql, rm);
    }
	@Override
	public  List<TaskHistory>  findAll(String id) {
		String sql = "SELECT t.* , e.name FROM `tasks_history` t LEFT JOIN `employee` e ON t.`employee_id` = e.id_Employee WHERE `Task_ID` = "+id+" ";
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
         
        RowMapper<TaskHistory> rm = new RowMapper<TaskHistory>() {
            @Override
            public TaskHistory mapRow(ResultSet resultSet, int i) throws SQLException {
            	TaskHistory taskHistory = new TaskHistory(resultSet.getInt("id_history"),
            			resultSet.getString("name"), 
            			resultSet.getFloat("Worked_Hours"), 
                		
                		resultSet.getString("Work_Done_Desctiption"), 
                		resultSet.getString("Work_Done_Issues"), 
                		resultSet.getString("start_date"),
                		resultSet.getString("end_date"), 
                		resultSet.getFloat("Complete_Before"), 
                		resultSet.getFloat("Complete_Now"));


                return taskHistory;
            }
        };

   
		return template.query(sql, rm);
	}
	
	@Override
	public  List<TaskHistory>  findAllByUser(int id) {
		String sql = "SELECT t.* , e.name FROM `tasks_history` t LEFT JOIN `employee` e ON t.`employee_id` = e.id_Employee WHERE `employee_id` = "+id+" ";
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
         
        RowMapper<TaskHistory> rm = new RowMapper<TaskHistory>() {
            @Override
            public TaskHistory mapRow(ResultSet resultSet, int i) throws SQLException {
            	TaskHistory taskHistory = new TaskHistory(resultSet.getInt("id_history"),
            			resultSet.getString("name"), 
            			resultSet.getFloat("Worked_Hours"), 
                		
                		resultSet.getString("Work_Done_Desctiption"), 
                		resultSet.getString("Work_Done_Issues"), 
                		resultSet.getString("start_date"),
                		resultSet.getString("end_date"), 
                		resultSet.getFloat("Complete_Before"), 
                		resultSet.getFloat("Complete_Now"));


                return taskHistory;
            }
        };

   
		return template.query(sql, rm);
	}
	
	


	@Override
	public void addTaskHistory(String taskID, int employeeID, float workedHours, String workDoneDescription,
			String workIssueDescription, String startDate, String endDate, float completeBefore, float completeNow) {
		// TODO Auto-generated method stub
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         jdbcTemplate.update("INSERT INTO `tasks_history` ( `Task_ID`, `employee_id`, `Worked_Hours`, `Work_Done_Desctiption`, `Work_Done_Issues`, `start_date`, `end_date`, `Complete_Before`, `Complete_Now`) "
         		+ "VALUES (?,?,?,?,?,?,?,?,?)",
         		taskID, employeeID, workedHours,  workDoneDescription, workIssueDescription, startDate, endDate, completeBefore, completeNow );
       
           System.out.println("Успешно добавяне на запис, преди %: " + completeBefore);
           
           
           
		
	}
	
	
	// извличане на данни за графика за една задача
	@Override
	public  List<TaskHistory>  findAllDataForGraphic(int id) {
		String sql = "SELECT * FROM `tasks_history` WHERE `Task_ID` =  "+id+" ";
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       
         
        RowMapper<TaskHistory> rm = new RowMapper<TaskHistory>() {
            @Override
            public TaskHistory mapRow(ResultSet resultSet, int i) throws SQLException {
            	TaskHistory taskHistory = new TaskHistory(resultSet.getFloat("Worked_Hours"), 
                		resultSet.getString("start_date"),
                		resultSet.getFloat("Complete_Before"), 
                		resultSet.getFloat("Complete_Now"));


                return taskHistory;
            }
        };

   
		return template.query(sql, rm);
	}
	

}
