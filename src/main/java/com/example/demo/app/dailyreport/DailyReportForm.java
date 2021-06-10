package com.example.demo.app.dailyreport;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class DailyReportForm {

	private int id;
	
	private int typeId;

	@NotNull(message ="内容を入力してください")
	@Future(message = "過去に設定されています")
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm\"")
	private LocalDateTime created;
	
	@NotNull(message="内容を入力してください")
	private Time startTime;
	
	@NotNull(message ="内容を入力してください")
	private Time endTime;
	
	@NotNull(message ="内容を入力してください")
	private String detail;
	
	private int stuffId;
	
	private boolean newReport;
	
	
	public DailyReportForm() {
		
	}
	
	public DailyReportForm(
			int id,
			LocalDateTime created,
			Time startTime,
			Time endTime,
			String detail,
			int stuffId,
			boolean newReport) {
		
		this.id= id;
		this.created = created;
		this.endTime = endTime;
		this.startTime = startTime;
		this.detail = detail;
		this.stuffId  = stuffId;
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
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int dailyReportId) {
		this.typeId = dailyReportId;
	}
}
