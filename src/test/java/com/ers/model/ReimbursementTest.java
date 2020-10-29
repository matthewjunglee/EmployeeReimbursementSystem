package com.ers.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ReimbursementTest {

	private Reimbursement r;
	
	@Before
	public void setup() {
		this.r = new Reimbursement();
	}
	
	@Test
	public void setTypeStringTest() {
		r.setTypeString("APPROVED");
		assertEquals("APPROVED", r.getTypeString());
	}
	
	@Test
	public void setAmountTest() {
		r.setAmount(null);
		assertEquals(null, r.getAmount());
	}
	
	@Test
	public void setSubmittedTest() {
		r.setSubmitted(null);
		assertEquals(null, r.getSubmitted());
	}
	
	@Test
	public void setDescriptionTest() {
		r.setDescription("d");
		assertEquals("d", r.getDescription());
	}
	
	@Test
	public void setReceiptTest() {
		r.setReceipt(null);
		assertEquals(null, r.getReceipt());
	}
	
	@Test
	public void setAuthorTest() {
		r.setAuthorId(1);
		assertEquals(1, r.getAuthorId());
	}
	
	@Test
	public void setTypeTest() {
		r.setTypeId(null);
		assertEquals(null, r.getTypeId());
	}
	
	@Test
	public void setDateSubmitTest() {
		r.setDateSubmitted(null);;
		assertEquals(null, r.getDateSubmitted());
	}
	
	@Test
	public void setDateResolveTest() {
		r.setDateResolved(null);;
		assertEquals(null, r.getDateResolved());
	}
	
}
