package com.ers.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ReimbursementStatusTest {

	private ReimbursementStatus r1;
	private ReimbursementStatus r2;
	private ReimbursementStatus r3;
	private ReimbursementStatus r4;
	
	@Before
	public void setup() {
		this.r1 = new ReimbursementStatus("PENDING");
		this.r2 = new ReimbursementStatus("DENIED");
		this.r3 = new ReimbursementStatus("");
		this.r4 = new ReimbursementStatus(2);
	}
	
	@Test
	public void setIdTest() {
		r1.setId(1);
		assertEquals(1, r1.getId());
	}
	
	@Test
	public void getStatusTest() {
		assertEquals("DENIED", r2.getStatus());
	}
	
	@Test
	public void setStatusTest() {
		r3.setStatus("DENIED");
		assertEquals("DENIED", r3.getStatus());
	}
	
}
