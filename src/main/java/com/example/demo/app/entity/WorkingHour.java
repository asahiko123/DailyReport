package com.example.demo.app.entity;





public class WorkingHour {
	
	private int id;
	private int type_id;
	private String name;
	private int stuff_id;
	private int work_id;
	private Long workTime;
	private String workTimeSum;
	private Stuff stuff;
	private Work work;
	private WorkType workType;
	private DailyReportType dailyReportType;
	private DailyReport dailyReport;
	private String created;
	private String date;
	private String end;
	
	public WorkingHour() {
		
	}
	
	public WorkingHour(
			int id,
		    int type_id,
		    String created,
		    String date,
		    String end,
		    String name,
		    int stuff_id,
		    int work_id,
		    Long workTime,
		    Stuff stuff,
		    Work work,
		    WorkType workType,
		    DailyReportType dailyReportType,
		    DailyReport dailyReport, 
		    String workTimeSum) {
		
		this.id =id;
		this.type_id=type_id;
		this.created = created;
		this.date = date;
		this.end = end;
		this.name = name;
		this.stuff_id =stuff_id;
		this.work_id = work_id;
		this.workTime =workTime;
		this.workTimeSum = workTimeSum;
		this.work = work;
		this.workType =workType;
		this.stuff=stuff;
		this.dailyReportType = dailyReportType;
		this.dailyReport=dailyReport;
		
	}




	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public String getWorkTimeSum() {
		return workTimeSum;
	}

	public void setWorkTimeSum(String workTimeSum) {
		this.workTimeSum = workTimeSum;
	}

	public DailyReportType getDailyReportType() {
		return dailyReportType;
	}

	public void setDailyReportType(DailyReportType dailyReportType) {
		this.dailyReportType = dailyReportType;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DailyReport getDailyReport() {
		return dailyReport;
	}

	public void setDailyReport(DailyReport dailyReport) {
		this.dailyReport = dailyReport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getCreated() {
		return created;
	}

	public void setCreated(String localDate) {
		this.created = localDate;
	}

	public Stuff getStuff() {
		return stuff;
	}


	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getStuff_id() {
		return stuff_id;
	}

	public void setStuff_id(int stuff_id) {
		this.stuff_id = stuff_id;
	}

	public int getWork_id() {
		return work_id;
	}

	public void setWork_id(int work_id) {
		this.work_id = work_id;
	}

	public Long getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Long workTime) {
		this.workTime = workTime;
	}
	
	

}
