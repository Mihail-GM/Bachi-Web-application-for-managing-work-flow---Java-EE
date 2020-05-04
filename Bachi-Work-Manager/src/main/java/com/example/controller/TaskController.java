package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Role;
import com.example.model.TaskHistory;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.service.ProjectService;
import com.example.service.TaskHistoryService;
import com.example.service.TaskService;

import com.example.service.UserService;

@Controller
public class TaskController {

	  @Autowired
	    private TaskService taskService;
	  @Autowired
	    private ProjectService projectService;
	  @Autowired
	    private TaskHistoryService taskHistoryService;
	  @Autowired
	    private UserService userService;

	    //показване на задачи
	    
		//репорт на дадена задача, запазване в история
	    //TO DO 
	    @RequestMapping(value = "/admin/task/add-task-history", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public String addTaskHistory(@RequestParam("id_task") String id, 
	    		@RequestParam("description") String Description,
	    		@RequestParam("issue_description") String descriptionIssue,
	    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
	    		@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
	    		@RequestParam("status") String employeeId,
	    		@RequestParam("totalHours") float totalHours,
	    		@RequestParam("progress") float progress,
	    		Model model) {

	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
	    	
	    	//save task hitory
	    	taskHistoryService.addTaskHistory(id, user.getId(), totalHours, Description, descriptionIssue, startDate, endDate, totalHours, progress);
	    	//update task
	    	taskService.updateProgres(id, progress);
	    	
	    return "admin/task/add-task-history";
	    }
	    //промяна на дададена задача
	    //TO DO  updateRecordHistory
	    
	    @RequestMapping(value = "/admin/task/update-task-success", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public String updateTask(@RequestParam("id_task") String id, 
	    		@RequestParam("name_task") String nameTask,
	    		@RequestParam("ProjectSelected_task") int projectId,
	    		@RequestParam("description_task") String decription,
	    		@RequestParam("priority_task") int priority,
	    		@RequestParam("statusID") int status,
	    		@RequestParam("startDate_task") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
	    		@RequestParam("endDate_task") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
	    		@RequestParam("employeeSelected_task") int employeeId,
	    		@RequestParam("totalHours_task") float totalHours,
	    		@RequestParam("task_progres_task") float progress,
	    		Model model) {

	    	taskService.updateRecordHistory(id, nameTask, projectId, decription, startDate, endDate, status, priority, totalHours, employeeId, progress);

	    	System.out.println("успешно обновяване: " + decription);
	    	
	    return "admin/task/update-task-success";
	    }

	    //показване на всички задачи
	    @RequestMapping(value="admin/task/view-all", method = RequestMethod.GET)
	    public String viewAllTasks(Model model) {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
		
			model.addAttribute("tasks", taskService.findAll());
	    return "admin/task/view-all";
	    }
	    
	
	    
	    //показване още за една задача
	    @RequestMapping(value = "/admin/task/description",  params="action=view", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public String projectDescription(@RequestParam(value="action", required=true) String action,
	    		@RequestParam("id_task") String id,
	    		@RequestParam("name") String name,
	    		@RequestParam("project_ID") String project_ID,
	    		@RequestParam("status_id") String status_id,
	    		@RequestParam("pririthyID") String pririthyID,
	    		@RequestParam("complete") String complete,
	    		@RequestParam("description") String description,
	    		@RequestParam("start_date") String startDate,
	    		@RequestParam("end_date") String endDate,
	    		@RequestParam("empID") String employeeID,
	    		@RequestParam("totalHours") String totalHoursV,
	    		 Model model) {
	    
	    
	        	 model.addAttribute("id_task", id);
	 	    	model.addAttribute("name", name);
	 	    	model.addAttribute("project_ID", project_ID);
	 	    	model.addAttribute("status_id", status_id);
	 	    	model.addAttribute("pririthyID", pririthyID);
	 	    	model.addAttribute("complete", complete);
	 	    	model.addAttribute("description", description);
	 	    	model.addAttribute("start_date", startDate);
	 	    	model.addAttribute("end_date", endDate);
	 	    	model.addAttribute("empID", employeeID);
	 	    	model.addAttribute("totalHours", totalHoursV);
	 	    	
	 	    	ArrayList<Integer> paran =  new ArrayList<Integer>(); 
	 	    	paran.add(2);
	 	    	paran.add(11);
	 	    	paran.add(5);
	 	    	
	 	    	model.addAttribute("message", paran);
	 	    
	 	    	// начало на взимане на данните за графиката
	 	    	List<TaskHistory> historyData = taskHistoryService.findAllDataForGraphic(Integer.parseInt(id));
	 	    	
	 	    	
	 	    	
	 	   	ArrayList<Float> workedHours =  new ArrayList<Float>(); 
	 	 	ArrayList<Float> comleteNow =  new ArrayList<Float>(); 
	 	 	ArrayList<String> dates =  new ArrayList<String>(); 
	 	 	
	 	    	for(int i = 0; i < historyData.size() ; i++){
	 	    		workedHours.add(historyData.get(i).getWorkHours());
	 	    		comleteNow.add(historyData.get(i).getCompleteNow());
	 	    		dates.add(historyData.get(i).getStartDate());
	 	    	}
	 	    	
	 	    	// край на взимане на данните за графиката
	 	    	
	 	    	//добавяне на атрибути към thymleaf
	 	    	model.addAttribute("workHours", workedHours);
	 	    	model.addAttribute("completeNow", comleteNow);
	 	    	model.addAttribute("datesWork", dates);
	 	    	
	 	    	model.addAttribute("tasksHistory", taskHistoryService.findAll(id));
	 	    	model.addAttribute("projects", projectService.findAll());
		    	model.addAttribute("employees", userService.findAll());
	 	    	
	 	  System.out.println("Дата  : " + employeeID);
	 	  
	    return "admin/task/description";
	    }
	
	    
	  //обновяване на описанието на задачата
	    @RequestMapping(value = "/admin/task/description", params="action=update", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public String updateTask(@RequestParam(value="action", required=true) String action,
	    		@RequestParam("id_task") String id,
	    		@RequestParam("name") String name,
	    		@RequestParam("project_ID") String project_ID,
	    		@RequestParam("status_id") String status_id,
	    		@RequestParam("pririthyID") String pririthyID,
	    		@RequestParam("complete") String complete,
	    		@RequestParam("description") String description,
	    		 Model model) {
	    
	    	model.addAttribute("projects", projectService.findAll());
	    	model.addAttribute("employees", userService.findAll());

	    	//taskService.delete(id);
	    	
	    return "redirect:/admin/task/update";	
	    }
	    
	    //изтриване на задача
	    @RequestMapping(value = "/admin/task/description", params="action=delete", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	    public String deleteTask(@RequestParam("id_task") String id, Model model) {
		       taskService.delete(id);

	    return "redirect:view-all";
	    }
	    
	    // добавяне на задача
	    
	    //Показване на изглед взимаме информацията за проекти и служители
	    @RequestMapping(value="/admin/task/add", method = RequestMethod.GET)
	    public String viewNewTask (Model model) {
	    	//ProjectService post = ProjectService.findByName();
	    	model.addAttribute("projects", projectService.findAll());
	    	model.addAttribute("employees", userService.findAll());

	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			System.out.println("Current user id: " + user.getId());
			
	    return "admin/task/add";
	    }
	    
	    //Добавяме нова задача, правим заявка с попълнените данни
	    @PostMapping(value = "/admin/task/add" )
	    public String addProjectFromWeb(@RequestParam("name") String name, @RequestParam("ProjectSelected") String Project_ID,
	    		@RequestParam("description") String Description,
	    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
	    		@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
	    		@RequestParam("employeeSelected") int employeeId,
	    		@RequestParam("totalHours") float totalHours,
	    		@RequestParam("priority") int priorityID,Model model) {
	    	
	    	 System.out.println("Описание: " + Description);
	    		taskService.addReport(name, Integer.parseInt(Project_ID),  Description, startDate, endDate, 2 ,  priorityID,  totalHours,  employeeId, 1.0f );
	           // System.out.println("name = " + name + ",rescued = " + rescued + ", vaccinated = " + vaccinated);
	    return "/admin/task/add";
	    }
	    
	    
	    
	    //заявки от потребител тип служител
	    // НАЧАЛО
	    
	    //показване на задачи
	    
	  		//репорт на дадена задача, запазване в история
	  	    //TO DO 
	  	    @RequestMapping(value = "/employeer/task/add-task-history", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	  	    public String addTaskHistoryEmployee(@RequestParam("id_task") String id, 
	  	    		@RequestParam("description") String Description,
	  	    		@RequestParam("issue_description") String descriptionIssue,
	  	    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
	  	    		@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate,
	  	    		@RequestParam("status") String employeeId,
	  	    		@RequestParam("totalHours") float totalHours,
	  	    		@RequestParam("progress") float progress,
	  	    		Model model) {

	  	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  			User user = userService.findUserByEmail(auth.getName());
	  	    	
	  	    	//save task hitory
	  	    	taskHistoryService.addTaskHistory(id, user.getId(), totalHours, Description, descriptionIssue, startDate, endDate, totalHours, progress);
	  	    	//update task
	  	    	taskService.updateProgres(id, progress);
	  	    	
	  	    return "employeer/task/add-task-history";
	  	    }
	  	    
	  	    // показване на задачите САМО на потребителя
	  	    @RequestMapping(value="/employeer/task/view-all", method = RequestMethod.GET)
	  	    public String viewAllTasksEmployee(Model model) {
	  	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  			User user = userService.findUserByEmail(auth.getName());
	  		
	  			model.addAttribute("tasks", taskService.findAllPerUser(user.getId()));
	  			
	  	    return "employeer/task/view-all";
	  	    }
	  	    
	  	    //показване още за една задача
	  	    @RequestMapping(value = "/employeer/task/description",  params="action=view", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	  	    public String projectDescriptionEmployee(@RequestParam(value="action", required=true) String action,
	  	    		@RequestParam("id_task") String id,
	  	    		@RequestParam("name") String name,
	  	    		@RequestParam("project_ID") String project_ID,
	  	    		@RequestParam("status_id") String status_id,
	  	    		@RequestParam("pririthyID") String pririthyID,
	  	    		@RequestParam("complete") String complete,
	  	    		@RequestParam("description") String description,
	  	    		@RequestParam("start_date") String startDate,
	  	    		@RequestParam("end_date") String endDate,
	  	    		@RequestParam("empID") String employeeID,
	  	    		@RequestParam("totalHours") String totalHoursV,
	  	    		 Model model) {
	  	    
	  	    
	  	        	 model.addAttribute("id_task", id);
	  	 	    	model.addAttribute("name", name);
	  	 	    	model.addAttribute("project_ID", project_ID);
	  	 	    	model.addAttribute("status_id", status_id);
	  	 	    	model.addAttribute("pririthyID", pririthyID);
	  	 	    	model.addAttribute("complete", complete);
	  	 	    	model.addAttribute("description", description);
	  	 	    	model.addAttribute("start_date", startDate);
	  	 	    	model.addAttribute("end_date", endDate);
	  	 	    	model.addAttribute("empID", employeeID);
	  	 	    	model.addAttribute("totalHours", totalHoursV);
	  	 	    	
	  	 	    	
	  	 	    	model.addAttribute("tasksHistory", taskHistoryService.findAll(id));
	  	 	    	
	  	 	    	model.addAttribute("projects", projectService.findAll());
	  		    	model.addAttribute("employees", userService.findAll());
	  	 	    	
	  	 	  System.out.println("Дата  : " + employeeID);
	  	 	  
	  	    return "employeer/task/description";
	  	    }
	  	
	  	    
	  	  //обновяване на описанието на задачата
	  	    // Тук служителя репортва по дадена задача
	  	    @RequestMapping(value = "/employeer/task/description", params="action=update", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	  	    public String updateTaskEmployee(@RequestParam(value="action", required=true) String action,
	  	    		@RequestParam("id_task") String id,
	  	    		@RequestParam("name") String name,
	  	    		@RequestParam("project_ID") String project_ID,
	  	    		@RequestParam("status_id") String status_id,
	  	    		@RequestParam("pririthyID") String pririthyID,
	  	    		@RequestParam("complete") String complete,
	  	    		@RequestParam("description") String description,
	  	    		 Model model) {
	  	    
	  	    	model.addAttribute("projects", projectService.findAll());
	  	    	model.addAttribute("employees", userService.findAll());

	  	    	//taskService.delete(id);
	  	    	
	  	    return "redirect:/employeer/task/update";	
	  	    }
	  	    

	  	    
 
	    
}
