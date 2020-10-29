package com.ers.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserRoleTest {

	private UserRole u;
	
	@Before
	public void setup() {
		this.u = new UserRole(3);
	}
	
	@Test
	public void setIdTest() {
		u.setId(1);
		assertEquals(1, u.getId());
	}
	
	@Test
	public void setRoleTest() {
		u.setRole("Employee");
		assertEquals("Employee", u.getRole());
	}
	
}
