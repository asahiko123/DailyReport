package com.example.demo.app.dailyreport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class DailyReportForm {

	private int id;
	
	private int typeId;
	
	@NotNull(message ="内容を入力してください")
	private String created;
	
	@NotNull(message="内容を入力してください")
	
	private String startTime;
	
	@NotNull(message ="内容を入力してください")
	private String endTime;
	
	@NotNull(message ="内容を入力してください")
	@Size(max =20,min = 1)
	private String detail;
	
	private String name;
	
	private String stuffName;
	
	private boolean newReport;
	
	
	public DailyReportForm() {
		
	}
	
	public DailyReportForm(
			int id,
     		@NotNull(message = "内容を入力してください") String created,
			@NotNull(message = "内容を入力してください") String startTime,
			@NotNull(message = "内容を入力してください") String endTime,
			String stuffName,
			String detail,
			String name,
			boolean newReport) {
		
		this.id= id;
		this.created = created;
		this.endTime = endTime;
		this.startTime = startTime;
		this.detail = detail;
		this.stuffName = stuffName;
		this.name = name;
		this.newReport = newReport;
	}

	public boolean isNewReport() {
		return newReport;
	}

	public void setNewReport(boolean newReport) {
		this.newReport = newReport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public String getStuffName() {
		return stuffName;
	}

	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getStartTime() {
		return startTime;
	}

	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int dailyReportId) {
		this.typeId = dailyReportId;
	}
}
