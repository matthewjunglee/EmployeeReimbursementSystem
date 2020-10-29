package com.ers.service;

import java.util.List;

import com.ers.model.User;
import com.ers.repo.UserDao;

public class UserService {

	private UserDao uDao;

	public UserService() {
		this(new UserDao());
	}

	public UserService(UserDao uDao) {
		this.uDao = uDao;
	}

	public User login(String username, String password) {
		return uDao.login(username, password);
	}

	public int create(User t) {
		return uDao.create(t);
	}

	public List<User> findAll() {
		return uDao.findAll();
	}
	
//	public User findById(Integer i) {
//		return uDao.findById(i);
//	}
	
//	public int update(User t) {
//		return uDao.update(t);
//	}
	
	public int delete(Integer i) {
		return uDao.delete(i);
	}

}
