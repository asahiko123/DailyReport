package com.example.demo.app.entity;




public class DailyReport {
	
	private int id;
	private int stuffId;
	private int workId;
	private DailyReportType dailyReportType;
	private Stuff stuff;
	private Work work;
	private String created;
	private String startTime;
	private String endTime;
	private String detail;
	private String name;
	private int typeId;
	
	
	
	
	public DailyReport() {
		
	}
	
	public DailyReport(
			int id,
			int typeId,
			int stuffId,
			int workId,
			DailyReportType dailyReportType,
			Stuff stuff,
			Work work,
			String created,
			String startTime,
			String endTime,
			String detail,
			String name
			
			) {
		
		  super();
		  this.id = id;
		  this.typeId = typeId;
		  this.stuffId = stuffId;
		  this.workId = workId;
		  this.dailyReportType = dailyReportType;
		  this.stuff = stuff;
		  this.work = work;
          this.created = created;
		  this.startTime = startTime;
		  this.endTime = endTime;
		  this.detail = detail;
		  this.name = name;
		
		
		
	}

	

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	

	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public int getStuffId() {
		return stuffId;
	}

	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
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
