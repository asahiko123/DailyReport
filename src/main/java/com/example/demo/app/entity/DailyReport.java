package com.example.demo.app.entity;




public class DailyReport {
	
	private int id;
	private DailyReportType dailyReportType;
	private String created;
	private String startTime;
	private String endTime;
	private String detail;
	private String name;
	private String stuffName;
	private int typeId;

	
	
	
	public DailyReport() {
		
	}
	
	public DailyReport(
			int id,
			int typeId,
			int stuffId,
			DailyReportType dailyReportType,
			String created,
			String startTime,
			String endTime,
			String detail,
			String name,
			String stuffName
			) {
		
		  super();
		  this.id = id;
		  this.typeId = typeId;
		  this.stuffName = stuffName;
		  this.dailyReportType = dailyReportType;
          this.created = created;
		  this.startTime = startTime;
		  this.endTime = endTime;
		  this.detail = detail;
		  this.name = name;
		
		
		
	}

	

	public String getStuffName() {
		return stuffName;
	}

	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public DailyReportType getDailyReportType() {
		return dailyReportType;
	}

	public void setDailyReportType(DailyReportType dailyReportType) {
		this.dailyReportType = dailyReportType;
	}


}
