package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.TaskHistory;
import com.example.model.User;
import com.example.service.ProjectService;
import com.example.service.TaskHistoryService;
import com.example.service.UserService;


@Controller
public class EmployeeControler {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskHistoryService taskHistoryService;
	@Autowired
    private ProjectService projectService;

	//покзване на изглед за добавяне на служител
	@RequestMapping(value="/admin/employee", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ModelAndView adminEmployee(){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/admin/employee");
		return modelAndView;
	}
	
	// показване на всички служители
	@RequestMapping(value="/admin/employee/view", method = RequestMethod.GET)
	public String showEmployee(Model md){
		md.addAttribute("employees", userService.findAll());
		

		return "admin/employee/view";
	}
	
	// показване на изглед за редакция на служител
	 @RequestMapping(value = "/admin/employee/edit" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	    public String addProject(Model model){
	    		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
	    	
			
		 model.addAttribute("personalEmployees", userService.findByIdAllfields(user.getId()));
	    		model.addAttribute("employees", userService.findAll());
		
		
		
		return "admin/employee/edit";
		
	    }
	
	// TO DO show user profile with chart edit personal data...
	 // показване на личния профил на служител
	@RequestMapping(value="/admin/employee/profile", method = RequestMethod.GET)
	public String showEmployeeProfile(Model model){
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
    	
		System.out.println(user.getId());

		// начало на взимане на данните за графиката
		List<TaskHistory> historyData = taskHistoryService.findAllByUser(user.getId());
	    	
	 	ArrayList<Float> workedHours =  new ArrayList<Float>(); 
 	 	ArrayList<String> dates =  new ArrayList<String>(); 
 	 	
 	    	for(int i = 0; i < historyData.size() ; i++){
 	    		workedHours.add(historyData.get(i).getWorkHours());
 	    		dates.add(historyData.get(i).getStartDate());
 	    	}
 	    	
 	    	// край на взимане на данните за графиката
 	    	
 	    	//добавяне на атрибути към thymleaf
 	    	model.addAttribute("workHours", workedHours);
 	    	model.addAttribute("datesWork", dates);
		
		model.addAttribute("employees", userService.findById(user.getId()));
		model.addAttribute("tasksHistory", historyData);
		model.addAttribute("projects", projectService.findAllByUser(user.getId()));
    	
		return "admin/employee/profile";
	}
	
	
	//обновяване на данните за потребител
	
	@RequestMapping(value = "/admin/employee/edit", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String updateUser(@RequestParam("id") int id,
			@RequestParam("manager_id") int managerID,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
    		@RequestParam("date") String birthDay,
    		@RequestParam("gender") String gender,
    		 
    		@RequestParam("phone") String phone, 
    		@RequestParam("adress") String adress, 
    		@RequestParam("salary") float salary, 
    		@RequestParam("active") int active, 
    		 Model model) {
	
		
		System.out.println("Adress is:  " + adress);
		
		User user1 = new User(name, email , password, adress, phone, gender, birthDay , salary);
		
		System.out.println("1 aftter constructor: " + user1.getEmail());
		user1.setId(id);
		user1.setActive(1);
		user1.setManagerID(managerID);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		
		user1.setUpdateOn(dtf.format(now));
		user1.setActive(active);
			// запазваме новия служител в таблица
			userService.updateUser(user1);
			
			
	return "admin/employee/success";
	}
	
	
	// изпращане на заявка за добавяне на нов служител
	@RequestMapping(value = "/admin/employee", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String createNewUser(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
    		@RequestParam("date") String birthDay,
    		@RequestParam("gender") String gender,
    		@RequestParam("role") int role, 
    		@RequestParam("phone") String phone, 
    		@RequestParam("adress") String adress, 
    		@RequestParam("salary") float salary, 
    		 Model model) {
	
		User user1 = new User(name, email , password, adress, phone, gender, birthDay , salary);
		
		System.out.println("1 aftter constructor");
		user1.setActive(1);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		user1.setCreateOn(dtf.format(now));
		user1.setUpdateOn(dtf.format(now));
		user1.setActive(1);
			// запазваме новия служител в таблица
			userService.saveUser(user1);
			
			System.out.println("1 aftter save user");
			//добавяме роля ( админ или служител , имейла също е уникален затова го ползваме да вземем ID
			userService.editRole(userService.findUserByEmail(email).getId(), role);
					
	return "admin/employee";
	}
	
	// показване на ръководство за потребителя
		@RequestMapping(value="/admin/user-guide", method = RequestMethod.GET)
		public String showEmployeeGuide(Model md){	

		return "admin/user-guide";
		}
	
	 //заявки от потребител тип служител
	// НАЧАЛО
	
	
	// показване на изглед за редакция на служител
	 @RequestMapping(value = "/employeer/employee/edit" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	    public String addEditEmployee(Model model){
	    		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
	    	
			
		 model.addAttribute("personalEmployees", userService.findByIdAllfields(user.getId()));
	    		model.addAttribute("employees", userService.findAll());
		
		
		
		return "employeer/employee/edit";
	    }
	
	// TO DO show user profile with chart edit personal data...
	 // показване на личния профил на служител
	@RequestMapping(value="/employeer/employee/profile", method = RequestMethod.GET)
	public String showEmployeeProfileEmployee(Model model){
		
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
    	
		System.out.println(user.getId());
		
		model.addAttribute("employees", userService.findById(user.getId()));
		model.addAttribute("tasksHistory", taskHistoryService.findAllByUser(user.getId()));
		model.addAttribute("projects", projectService.findAllByUser(user.getId()));
    	
	return "employeer/employee/profile";
	}
	
	
	//обновяване на данните за потребител
	
	@RequestMapping(value = "/employeer/employee/edit", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String updateUserEmployee(@RequestParam("id") int id,
			@RequestParam("manager_id") int managerID,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
    		@RequestParam("date") String birthDay,
    		@RequestParam("gender") String gender,
    		@RequestParam("phone") String phone, 
    		@RequestParam("adress") String adress, 
    		@RequestParam("salary") float salary, 
    		@RequestParam("active") int active, 
    		 Model model) {
	
		
		System.out.println("Adress is:  " + adress);
		
		User user1 = new User(name, email , password, adress, phone, gender, birthDay , salary);
		
		System.out.println("1 aftter constructor: " + user1.getEmail());
		user1.setId(id);
		user1.setActive(1);
		user1.setManagerID(managerID);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		
		user1.setUpdateOn(dtf.format(now));
		user1.setActive(active);
			// запазваме новия служител в таблица
			userService.updateUser(user1);
			
			System.out.println("1 aftter save user");
			//добавяме роля ( админ или служител , имейла също е уникален затова го ползваме да вземем ID
		
				
			
			
	return "employeer/employee/success";
	}
	
	
	
	
	
}
