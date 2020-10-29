package com.ers.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ers.model.User;
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
		if (u.getUsername() != null && u.getUsername().equals(username)) {
			req.getSession().invalidate();
			req.getSession().setAttribute("user", u);

			if (u.getRoleId().getId() == 1) {
				return "html/managerPortal.html";
			} else {
				return "html/employeePortal.html";
			}
		}
		return "html/login.html";
	}

	public String registerUser(HttpServletRequest req) {
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setPassword(req.getParameter("password"));
		u.setFirstName(req.getParameter("firstName"));
		u.setLastName(req.getParameter("lastName"));
		u.setEmail(req.getParameter("email"));
//		u.setRoleId(new UserRole(2));
		u.setId(us.create(u));
		
		if (u.getId() == 0) {
			return "html/login.html";
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