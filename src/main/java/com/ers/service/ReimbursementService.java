package com.ers.service;

import java.util.List;

import com.ers.model.Reimbursement;
import com.ers.repo.ReimbursementDao;

public class ReimbursementService {

	private ReimbursementDao rDao;

	public ReimbursementService() {
		this(new ReimbursementDao());
	}

	public ReimbursementService(ReimbursementDao uDao) {
		this.rDao = uDao;
	}

	public int create(Reimbursement t) {
		return rDao.create(t);
	}

	public List<Reimbursement> findAll() {
		return rDao.findAll();
	}
	
//	public Reimbursement findById(Integer i) {
//		return rDao.findById(i);
//	}
	
//	public int update(Reimbursement t) {
//		return rDao.update(t);
//	}
	
	public int delete(Integer i) {
		return rDao.delete(i);
	}
	
	public int approveDeny(Reimbursement r) {
		return rDao.approveDeny(r);
	}
}
