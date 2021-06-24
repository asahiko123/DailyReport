package com.example.demo.app.entity;

public class StuffType {
	
	private int id;
	private String stuffId;
	
	public StuffType() {
		
	}
	
	public StuffType(int id,String stuffId) {
		this.id = id;
		this.stuffId = stuffId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStuffId() {
		return stuffId;
	}

	public void setStuffId(String stuffId) {
		this.stuffId = stuffId;
	}


	

}
