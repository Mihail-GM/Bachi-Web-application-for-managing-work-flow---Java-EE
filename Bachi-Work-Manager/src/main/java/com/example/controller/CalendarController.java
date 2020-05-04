package com.example.controller;

import java.util.Date;

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

import com.example.model.User;
import com.example.service.CalendarService;
import com.example.service.UserService;

@Controller
public class CalendarController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CalendarService calendarService;
	
	//показване на моите събития
	   @RequestMapping(value = "/admin/events/mine" , method = RequestMethod.GET)
	    public String viewCalendar(Model model){
	    		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
	    		model.addAttribute("calendar", calendarService.findAll(user.getId()));
		
		
	    		System.out.println("Getting calendar");
		return "admin/events/mine";
		
	    }
	   
	   //показване на изглед за добавяне на ново събитие
	   @RequestMapping(value = "/admin/events/add" , method = RequestMethod.GET)
	    public String showAddEvent(Model model){
		return "/admin/events/add";
	    }
	   
	   // обработка на заявка за създаване на ново събитие
	   @PostMapping(value = "/admin/events/add" )
	    public String addEvent(@RequestParam("name") String name, 
	    		@RequestParam("description") String description,
	    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
	    		@RequestParam("priority") int priority ){
	    	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			
			calendarService.addEvent(name, date, description,user.getId(), priority);
	         
	    	return "admin/events/add";
	    }
	   
	   //заявки от потребител тип служител
	   
	 //показване на моите събития
	   @RequestMapping(value = "/employeer/events/mine" , method = RequestMethod.GET)
	    public String viewCalendarEmlpoyeer(Model model){
	    		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
	    		model.addAttribute("calendar", calendarService.findAll(user.getId()));
		
		
	    		System.out.println("Getting calendar");
		return "employeer/events/mine";
		
	    }
	   
	   //показване на изглед за добавяне на ново събитие
	   @RequestMapping(value = "/employeer/events/add" , method = RequestMethod.GET)
	    public String showAddEventEmlpoyeer(Model model){
		return "/employeer/events/add";
	    }
	   
	   // обработка на заявка за създаване на ново събитие
	   @PostMapping(value = "/employeer/events/add" )
	    public String addEmlpoyeer(@RequestParam("name") String name, 
	    		@RequestParam("description") String description,
	    		@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
	    		@RequestParam("priority") int priority ){
	    	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			
			calendarService.addEvent(name, date, description,user.getId(), priority);
	         
	    	return "employeer/events/add";
	    }

}
