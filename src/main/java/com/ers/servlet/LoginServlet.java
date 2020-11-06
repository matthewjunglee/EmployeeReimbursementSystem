package com.ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.model.User;

@WebServlet(name = "login", urlPatterns = {"*.page" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		switch (req.getRequestURI()) {
		case "/ERS-0.0.1-SNAPSHOT/login.page":
			req.getSession().invalidate();
			req.getRequestDispatcher("html/login.html").forward(req, resp);
			break;
		case "/ERS-0.0.1-SNAPSHOT/employee.page":
			// user must be logged in as employee
			if (req.getSession(false) != null &&
				((User) req.getSession().getAttribute("user")).getRoleId().getId() == 2) {
				req.getRequestDispatcher("html/employeePortal.html").forward(req, resp);
			} else {
				resp.sendRedirect("login.page");
			}
			break;
		case "/ERS-0.0.1-SNAPSHOT/manager.page":
			// user must be logged in as manager
			if (req.getSession(false) != null && 
				((User) req.getSession(false).getAttribute("user")).getRoleId().getId() == 1) {
				req.getRequestDispatcher("html/managerPortal.html").forward(req, resp);
			} else {
				resp.sendRedirect("login.page");
			}
			break;
		case "/ERS-0.0.1-SNAPSHOT/logout.page":
			req.getSession().invalidate();
			resp.sendRedirect("login.page");
			break;
		default:
			req.getRequestDispatcher("html/error.html").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (new RequestForwarder().routes(req)) {
		case "html/employeePortal.html":
			resp.sendRedirect("employee.page");
			break;
		case "html/managerPortal.html":
			resp.sendRedirect("manager.page");
			break;
		default:
			resp.sendRedirect("login.page");
		}
	} 
}
