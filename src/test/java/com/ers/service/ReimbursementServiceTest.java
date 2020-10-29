package com.ers.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ers.model.Reimbursement;
import com.ers.repo.ReimbursementDao;

public class ReimbursementServiceTest {

	private ReimbursementDao rd;
	private ReimbursementService rs;
	
	@Before
	public void setup() {
		this.rd = Mockito.mock(ReimbursementDao.class);
		this.rs = new ReimbursementService(this.rd);
	}
	
	@Test
	public void createTest() {
		Reimbursement r = new Reimbursement(0, 0, null, new Timestamp(0), null, null, null, null, null);
		Mockito.when(rd.create(r)).thenReturn(1);
		assertEquals(1, rs.create(r));
	}
	
	@Test
	public void findAllTest() {
		Mockito.when(rd.findAll()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), rs.findAll());
	}
	
//	@Test
//	public void findByIdTest() {
//		Reimbursement r = new Reimbursement(1, null, null, null, null);
//		Mockito.when(rd.findById(1)).thenReturn(r);
//		assertEquals(1, rs.findById(1).getId());
//	}
	
//	@Test
//	public void updateTest() {
//		Reimbursement r = new Reimbursement(1, null, null, null, null);
//		Mockito.when(rd.update(r)).thenReturn(1);
//		assertEquals(1, rs.update(r));
//	}
	
	@Test
	public void deleteTest() {
		Mockito.when(rd.delete(1)).thenReturn(1);
		assertEquals(1, rs.delete(1));
	}
	
	@Test
	public void approveDenyTest() {
		Reimbursement r = new Reimbursement();
		Mockito.when(rd.approveDeny(r)).thenReturn(1);
		assertEquals(1, rs.approveDeny(r));		
	}
	
}
