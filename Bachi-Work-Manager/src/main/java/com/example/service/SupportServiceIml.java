package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.model.Support;
import com.example.model.User;

@Service("supportService")
public class SupportServiceIml implements SupportService {
	
	@Autowired
	private DataSource dataSource;
    @Autowired
	JdbcTemplate template;
    
    
	@Override
	public void addSupportAnswer(int employeeID, int asignEmpployeID, String Description, int perrantid, String date, int priority) {
		// TODO Auto-generated method stub
		
		//sql INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `perrant_ticket_id`, `date`, `Priority_ID`) VALUES ( '1', '2', 'Опиасние на проблема', NULL, '2018-05-31', '1'););
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `perrant_ticket_id`, `date`, `Priority_ID`) "
        		+ "VALUES ( ?,?,?,?,?,?)",
        		employeeID,asignEmpployeID, Description, perrantid, date , priority);
		
	}
	
	//create suppor
	@Override
	public void addSupport(int employeeID, int asignEmpployeID, String Description, String date, int priority) {
		// TODO Auto-generated method stub
		
		//sql INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `perrant_ticket_id`, `date`, `Priority_ID`) VALUES ( '1', '2', 'Опиасние на проблема', NULL, '2018-05-31', '1'););
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `date`, `Priority_ID`, `active`) "
        		+ "VALUES ( ?,?,?,?,?,?)",
        		employeeID,asignEmpployeID, Description, date , priority,1);
		
	}
	

	// reply to suppor обновяваме и  съобщението родител
	@Override
	public void replySupport(int employeeID, int asignEmpployeID, String Description, String date, int priority, int perrantID, int active) {
		// TODO Auto-generated method stub
		
		//sql INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `perrant_ticket_id`, `date`, `Priority_ID`) VALUES ( '1', '2', 'Опиасние на проблема', NULL, '2018-05-31', '1'););
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO `support` ( `Employee_ID`, `Assign_TO_ID`, `Description`, `perrant_ticket_id`,`date`, `Priority_ID`, `active`) "
        		+ "VALUES ( ?,?,?,?,?,?,?)",
        		employeeID,asignEmpployeID, Description,perrantID, date , priority,active);
        
        jdbcTemplate.update("UPDATE `support` SET `active` = '"+active+"' WHERE `support`.`id_ticket` = "+perrantID+"");
		
	}
	
	@Override
	public List<Support> findByPerrantId(int id) {
		String sql = "SELECT s.* , pr.`Priority_Descruption` FROM `support` s LEFT JOIN `priority` pr ON s.`Priority_ID` = pr.`id_Priority` WHERE `Assign_TO_ID` ="+id+"  AND `perrant_ticket_id` IS NULL";
        RowMapper<Support> rm = new RowMapper<Support>() {
            @Override
            public Support mapRow(ResultSet resultSet, int i) throws SQLException {
                Support user = new Support(resultSet.getInt("id_ticket"),
                		resultSet.getString("Employee_ID"),
                		resultSet.getString("Assign_TO_ID"), 
                		resultSet.getString("Description"),
                		resultSet.getInt("perrant_ticket_id"),
                		resultSet.getString("date"),
                		resultSet.getInt("Priority_ID"),
                		resultSet.getString("Priority_Descruption"),
                		resultSet.getInt("active")
                		);
                		
               

                return user;
            }
        };

        return template.query(sql, rm);
	}
	
	@Override
	public List<Support> findAllMessagesByPerrantId(int id) {
		String sql = "SELECT s.* , pr.`Priority_Descruption` FROM `support` s LEFT JOIN `priority` pr ON s.`Priority_ID` = pr.`id_Priority` WHERE"
				+ " `id_ticket` = "+id+" OR   `perrant_ticket_id` = "+id+"";
        RowMapper<Support> rm = new RowMapper<Support>() {
            @Override
            public Support mapRow(ResultSet resultSet, int i) throws SQLException {
                Support user = new Support(resultSet.getInt("id_ticket"),
                		resultSet.getString("Employee_ID"),
                		resultSet.getString("Assign_TO_ID"), 
                		resultSet.getString("Description"),
                		resultSet.getInt("perrant_ticket_id"),
                		resultSet.getString("date"),
                		resultSet.getInt("Priority_ID"),
                		resultSet.getString("Priority_Descruption"),
                		resultSet.getInt("active")
                		);
                		
               

                return user;
            }
        };

        return template.query(sql, rm);
	}

	
	

}
