package com.ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginServletTest {

	private LoginServlet ls;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	@Before
	public void setup() {
		this.ls = new LoginServlet();
		this.req = Mockito.mock(HttpServletRequest.class);
		this.resp = Mockito.mock(HttpServletResponse.class);
	}
	
	@Test
	public void defaultPostTest() {
		Mockito.when(req.getRequestURI()).thenReturn("/ERS/login.page");
		Mockito.when(req.getContextPath()).thenReturn("/ERS");
		try {
			ls.doPost(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
