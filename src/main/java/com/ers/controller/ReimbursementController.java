package com.ers.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.model.Reimbursement;
import com.ers.model.ReimbursementType;
import com.ers.model.User;
import com.ers.service.ReimbursementService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementController {

	private ReimbursementService rs;

	public ReimbursementController() {
		this(new ReimbursementService());
	}

	public ReimbursementController(ReimbursementService rs) {
		this.rs = rs;
	}

	public void createRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Reimbursement r = new Reimbursement();
		r.setAuthorId(((User)req.getSession().getAttribute("user")).getId());
		r.setAmount(BigDecimal.valueOf(Double.parseDouble(req.getParameter("amount"))));
		r.setSubmitted(new Timestamp(System.currentTimeMillis()));
		r.setDescription(req.getParameter("description"));
		r.setTypeId(new ReimbursementType(Integer.parseInt(req.getParameter("type"))));
		
		rs.create(r);
		resp.sendRedirect("employee.page");
	}

	public void sendAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
		resp.setContentType("text/json");
		User u = (User) req.getSession(false).getAttribute("user");
		
		List<Reimbursement> reimbs = rs.findAll();
		
		// filter reimbursements if user is an employee
		if (u.getRoleId().getId() == 2) {
			reimbs = reimbs.stream()
							.filter(r -> r.getAuthorId() == u.getId())
							.collect(Collectors.toList());
			
			for (Reimbursement r : reimbs) {
				r.setResolverId(0);
			}
		}
		
		try {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void approveDeny(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/json");
		try {
			Reimbursement r = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
			r.setResolved(new Timestamp(System.currentTimeMillis()));
			r.setResolverId(((User) req.getSession(false).getAttribute("user")).getId());
			
			rs.approveDeny(r);
			
			List<Reimbursement> reimbs = rs.findAll();
			resp.getWriter().println(new ObjectMapper().writeValueAsString(reimbs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void update(HttpServletRequest req) {
//		try {
////			Object r = new ObjectMapper().readValue(req.getInputStream(), Object.class);
//			Reimbursement r = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void deleteReimbursement(HttpServletRequest req) {
		try {
			Reimbursement r = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
			rs.delete(r.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
