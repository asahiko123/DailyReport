package com.example.demo.app.supplier;

import javax.validation.constraints.NotNull;

import com.example.demo.app.entity.SupplierType;

public class SupplierForm {
	
	private int id;
	private int typeId;
	private SupplierType supplierType;
	private String supplier;
	@NotNull(message ="内容を入力してください")
	private String comment;
	private boolean newSupplier;
	
	
	public SupplierForm() {
		
	}
	
	public SupplierForm(int id, int typeId, SupplierType supplierType,String supplier,String comment, boolean newSupplier) {
		this.id = id;
		this.typeId = typeId;
		this.supplierType = supplierType;
		this.supplier = supplier;
		this.comment  = comment;
		this.newSupplier = newSupplier;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public SupplierType getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}

	public boolean isNewSupplier() {
		return newSupplier;
	}

	public void setNewSupplier(boolean newSupplier) {
		this.newSupplier = newSupplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int supplierId) {
		this.typeId = supplierId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
