package com.example.demo.app.entity;

import java.sql.Time;
import java.time.LocalDateTime;

public class DailyReport {
	
	private int id;
	private DailyReportType dailyReportType;
	private LocalDateTime created;
	private Time startTime;
	private Time endTime;
	private String detail;
	private int stuffId;
	private int typeId;
	
	
	
	public DailyReport() {
		
	}
	
	public DailyReport(
			int id,
			int typeId,
			DailyReportType dailyReportType,
			LocalDateTime date,
			Time startTime,
			Time endTime,
			String detail,
			int stuffId
			) {
		
		  super();
		  this.id = id;
		  this.typeId = typeId;
		  this.dailyReportType = dailyReportType;
		  this.created = date;
		  this.startTime = startTime;
		  this.endTime = endTime;
		  this.detail = detail;
		  this.stuffId = stuffId;
		
		
		
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}


	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getStuffId() {
		return stuffId;
	}

	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
	}
	
	public DailyReportType getDailyReportType() {
		return dailyReportType;
	}

	public void setDailyReportType(DailyReportType dailyReportType) {
		this.dailyReportType = dailyReportType;
	}


}
