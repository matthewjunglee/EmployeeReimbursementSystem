package com.ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controller.ReimbursementController;
import com.ers.controller.UserController;

public class RequestForwarder {
	
	public String routes(HttpServletRequest req) {
		switch (req.getRequestURI()) {
		case "/ERS/login.page":
			return new UserController().loginUser(req);
		case "/ERS/createAccount.page":
			return new UserController().registerUser(req);
		default:
			return "";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getSession(false) == null) {
			resp.sendRedirect("login.page");
			return;
		}
		
		switch (req.getRequestURI()) {
		case "/ERS/reimbursements.json":
			new ReimbursementController().sendAllReimbursements(req, resp);
			break;
		case "/ERS/delete.json":
			new ReimbursementController().deleteReimbursement(req);
			break;
		case "/ERS/create.json":
			new ReimbursementController().createRequest(req, resp);
			break;
		case "/ERS/approveDeny.json":
			new ReimbursementController().approveDeny(req, resp);
			break;
		default:
			resp.sendRedirect("login.page");
			break;
		}
	}
}
