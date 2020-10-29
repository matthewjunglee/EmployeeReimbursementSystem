package com.ers.model;

public class ReimbursementType {

	private int id;
	private String type;

	public ReimbursementType(int id) {
		super();
		this.id = id;

		switch (id) {
		case 1:
			type = "LODGING";
			break;
		case 2:
			type = "TRAVEL";
			break;
		case 3:
			type = "FOOD";
			break;
		case 4:
			type = "OTHER";
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
