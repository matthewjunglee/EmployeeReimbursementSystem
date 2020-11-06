package com.ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controller.ReimbursementController;
import com.ers.controller.UserController;

/**
 * Forward request based on user input
 */
public class RequestForwarder {
	
	/**
	 * Receives requests from LoginServlet
	 * @param req httprequest
	 * @return new route
	 */
	public String routes(HttpServletRequest req) {
		switch (req.getRequestURI()) {
		case "/ERS-0.0.1-SNAPSHOT/login.page":
			return new UserController().loginUser(req);
		case "/ERS-0.0.1-SNAPSHOT/createAccount.page":
			return new UserController().registerUser(req);
		default:
			return "";
		}
	}
	
	/**
	 * Receives requests from the DataServlet
	 * @param req 
	 * @param resp 
	 * @throws IOException
	 * @throws ServletException
	 */
	public void data(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getSession(false) == null) {
			resp.sendRedirect("login.page");
			return;
		}
		
		switch (req.getRequestURI()) {
		case "/ERS-0.0.1-SNAPSHOT/reimbursements.json":
			new ReimbursementController().sendAllReimbursements(req, resp);
			break;
		case "/ERS-0.0.1-SNAPSHOT/delete.json":
			new ReimbursementController().deleteReimbursement(req);
			break;
		case "/ERS-0.0.1-SNAPSHOT/create.json":
			new ReimbursementController().createRequest(req, resp);
			break;
		case "/ERS-0.0.1-SNAPSHOT/approveDeny.json":
			new ReimbursementController().approveDeny(req, resp);
			break;
		default:
			resp.sendRedirect("login.page");
			break;
		}
	}
}
