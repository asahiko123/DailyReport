package com.example.demo.app.entity;

import javax.validation.constraints.NotNull;

public class Supplier {
	
	private int id;
	private int typeId;
	@NotNull(message ="内容を入力してください")
	private String comment;
	private SupplierType supplierType;
	
	public Supplier() {
		
	}

	public Supplier(int id,String comment, int typeId, SupplierType supplierType) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.comment = comment;
		this.supplierType = supplierType;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public SupplierType getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
