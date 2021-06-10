package com.example.demo.app.entity;

public class SupplierType {
	
	private int id;
	private String type;
	
	public SupplierType() {
		
	}
	
	public SupplierType(int id,String type) {
		this.id = id;
		this.type = type;
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
