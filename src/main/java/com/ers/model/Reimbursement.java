package com.ers.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Reimbursement {

	private int id;
	private int authorId;
	private BigDecimal amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte[] receipt;
	private ReimbursementStatus statusId;
	private ReimbursementType typeId;
	private String typeString;

	private LocalDate dateSubmitted;
	private LocalDate dateResolved;

	public Reimbursement() {}

	public Reimbursement(int id, int authorId, BigDecimal amount, Timestamp submitted, Timestamp resolved,
			String description, byte[] receipt, ReimbursementStatus statusId, ReimbursementType typeId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.statusId = statusId;
		this.typeId = typeId;
		this.authorId = authorId;
		
		this.dateSubmitted = this.submitted.toLocalDateTime().toLocalDate();
		if (this.resolved != null) {
			this.dateResolved = this.resolved.toLocalDateTime().toLocalDate();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public ReimbursementStatus getStatusId() {
		return statusId;
	}

	public void setStatusId(ReimbursementStatus statusId) {
		this.statusId = statusId;
	}

	public ReimbursementType getTypeId() {
		return typeId;
	}

	public void setTypeId(ReimbursementType typeId) {
		this.typeId = typeId;
	}

	public LocalDate getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(LocalDate dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public LocalDate getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(LocalDate dateResolved) {
		this.dateResolved = dateResolved;
	}
	
	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
		this.statusId = new ReimbursementStatus(typeString);
	}

}
