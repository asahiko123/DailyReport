package com.example.demo.app.entity;

public class StuffType {
	
	private int id;
	private String department;
	
	public StuffType() {
		
	}
	
	public StuffType(int id,String department) {
		this.id = id;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	

}
