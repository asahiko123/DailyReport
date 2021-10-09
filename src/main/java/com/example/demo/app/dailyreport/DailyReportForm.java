package com.example.demo.app.dailyreport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



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
	
	private String diff;
	
	private String startDate;
	
	private String endDate;

	
	private String name;
	
	private int stuffId;
	
	private int workId;
	
	private String registeredId;
	
	private String workDivId;
	
	private boolean newReport;
	
	
	public DailyReportForm() {
		
	}
	
	public DailyReportForm(
			int id,
     		@NotNull(message = "内容を入力してください") String created,
			@NotNull(message = "内容を入力してください") String startTime,
			@NotNull(message = "内容を入力してください") String endTime,
			@NotNull(message ="内容を入力してください")@Size(min = 1, max = 20)String detail,
			String startDate,
			String endDate,
			String diff,
			String name,
			boolean newReport, 
			int stuffId,
			int workId,
			String registeredId,
			String workDivId) {
		
		this.id= id;
		this.created = created;
		this.endTime = endTime;
		this.startTime = startTime;
		this.startDate = startDate;
		this.diff = diff;
		this.endDate = endDate;
		this.detail = detail;
		this.stuffId = stuffId;
		this.registeredId = registeredId;
		this.workDivId = workDivId;
		this.workId = workId;
		this.name = name;
		this.newReport = newReport;
		
	}


	
	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegisteredId() {
		return registeredId;
	}

	public void setRegisteredId(String registeredId) {
		this.registeredId = registeredId;
	}


	public String getWorkDivId() {
		return workDivId;
	}

	public void setWorkDivId(String workDivId) {
		this.workDivId = workDivId;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
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


	public int getStuffId() {
		return stuffId;
	}

	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
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
