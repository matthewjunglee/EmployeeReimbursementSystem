package com.ers.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ReimbursementTypeTest {

	private ReimbursementType r;
	
	@Before
	public void setup() {
		this.r = new ReimbursementType(5);
	}
	
	@Test
	public void setIdTest() {
		r.setId(1);
		assertEquals(1, r.getId());
	}
	
	@Test
	public void setTypeTest() {
		r.setType("LODGING");
		assertEquals("LODGING", r.getType());
	}
	
}
