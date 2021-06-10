package com.example.demo.app.entity;

public class Work {
	
	private int id;
	private int typeId;
	private WorkType workType;
	private String comment;
	
	public Work() {
		
	}
	public Work(int id,String comment, int typeId, WorkType workType) {
		this.id = id;
		this.typeId = typeId;
		this.workType = workType;
		this.comment = comment;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public WorkType getWorkType() {
		return workType;
	}
	public void setWorkType(WorkType workType) {
		this.workType = workType;
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
