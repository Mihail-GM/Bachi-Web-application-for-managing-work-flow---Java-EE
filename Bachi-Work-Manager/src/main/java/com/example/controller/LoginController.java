package com.example.controller;

import java.util.Locale;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.ProjectService;
import com.example.service.TaskService;
import com.example.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	 @Autowired
	    private TaskService taskService;
	 @Autowired
	    private ProjectService projectService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

	
	@RequestMapping(value="/registration", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		System.out.println(user.getEmail());
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(user.getEmail());
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("employee", "error.user",
							"Вече има регистриран потребител с този имейл");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Служителя беше регистриран успешно");
			modelAndView.addObject("employee", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	//пренасочване на входа в зависимост от вида на потребителя
	@RequestMapping(value="/index", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String index(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		String  role = user.getRoles().iterator().next().getRole();
		
		System.out.println(role);
		if(Objects.equals(role, "ADMIN")){
			return "redirect:/admin/index";
		} else {
			return "redirect:/employeer/index-emp";
		}
		
	}
	
	//ако е админ
	@RequestMapping(value="/admin/index", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	public String indexAdmin(Model model){
		
			model.addAttribute("projects", projectService.findAll());
			return "admin/index";
		
	}
	//ако е служител
	@RequestMapping(value="/employeer/index-emp", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	public String indexEMP(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

			model.addAttribute("tasks", taskService.findAllPerUser(user.getId()));
			
			return "employeer/index-emp";

	}
	
	@RequestMapping(value="/admin/index-reports", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	public String indexReports(Model model){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//modelAndView.addObject("userName", "Welcome " + user.getName() + " " + " (" + user.getEmail() + ")");
		//modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		
		
		model.addAttribute("tasks", taskService.findAll());
		
		return "admin/index-reports";
	}
	
	

	
	@RequestMapping(value="/test1", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String handleRequest (Locale locale) {
        return String.format("Request received.  тест бг Language: %s, Country: %s %n ",
                             locale.getLanguage(), locale.getDisplayCountry());
    }


	
}
