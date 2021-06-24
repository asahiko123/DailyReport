package com.example.demo.app.entity;

public class Stuff {
	
	private int id;
	private StuffType stuffType;
	private int typeId;
	private String name;
    private String detail;
    private String registeredId;
	
	public Stuff() {
		
	}
	
	public Stuff(int id,StuffType stuffType,int typeId,String name,String detail,String registeredId) {
		this.id = id;
		this.stuffType = stuffType;
		this.typeId  = typeId;
		this.name = name;
		this.detail = detail;
		this.registeredId = registeredId;
		
	}



	public String getRegisteredId() {
		return registeredId;
	}

	public void setRegisteredId(String registeredId) {
		this.registeredId = registeredId;
	}

	public StuffType getStuffType() {
		return stuffType;
	}

	public void setStuffType(StuffType stuffType) {
		this.stuffType = stuffType;
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
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


}
