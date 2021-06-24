package com.example.demo.app.work;

import javax.validation.constraints.NotNull;



public class WorkForm {
	
	private int id;
	private int typeId;
	@NotNull(message ="内容を入力してください")
	private String comment;
	private String workDivId;
	private boolean newWork;
	
	
	public WorkForm() {
		
	}
	
	
	public WorkForm(int id,int typeId,String comment,String workDivId,boolean newWork) {
		this.id = id;
		this.typeId  = typeId;
		this.comment = comment;
		this.workDivId = workDivId;
		this.newWork = newWork;
		
		
	}


	public String getWorkDivId() {
		return workDivId;
	}


	public void setWorkDivId(String workDivId) {
		this.workDivId = workDivId;
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
	
	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int workId) {
		this.typeId = workId;
	}



	public boolean isNewWork() {
		return newWork;
	}


	public void setNewWork(boolean newWork) {
		this.newWork = newWork;
	}
	
	

}
