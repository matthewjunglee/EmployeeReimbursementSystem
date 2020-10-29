package com.ers.servlet;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class RequestForwarderTest {

	private RequestForwarder rf;
	private HttpServletRequest req;
	
	@Before
	public void setup() {
		this.rf = new RequestForwarder();
		this.req = Mockito.mock(HttpServletRequest.class);
	}
	
	@Test
	public void createRequestRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("/ERS/createRequest.page");
		assertEquals("createRequest.page", rf.routes(req));
	}
	
	@Test
	public void accountRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("/ERS/newAccount.page");
		assertEquals("newAccount.page", rf.routes(req));
	}
	
	@Test
	public void defaultRouteTest() {
		Mockito.when(req.getRequestURI()).thenReturn("");
		assertEquals("html/login.html", rf.routes(req));
	}
	
}
