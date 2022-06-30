package com.jakeer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jakeer.modal.Customer;
import com.jakeer.services.CustService;
import com.jakeer.services.RecService;
import com.jakeer.services.RoomService;
@ComponentScan({"com.jakeer.services","com.jakeer.repository"})
@Controller
public class CustomerController {

	@Autowired
	CustService userService;
	@Autowired
	RecService recService;
	@Autowired
	RoomService roomService;

	@RequestMapping("/welcomess")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@RequestMapping("/registers-cu")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_ADMINREG");
		return "custreg";
	}

	@PostMapping("/save-userss")
	public String registerCustomer(@ModelAttribute Customer user, BindingResult bindingResult, HttpServletRequest request) {
		userService.saveMyCustomer(user);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@GetMapping("/show-usersss")
	public String showAllCustomers(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllCustomers());
		request.setAttribute("mode", "ALL_USERS");
		return "custpage";
	}
	@GetMapping("/show-roomss")
	public String showAllRooms(HttpServletRequest request) {
		request.setAttribute("users", roomService.showAllRooms());
		request.setAttribute("mode", "ALL_ROOMS");
		return "custpage";
	}

	@RequestMapping("/delete-userss")
	public String deleteCustomer(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyCustomer(id);
		request.setAttribute("users", userService.showAllCustomers());
		request.setAttribute("mode", "ALL_USERS");
		return "recppage";
	}
	
	@RequestMapping("/edit-userss")
	public String editCustomer(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("user", userService.editCustomer(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "recppage";
	}
	
	@RequestMapping("/login-cust")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN-CUST");
		return "customerlogin";
	}
	
	@RequestMapping ("/login-custs")
	public String loginCustomer(@ModelAttribute Customer user, HttpServletRequest request) {
		if(userService.findByReceptionistnameAndPassword(user.getUsername(), user.getPassword())!=null) {
			return "custpage";
		}
		else {
			request.setAttribute("error", "Invalid Customername or Password");
			request.setAttribute("mode", "MODE_LOGIN-CUST");
			return "customerlogin";
			
		}
	}
	@RequestMapping("/recploginss")
	public String recplogin(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_Recp_LOGIN");
		return "recplogin";
	}
}
