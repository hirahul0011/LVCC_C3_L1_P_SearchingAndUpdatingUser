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
					List<User> users=(List<User>)parametersDAO.getAllUsersDetails();
					if(userId.equalsIgnoreCase(user.get(0).getUserId()) && password.equals(user.get(0).getPassword())) {				
						map.addAttribute("userId", userId);
						map.addAttribute("users", users);
						page="administration";
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

}
