package com.ers.controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ers.model.User;
import com.ers.model.UserRole;
import com.ers.service.UserService;

public class UserControllerTest {

	private UserService us;
	private UserController uc;
	private UserController uc1;

	@Before
	public void setup() {
		this.us = Mockito.mock(UserService.class);
		this.uc = new UserController(us);
		this.uc1 = new UserController();
	}

	@Test
	public void invalidLoginUserTest() {
		HttpServletRequest hsr = Mockito.mock(HttpServletRequest.class);
		Mockito.when(hsr.getParameter("username")).thenReturn("test");
		Mockito.when(hsr.getParameter("password")).thenReturn("test");
		
		User u = new User(0, null, null, null, null, null, null);
		Mockito.when(us.login("test", "test")).thenReturn(u);
		
		assertEquals("html/login.html", uc.loginUser(hsr));
	}
	
	@Test
	public void validManagerLoginUserTest() {
		HttpServletRequest hsr = Mockito.mock(HttpServletRequest.class);
		Mockito.when(hsr.getParameter("username")).thenReturn("test");
		Mockito.when(hsr.getParameter("password")).thenReturn("test");
		User u = new User(0, "test", "test", "test", "test", "test", new UserRole(1));
		Mockito.when(us.login("test", "test")).thenReturn(u);
		
		HttpSession hs = Mockito.mock(HttpSession.class);
		Mockito.when(hsr.getSession()).thenReturn(hs);
		
		assertEquals("html/managerPortal.html", uc.loginUser(hsr));
	}
	
	@Test
	public void validEmployeeLoginUserTest() {
		HttpServletRequest hsr = Mockito.mock(HttpServletRequest.class);
		Mockito.when(hsr.getParameter("username")).thenReturn("test");
		Mockito.when(hsr.getParameter("password")).thenReturn("test");
		User u = new User(0, "test", "test", "test", "test", "test", new UserRole(2));
		Mockito.when(us.login("test", "test")).thenReturn(u);
		
		HttpSession hs = Mockito.mock(HttpSession.class);
		Mockito.when(hsr.getSession()).thenReturn(hs);
		
		assertEquals("html/employeePortal.html", uc.loginUser(hsr));
	}
	
	@Test
	public void invalidRegisterUserTest() {
		HttpServletRequest hsr = Mockito.mock(HttpServletRequest.class);
		assertEquals("html/login.html", uc.registerUser(hsr));
	}
	
//	@Test
//	public void validRegisterUserTest() {
//		HttpServletRequest hsr = Mockito.mock(HttpServletRequest.class);		
//		Mockito.when(us.create(null)).thenReturn(1);
//		User u = Mockito.mock(User.class);
//		Mockito.when(u.getId()).thenReturn(1);
//		System.out.println(u.getId());
//		assertEquals("html/employeePortal.html", uc.registerUser(hsr));
//	}

//
//	@Test
//	public void updateUser() {
//	}
//
//	@Test
//	public void deleteUser() {
//	}

}
