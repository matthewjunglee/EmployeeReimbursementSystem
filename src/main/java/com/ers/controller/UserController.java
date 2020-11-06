package com.ers.controller;

import javax.servlet.http.HttpServletRequest;

import com.ers.model.User;
import com.ers.model.UserRole;
import com.ers.service.UserService;

public class UserController {

	private UserService us;

	public UserController() {
		this(new UserService());
	}

	public UserController(UserService us) {
		this.us = us;
	}

	public String loginUser(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User u = us.login(username, password);
		
		// valid user login
		if (u.getId() != 0) {
			req.getSession().invalidate();
			req.getSession().setAttribute("user", u);

			if (u.getRoleId().getId() == 1) {
				return "html/managerPortal.html";
			} else {
				return "html/employeePortal.html";
			}
		} else {
			return "";
		}
	}

	public String registerUser(HttpServletRequest req) {
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setPassword(req.getParameter("password"));
		u.setFirstName(req.getParameter("firstName"));
		u.setLastName(req.getParameter("lastName"));
		u.setEmail(req.getParameter("email"));
		u.setRoleId(new UserRole(2));
		u.setId(us.create(u));
		
		// invalid user information, username already exists
		if (u.getId() == 0) {
			return "";
		}
		
		req.getSession().invalidate();
		req.getSession().setAttribute("user", u);

		return "html/employeePortal.html";
	}

//	public String updateUser(User user) {
//		return null;
//	}
//
//	public String deleteUser(int id) {
//		return null;
//	}

}
