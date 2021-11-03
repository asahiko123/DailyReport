package com.example.demo.app.entity;



public class DailyReportType {
    
	private int id;
	
	private String progress;
	
	public DailyReportType() {
		
	}
	
	public DailyReportType(int id, String progress) {
		this.id = id;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
}
