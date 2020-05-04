package com.bachi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.bachi.model.Project;
import com.bachi.model.User;
import com.bachi.repository.ProjectRepository;
import com.bachi.service.ProjectService;
import com.bachi.service.UserService;

@Controller
public class ProjectsController {


    @Autowired
    private ProjectService projectService;
    @Autowired
	private UserService userService;
    
    //Добавяне на проект
    @PostMapping(value = "/admin/project" )
    public String addProject(@RequestParam("name") String name, @RequestParam("id_project") int user,
    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    		@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
    		@RequestParam("status") int status, 
  
    		@RequestParam("totalHours") int totalHours, Model model) {
    	
    		projectService.addProject(name, user, startDate, endDate, status, totalHours);
         
    		return "admin/project";
    }
    
    //показване на изглед за добавяне на проекти
    @RequestMapping(value = "/admin/project" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String addProject(Model model){
    		
    		model.addAttribute("employees", userService.findAll());

	return "admin/project";
	
    }
    

    //показване на проекти
    @RequestMapping(value="/admin/projects-view", method = RequestMethod.GET)
    public String listProjects(Model model) {
    	//ProjectService post = ProjectService.findByName();
    	model.addAttribute("projects", projectService.findAll());
    	

    	return "admin/projects-view";
    	
    	
    }
	
    //заявки от потребител тип служител
    //НАЧАЛО
    
  //показване на проекти
    @RequestMapping(value="/employeer/projects-view", method = RequestMethod.GET)
    public String listProjectsPerEmployee(Model model) {
    	//ProjectService post = ProjectService.findByName();
    	model.addAttribute("projects", projectService.findAll());
    	

    	return "employeer/projects-view";
    	
    	
    }

}
