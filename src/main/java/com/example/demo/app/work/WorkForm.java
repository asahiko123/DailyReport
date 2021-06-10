package com.example.demo.app.work;

import javax.validation.constraints.NotNull;

public class WorkForm {
	
	private int id;
	private int typeId;
	@NotNull(message ="内容を入力してください")
	private String comment;
	private boolean newWork;
	
	public WorkForm() {
		
	}
	
	
	public WorkForm(int id,String comment,boolean newWork,int typeId) {
		this.id = id;
		this.comment = comment;
		this.newWork = newWork;
		this.typeId  = typeId;
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
