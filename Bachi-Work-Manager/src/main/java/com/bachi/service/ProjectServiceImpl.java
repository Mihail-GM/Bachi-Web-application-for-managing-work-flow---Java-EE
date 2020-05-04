package com.bachi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bachi.model.Project;
import com.bachi.repository.ProjectRepository;

import javax.sql.DataSource;
 

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ProjectRepository prRepository;
    @Autowired
    private JdbcTemplate template;
    
    
    
    
    //добавяне на проект
    @Override
	public void addProject(String name, int Project, Date startDate, Date endDate, int status,int totalHours) {

		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         jdbcTemplate.update("INSERT INTO projects(Project_Name, Project_Manager_ID, StartDate, End_Date, Status_ID, TotalHours)VALUES(?,?,?,?,?,?)",name, Project,startDate,  endDate, status, totalHours );
         
	}
    
    

    
  

	@Override
	public List<Project> findAll() {
		String sql = "SELECT p.`id_project`, p.`Project_Name`, c1.`name` , p.`StartDate`, p.`End_Date`, c2.`Status_desctription`, p.`TotalHours` FROM `projects` p LEFT JOIN `employee` c1 ON p.`id_project` = c1.`id_Employee` LEFT JOIN `status` c2 ON p.`Status_ID` = c2.`id_status`";
        RowMapper<Project> rm = new RowMapper<Project>() {
            @Override
            public Project mapRow(ResultSet resultSet, int i) throws SQLException {
                Project project = new Project(resultSet.getInt("id_project"),
                		resultSet.getString("Project_Name"), 
                		resultSet.getString("name"), 
                		resultSet.getString("StartDate"), 
                		resultSet.getString("End_Date"), 
                		resultSet.getString("Status_desctription"), 
                		resultSet.getFloat("TotalHours"));
                		
                	
             
             
            System.out.println("Status " + project.getStatus());

                return project;
            }
        };

   
		return template.query(sql, rm);
	}


//намиране на всички проекти за потребител
	@Override
	public List<Project> findAllByUser(int id) {
		String sql = "SELECT p.`id_project`, p.`Project_Name`, c1.`name` , p.`StartDate`, p.`End_Date`, c2.`Status_desctription`, p.`TotalHours` FROM `projects` p"
				+ " LEFT JOIN `employee` c1 ON p.`id_project` = c1.`id_Employee` "
				+ "LEFT JOIN `status` c2 ON p.`Status_ID` = c2.`id_status` WHERE c1.`id_Employee` = "+id+"";
        RowMapper<Project> rm = new RowMapper<Project>() {
            @Override
            public Project mapRow(ResultSet resultSet, int i) throws SQLException {
                Project project = new Project(resultSet.getInt("id_project"),
                		resultSet.getString("Project_Name"), 
                		resultSet.getString("name"), 
                		resultSet.getString("StartDate"), 
                		resultSet.getString("End_Date"), 
                		resultSet.getString("Status_desctription"), 
                		resultSet.getFloat("TotalHours"));
                		
                	
             
             
            

                return project;
            }
        };

   System.out.println("Find project for user : " + id);
		return template.query(sql, rm);
	}
	  
	
}
