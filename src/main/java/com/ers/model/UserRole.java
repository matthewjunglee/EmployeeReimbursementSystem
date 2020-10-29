package com.ers.model;

import java.io.Serializable;

public class UserRole implements Serializable {

	private int id;
	private String role;

	public UserRole(int id) {
		this.id = id;

		switch (id) {
		case 1:
			role = "FinanceManager";
			break;
		case 2:
			role = "Employee";
			break;
		default:
			break;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
