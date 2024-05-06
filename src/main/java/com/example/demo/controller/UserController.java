package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new UserAccount());
		return "index";	
	}
	
	@PostMapping("/save-user")
	public String handleSubmitBtn(@ModelAttribute("user") UserAccount user,Model model) {
		System.out.println(user);
		//user.setActivateSe("Y");
		String msg = userService.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		//model.addAttribute("user", new UserAccount());
		return "redirect:/users";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<UserAccount> userList = userService.getAllUserAccount();
		model.addAttribute("users", userList);
		
		return "view-users";
		
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id,Model model) {
		UserAccount userAcc = userService.getUserAcc(id);
		model.addAttribute("user", userAcc);
		return "index";
		
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id,Model model) {
		
	 userService.deleteUserAcc(id);
	 model.addAttribute("msg", "user record deleted");
		
		return "forward:/users";
		
	}
	
	@GetMapping("/update")
	public String statusUpdated(@RequestParam("id") Integer id ,@RequestParam("status")String status,Model model) {
		
		userService.updateUserAccStatus(id, status);
		if("Y".equals(status)) {
			model.addAttribute( "msg", "User Account Activated");
		}else {
			model.addAttribute("msg", "User Account Deactivate");
		}
		return "forward:/users";
		
	}
	

}
