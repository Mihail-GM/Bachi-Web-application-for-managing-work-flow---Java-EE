package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.service.SupportService;
import com.example.service.UserService;


@Controller
public class SupportController {
    @Autowired
	private UserService userService;
    @Autowired
	private SupportService supportService;
    
    //показване на изглед за добавяне на ново съобщение за помощ
    @RequestMapping(value = "/admin/support/new-ticket" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String addTicket(Model model){
    		model.addAttribute("employees", userService.findAll());
	
	
	
	return "admin/support/new-ticket";
	
    }
    
    // показване на изглед за отговор
    @RequestMapping(value = "/admin/support/reply" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String addAnswer(@RequestParam("idEmployee") int id,  
    		@RequestParam("ticketID") int idTicket, 
    		@RequestParam("description") String Description,
    		@RequestParam("pririthyID") String priorithy,
    		@RequestParam("pririthy") String priorithyString,
    		@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
    		Model model){
    		
    	 	model.addAttribute("employeeOwnerID", id);
    	 	model.addAttribute("ticketID", idTicket);
	    	model.addAttribute("description", Description);
	    	model.addAttribute("pririthyID", priorithy);
	    	model.addAttribute("pririthy", priorithyString);
	    	model.addAttribute("date", date);
	  
	    	
	    model.addAttribute("support", supportService.findAllMessagesByPerrantId(idTicket));
    	model.addAttribute("employees", userService.findById(id));
	
	return "admin/support/reply";
    }
    
    //показване на всички лични съобщения в таблица
    @RequestMapping(value = "/admin/support/view" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String showAllMineTickets(Model model){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addAttribute("support", supportService.findByPerrantId(user.getId()));
		
	return "admin/support/view";
	
    }
    
    
    
    //Добавяне на тикет заявка
    @PostMapping(value = "/admin/support/new-ticket" )
    public String addSupportTicker(@RequestParam("employeeSelected") int asiginEmpl, 
    		@RequestParam("description") String description,
    		@RequestParam("priority") int priorithy,
    		Model model) {
    	
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User user = userService.findUserByEmail(auth.getName());
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		
    		System.out.println(user.getId()+ "  " + asiginEmpl+ "  " + description+ "  " + dtf.format(now)+ "  " + priorithy);
    	supportService.addSupport(user.getId(), asiginEmpl, description, dtf.format(now), priorithy);
    	
    return "admin/support/new-ticket";
    }

    
  //Добавяне на отговор на тикет заявка
    @PostMapping(value = "/admin/support/reply" )
    public String addAnswerSupportTicker(@RequestParam("idEmployee") int asiginEmpl, 
    		@RequestParam("description") String description,
    		@RequestParam("priotityIDSend") int priorithy,
    		@RequestParam("ticketID") int idTicket, 
    		@RequestParam("active") int active, 
    		Model model) {
    	
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User user = userService.findUserByEmail(auth.getName());
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		
    		System.out.println(user.getId()+ "  " + asiginEmpl+ "  " + description+ "  " + dtf.format(now)+ "  " + priorithy);
    		supportService.replySupport(user.getId(), asiginEmpl, description, dtf.format(now), priorithy,idTicket, active);
    	
    return "admin/support/view";
    }
    

    
    //заявки от потребител тип служител
    //НАЧАЛО
    
    //показване на изглед за добавяне на ново съобщение за помощ
    @RequestMapping(value = "/employeer/support/new-ticket" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String addTicketEmployee(Model model){
    		model.addAttribute("employees", userService.findAll());
	
	
	
	return "employeer/support/new-ticket";
	
    }
    
    // показване на изглед за отговор
    @RequestMapping(value = "/employeer/support/reply" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String addEmployeeAnswer(@RequestParam("idEmployee") int id,  
    		@RequestParam("ticketID") int idTicket, 
    		@RequestParam("description") String Description,
    		@RequestParam("pririthyID") String priorithy,
    		@RequestParam("pririthy") String priorithyString,
    		@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
    		Model model){
    		
    	 	model.addAttribute("employeeOwnerID", id);
    	 	model.addAttribute("ticketID", idTicket);
	    	model.addAttribute("description", Description);
	    	model.addAttribute("pririthyID", priorithy);
	    	model.addAttribute("pririthy", priorithyString);
	    	model.addAttribute("date", date);
	  
	    	
	    model.addAttribute("support", supportService.findAllMessagesByPerrantId(idTicket));
    	model.addAttribute("employees", userService.findById(id));
	
	return "employeer/support/reply";
    }
    
    //показване на всички лични съобщения в таблица
    @RequestMapping(value = "/employeer/support/view" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String showEmployyeeAllMineTickets(Model model){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addAttribute("support", supportService.findByPerrantId(user.getId()));
		
	return "employeer/support/view";
	
    }
    
    
    
    //Добавяне на тикет заявка
    @PostMapping(value = "/employeer/support/new-ticket" )
    public String addSupportTickerEmployyee(@RequestParam("employeeSelected") int asiginEmpl, 
    		@RequestParam("description") String description,
    		@RequestParam("priority") int priorithy,
    		Model model) {
    	
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User user = userService.findUserByEmail(auth.getName());
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		
    		System.out.println(user.getId()+ "  " + asiginEmpl+ "  " + description+ "  " + dtf.format(now)+ "  " + priorithy);
    	supportService.addSupport(user.getId(), asiginEmpl, description, dtf.format(now), priorithy);
    	
    return "employeer/support/new-ticket";
    }

    
  //Добавяне на отговор на тикет заявка
    @PostMapping(value = "/employeer/support/reply" )
    public String addAnswerSupportTickerEmployyee(@RequestParam("idEmployee") int asiginEmpl, 
    		@RequestParam("description") String description,
    		@RequestParam("priotityIDSend") int priorithy,
    		@RequestParam("ticketID") int idTicket, 
    		@RequestParam("active") int active, 
    		Model model) {
    	
     		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		User user = userService.findUserByEmail(auth.getName());
    		
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		
    		System.out.println(user.getId()+ "  " + asiginEmpl+ "  " + description+ "  " + dtf.format(now)+ "  " + priorithy);
    		supportService.replySupport(user.getId(), asiginEmpl, description, dtf.format(now), priorithy,idTicket, active);
    	
    return "employeer/support/view";
    }
    
    

}
