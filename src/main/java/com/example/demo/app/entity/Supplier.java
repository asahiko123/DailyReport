package com.example.demo.app.entity;

public class Supplier {
	
	private int id;
	private int typeId;
	private String name;
	private SupplierType supplierType;
	
	public Supplier() {
		
	}

	public Supplier(int id,String name, int typeId, SupplierType supplierType) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
