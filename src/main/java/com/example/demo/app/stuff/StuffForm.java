package com.example.demo.app.stuff;

import javax.validation.constraints.NotNull;

public class StuffForm {
	
	private int id;
	private int typeId;
	private int stuffId;
	private String name;
	private String detail;
	@NotNull(message ="内容を入力してください")		
	private String registeredId;
	private boolean newStuff;

	public StuffForm() {
		
	}
	public StuffForm(int id,int typeId,String name,String detail,String registeredId,int stuffId, boolean newStuff) {
		this.id = id;
		this.typeId = typeId;
		this.stuffId = stuffId;
		this.name = name;
		this.detail = detail;
		this.registeredId = registeredId;
		this.newStuff = newStuff;
	}
	
	public int getStuffId() {
		return stuffId;
	}
	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
	}
	public String getRegisteredId() {
		return registeredId;
	}
	public void setRegisteredId(String registeredId) {
		this.registeredId = registeredId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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
	public void setTypeId(int stuffId) {
		this.typeId = stuffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNewStuff() {
		return newStuff;
	}
	public void setNewStuff(boolean newStuff) {
		this.newStuff = newStuff;
	}
	
	
}
