package com.ers.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.ers.model.Reimbursement;
import com.ers.model.ReimbursementStatus;
import com.ers.model.ReimbursementType;

public class ReimbursementDaoTest {

	private ReimbursementDao rd;

	@Before
	public void setup() {
		this.rd = new ReimbursementDao();
	}

	@Test
	public void createTest() {
		Reimbursement r = new Reimbursement(0, 3, 0, BigDecimal.valueOf(10.00),
				new Timestamp(System.currentTimeMillis()), null, "test", null, null, new ReimbursementType(1));
		assertEquals(1, rd.create(r));
	}
	
	@Test
	public void invalidCreateTest() {
		Reimbursement r = new Reimbursement(0, 0, 0, BigDecimal.valueOf(10.00),
				new Timestamp(System.currentTimeMillis()), null, "test", null, null, new ReimbursementType(1));
		assertEquals(0, rd.create(r));
	}

	@Test
	public void findAllTest() {
		assertTrue(rd.findAll().size() > 0);
	}

	@Test
	public void findByIdTest() {
		assertEquals(null, rd.findById(1));
	}

//	@Test
//	public void findByAuthorIdTest() {
//		Mockito.when(rd.findByAuthorId(3)).thenReturn(new ArrayList<>());
//		assertEquals(new ArrayList<>(), rd.findByAuthorId(3));
//	}

	@Test
	public void updateTest() {
		assertEquals(0, rd.update(null));
	}

	@Test
	public void deleteTest() {
		assertEquals(1, rd.delete(1));
	}
	
	@Test
	public void invalidDeleteTest() {
		assertEquals(0, rd.delete(0));
	}
	
	@Test
	public void applyDenyTest() {
		Reimbursement r = new Reimbursement();
		r.setStatusId(new ReimbursementStatus(1));
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		r.setResolverId(1);
		r.setId(2);
		assertEquals(1, rd.approveDeny(r));
	}
	
	@Test
	public void invalidApplyDenyTest() {
		Reimbursement r = new Reimbursement();
		r.setStatusId(new ReimbursementStatus(1));
		r.setResolved(new Timestamp(System.currentTimeMillis()));
		r.setResolverId(0);
		r.setId(2);
		assertEquals(0, rd.approveDeny(r));
	}
}
