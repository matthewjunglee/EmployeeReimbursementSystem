package com.ers.servlet;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RequestForwarderTest {

	private RequestForwarder rf;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession hs;
	
	@Before
	public void setup() {
		this.rf = new RequestForwarder();
		this.req = Mockito.mock(HttpServletRequest.class);
		this.resp = Mockito.mock(HttpServletResponse.class);
		this.hs = Mockito.mock(HttpSession.class);
	}
	
	@Test
	public void defaultRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("null");
		Mockito.when(req.getContextPath()).thenReturn("");
		assertEquals("", rf.routes(req));
	}
	
	@Test
	public void loginRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("/ERS/login.page");
		Mockito.when(req.getContextPath()).thenReturn("/ERS");
		rf.routes(req);
	}
	
	@Test
	public void accountRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("/ERS/newAccount.page");
		Mockito.when(req.getContextPath()).thenReturn("/ERS");
		rf.routes(req);
	}
	
	@Test
	public void sessionDataTest() {
		Mockito.when(req.getSession(false)).thenReturn(null);
		try {
			rf.data(req, resp);
		} catch (IOException | ServletException e) {}
	}
	
	@Test
	public void defaultDataTest() {
		Mockito.when(req.getSession(false)).thenReturn(hs);
		Mockito.when(req.getRequestURI()).thenReturn("null");
		Mockito.when(req.getContextPath()).thenReturn("");
		try {
			rf.data(req, resp);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}
