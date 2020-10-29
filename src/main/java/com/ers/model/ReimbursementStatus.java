package com.ers.model;

public class ReimbursementStatus {

	private int id;
	private String status;

	public ReimbursementStatus(int id) {
		this.id = id;

		switch (id) {
		case 0:
			status = "PENDING";
			break;
		case 1:
			status = "APPROVED";
			break;
		case -1:
			status = "DENIED";
			break;
		default:
			break;
		}
	}

	public ReimbursementStatus(String status) {
		this.status = status;

		switch (status) {
		case "PENDING":
			id = 0;
			break;
		case "APPROVED":
			id = 1;
			break;
		case "DENIED":
			id = -1;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
