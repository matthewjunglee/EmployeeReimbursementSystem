package com.ers.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ers.model.User;
import com.ers.repo.UserDao;

public class UserServiceTest {

	private UserDao ud;
	private UserService us;
	private UserService us1;
	
	@Before
	public void setup() {
		this.ud = Mockito.mock(UserDao.class);
		this.us = new UserService(this.ud);
		this.us1 = new UserService();
	}
	
	@Test
	public void loginTest() {
		User u = new User(0, null, null, null, null, null, null);
		Mockito.when(ud.login("test", "test")).thenReturn(u);
		assertEquals(0, us.login("test", "test").getId());
	}
	
	@Test
	public void createTest() {
		User u = new User(0, null, null, null, null, null, null);
		Mockito.when(ud.create(u)).thenReturn(1);
		assertEquals(1, us.create(u));
	}
	
	@Test
	public void findAllTest() {
		Mockito.when(ud.findAll()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), us.findAll());
	}
	
//	@Test
//	public void findByIdTest() {
//		Mockito.when(ud.findById(1)).thenReturn(new User(1));
//		assertEquals(1, us.findById(1).getId());
//	}
	
//	@Test
//	public void updateTest() {
//		User u = new User(0, null, null, null, null, null, null);
//		Mockito.when(ud.update(u)).thenReturn(1);
//		assertEquals(1, us.update(u));
//	}
	
	@Test
	public void deleteTest() {
		Mockito.when(ud.delete(1)).thenReturn(1);
		assertEquals(1, us.delete(1));
	}
	
}
