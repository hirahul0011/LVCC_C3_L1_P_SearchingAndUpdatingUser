package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.dao.ParametersDAO;
import com.user.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private ParametersDAO parametersDAO;
	
	@RequestMapping(value="validationCheck",method=RequestMethod.POST)
	public String isValidUser(@RequestParam("username")String userId,
			@RequestParam("password")String password, ModelMap map) {
		String page="login";
		if(userId!="") {			
			try {				
					List<User> user=(List<User>)parametersDAO.getTheUserDetails(userId);					
					if(userId.equalsIgnoreCase(user.get(0).getUserId()) && password.equals(user.get(0).getPassword())) {				
						map.addAttribute("user", user.get(0));
						map.addAttribute("userId", userId);
						map.addAttribute("userFirstName", user.get(0).getFirstName());
						page="details";
					}else {
						map.addAttribute("errorMessage", "Invalid login credentials");
					}								
			}catch(IndexOutOfBoundsException e) {
				map.addAttribute("errorMessage", "Please enter correct UserName");
				page="login";
			}									
		}else {
			map.addAttribute("errorMessage", "Please enter the UserName");
		}		
		return page;		
	}
	
	@RequestMapping(value="changeDetails",method=RequestMethod.GET)	
	public String changeDetails(@RequestParam("userId")String userId,
			@RequestParam("password")String password,
			@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,
			@RequestParam("age")Integer age,
			@RequestParam("gender")String gender,
			ModelMap map){
		
		String page="changeDetails";		
		
		map.addAttribute("userId", userId);
		map.addAttribute("password", password);
		map.addAttribute("firstName", firstName);
		map.addAttribute("lastName", lastName);
		map.addAttribute("age", age);
		map.addAttribute("gender", gender);				
		
		return page;
		
	}
	
	@RequestMapping(value="changeDetailsValidation",method=RequestMethod.POST)	
	public String changeDetailsValidation(@RequestParam("userId")String userId,
			@RequestParam("password")String password,
			@RequestParam("firstName")String firstName,
			@RequestParam("lastName")String lastName,
			@RequestParam("age")Integer age,
			@RequestParam("gender")String gender,
			ModelMap map){
		
		String page="changeDetails";		
		
		map.addAttribute("userId", userId);
		map.addAttribute("password", password);
		map.addAttribute("firstName", firstName);
		map.addAttribute("lastName", lastName);
		map.addAttribute("age", age);
		map.addAttribute("gender", gender);
		
		String errorMessages="";
		
		if(password!="") {
			if(password.length()<8 || password.length()>15) {
				errorMessages=errorMessages+"<br>Please enter password between 8 to 15 character length!";							
			}			
		}else {
			errorMessages=errorMessages+"<br>Please enter Password to continue!";			
		}
		
		if(firstName=="") {			
			errorMessages=errorMessages+"<br>Please enter First Name to continue!";			
		}
		
		if(lastName=="") {			
			errorMessages=errorMessages+"<br>Please enter Last Name to continue!";			
		}
		
		if(age!=null) {
			if(age<=0 || age>=150) {			
				errorMessages=errorMessages+"<br>Please enter some valid age to continue!";			
			}			
		}else {
			errorMessages=errorMessages+"<br>Please enter the age to continue!";
		}
				
		
		if(errorMessages=="") {				
			parametersDAO.changeUserDetails(userId, password, firstName, lastName, age, gender);
			page="changeDetailsConfirmation";					
		}else {
			map.addAttribute("errorMessages", errorMessages);			
		}
		
		return page;
		
	}
	
	@RequestMapping(value="redirectII",method=RequestMethod.GET)	
	public String redirectII(@RequestParam(required=false, name="userId")String userId,			
			ModelMap map){
		
		String page="details";		
		List<User> user=(List<User>)parametersDAO.getTheUserDetails(userId);		
		map.addAttribute("user", user.get(0));	
		map.addAttribute("userFirstName", user.get(0).getFirstName());				
		
		return page;		
	}
	
	@RequestMapping(value="redirectIII",method=RequestMethod.GET)	
	public String redirectIII(){
		
		String page="login";				
		return page;		
	}

}
