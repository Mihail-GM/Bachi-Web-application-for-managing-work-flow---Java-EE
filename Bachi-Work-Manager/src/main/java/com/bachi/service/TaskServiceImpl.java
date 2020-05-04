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
import com.bachi.model.Tasks;
import com.bachi.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
    private DataSource dataSource;
    @Autowired
    private TaskRepository prRepository;
    @Autowired
    private JdbcTemplate template;
    
    
	@Override
	public List<Tasks> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `tasks` WHERE 1";
        RowMapper<Tasks> rm = new RowMapper<Tasks>() {
            @Override
            public Tasks mapRow(ResultSet resultSet, int i) throws SQLException {
                Tasks task = new Tasks(resultSet.getInt("id_task"),
                		resultSet.getString("Task_Name"), 
                		
                		resultSet.getInt("Project_ID"), 
                		resultSet.getString("Description"), 
                		resultSet.getString("start_date"), 
                		resultSet.getString("end_date"), 
                		resultSet.getString("Status_ID"),
                		resultSet.getInt("Priority_ID"), 
                		resultSet.getFloat("Total_Hours"), 
                		resultSet.getInt("Employee_ID"), 
                		resultSet.getFloat("Complete"));
                		
                	
             
     
            

                return task;
            }
        };

   
		return template.query(sql, rm);
	}

	
	@Override
	public List<Tasks> findAllPerUser(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `tasks` WHERE `Employee_ID` = "+id+"";
        RowMapper<Tasks> rm = new RowMapper<Tasks>() {
            @Override
            public Tasks mapRow(ResultSet resultSet, int i) throws SQLException {
                Tasks task = new Tasks(resultSet.getInt("id_task"),
                		resultSet.getString("Task_Name"), 
                		
                		resultSet.getInt("Project_ID"), 
                		resultSet.getString("Description"), 
                		resultSet.getString("start_date"), 
                		resultSet.getString("end_date"), 
                		resultSet.getString("Status_ID"),
                		resultSet.getInt("Priority_ID"), 
                		resultSet.getFloat("Total_Hours"), 
                		resultSet.getInt("Employee_ID"), 
                		resultSet.getFloat("Complete"));
                		
                	
             
     
            

                return task;
            }
        };

   
		return template.query(sql, rm);
	}



	@Override
	public void addReport(String name, int Project_ID, String Description, String start_date, String end_date, int Status_ID, int Priority_ID, float Total_Hours, int Employee_ID, float Complete) {
				 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		         jdbcTemplate.update("INSERT INTO `tasks` (`Task_Name`, `Project_ID`, `Description`, `start_date`, `end_date`, `Status_ID`, `Priority_ID`, `Total_Hours`, `Employee_ID`, `Complete`) "
		         		+ "VALUES (?,?,?,?,?,?,?,?,?,?)",
		         		name, Project_ID,Description,  start_date, end_date, Status_ID, Priority_ID, Total_Hours, Employee_ID , Complete   );
		       
		           System.out.println("Описание: " + Description);
			
	}


	@Override
	public void delete(String id) {
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 jdbcTemplate.update("DELETE FROM `tasks` WHERE id_task = "+id+" "  );
	       
				
		
	}

	//update task %
	@Override
	public void updateProgres(String id, float complete) {

		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         jdbcTemplate.update("UPDATE `tasks` SET `Complete` =  "+complete+" WHERE `tasks`.`id_task` = "+id+"" );
       
		
	}


	@Override
	public void updateRecordHistory(String taskID, String name, int Project_ID, String Description, String start_date, String end_date,
			int Status_ID, int Priority_ID, float Total_Hours, int Employee_ID, float Complete) {
		// TODO Auto-generated method stub
		
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         jdbcTemplate.update("UPDATE `tasks` SET `Task_Name` = ' "+name+" ', `Project_ID` = "+Project_ID+", `Description` = ' "+Description+" ', "
         		+ "`start_date` = ' "+start_date+" ', `end_date` = ' "+end_date+" ', `Status_ID` = "+Status_ID+", `Priority_ID` = "+Priority_ID+", `Total_Hours` = "+Total_Hours+", `Employee_ID` = "+Employee_ID+", "
         		+ "`Complete` = "+Complete+" WHERE `tasks`.`id_task` = "+taskID+"" );
       
         
	}






	//INSERT INTO `tasks_history` (`id_history`, `Task_ID`, `employee_id`, `Worked_Hours`, `Work_Done_Desctiption`, `Work_Done_Issues`, `start_date`, `end_date`, `Complete_Before`, `Complete_Now`) VALUES
	
	
	
	
	
	

}




