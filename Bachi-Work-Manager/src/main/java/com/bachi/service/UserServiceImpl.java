package com.bachi.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bachi.model.Role;
import com.bachi.model.User;
import com.bachi.repository.RoleRepository;
import com.bachi.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JdbcTemplate template;
    
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        /*
    
        System.out.println( user.getGender()+ "  " +user.getBirthday()+ "  " +user.getAdress());
    	

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO `employee` (`id_Employee`, `manager_id`, `Phone`, `position_id`, `sallary`,"
        		+ " `Gender`, `Birthday`, `Adress`,"
        		+ "  `created_on`, `updated_on`, `name`, `email`, `password`, `active`) VALUES "
        		+ "( ?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
        		user.getId(), user.getManagerID(), user.getPhoneNumber(), user.getPositionID(), user.getSallary(), user.getGender(), user.getBirthday(), user.getAdress(), 
        		user.getCreateOn(), user.getUpdateOn(), user.getName(),
        		user.getEmail(),user.getPassword(),  user.getActive() );
      
        */
       
		userRepository.save(user);
		 System.out.println("save  succsess");
	}
	
	
	@Override
	public void updateUser(User user) {
	
    
        System.out.println(user.getId() + user.getGender()+ "  " +user.getBirthday()+ "  " +user.getAdress());
    	
        //UPDATE `employee` SET `manager_id` = '5', `Phone` = '0896707808', `sallary` = '2500', `Gender` = 'М', `Birthday` = '1995-06-27', `Adress` = 'Варна промяна', 
        //`updated_on` = '2018-06-09 00:00:00' WHERE `employee`.`id_Employee` = 66;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE `employee` SET `manager_id` = "+user.getManagerID()+", "
        		+ "`Phone` = '"+user.getPhoneNumber()+"', `sallary` = '"+user.getSallary()+"', "
        		+ "`Gender` = '"+user.getGender()+"', `Birthday` = '"+user.getBirthday()+"', "
        		+ "`Adress` = '"+user.getAdress()+"', `updated_on` = '"+user.getUpdateOn()+"' "
        				+ "WHERE `employee`.`id_Employee` = "+user.getId()+" ");
      
        
        System.out.println("save  succsess");
		//userRepository.save(user);
	}
	
	
	@Override
	public void editRole(int idEmployee, int idRole) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO `user_role` (`user_id`, `role_id`) VALUES "
        		+ "(?,?)",
        		idEmployee,idRole );
      
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT p.`id_Employee`, p.`name`, p.`manager_id`, p.`Adress`,  p.`email`, c1.`position_name`  , p.`Phone`, p.`Gender`, p.`created_on` , p.`sallary` FROM `employee` p LEFT JOIN `positions` c1 ON p.`position_id` = c1.`ID` ";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getInt("id_Employee"),
                		resultSet.getString("name"),
                		resultSet.getInt("manager_id"),
                		resultSet.getString("Adress"), 
                		resultSet.getString("Phone"),
                		resultSet.getString("Gender"),
                		resultSet.getFloat("sallary"));
                		
                String phoneNumber = resultSet.getString("Phone");
                		
                	
                String email = resultSet.getString("email");
                if (email != null) {
                    user.setEmail(email);
                }

                return user;
            }
        };

        return template.query(sql, rm);
	}
	
	@Override
	public List<User> findById(int id) {
		String sql = "SELECT * FROM `employee` WHERE `id_Employee` = "+id+" ";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getInt("id_Employee"),
                		resultSet.getString("name"),
                		
                		resultSet.getString("Adress"), 
                		resultSet.getString("Phone"),
                		resultSet.getString("Gender"),
                		resultSet.getFloat("sallary"));
                		
              
                		
                	System.out.println(" Curent employee name: " + user.getName() );
                String email = resultSet.getString("email");
                if (email != null) {
                    user.setEmail(email);
                }

                return user;
            }
        };

        return template.query(sql, rm);
	}
	
	
	@Override
	public List<User> findByIdAllfields(int id) {
		String sql = "SELECT * FROM `employee` WHERE `id_Employee` = "+id+" ";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getInt("id_Employee"),
                		resultSet.getString("name"),
                		resultSet.getString("email"),
                		resultSet.getString("Adress"), 
                		resultSet.getString("Phone"),
                		resultSet.getString("Gender"),
                		resultSet.getString("Birthday"),
                		resultSet.getFloat("sallary"),
                	
                		resultSet.getInt("manager_id"),
                		resultSet.getInt("position_id"),
                		resultSet.getInt("active")
                		);
                		
              
                		
                	System.out.println(" Curent employee name: " + user.getName() );
                String email = resultSet.getString("email");
                if (email != null) {
                    user.setEmail(email);
                }

                return user;
            }
        };

        return template.query(sql, rm);
	}
	
	

}
