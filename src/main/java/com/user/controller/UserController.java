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

}
