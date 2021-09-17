package com.example.demo.app.entity;

import java.time.LocalDate;



public class WorkingHour {
	
	private int id;
	private int type_id;
	private String name;
	private int stuff_id;
	private int work_id;
	private String workTime;
	private Stuff stuff;
	private Work work;
	private DailyReport dailyReport;
	private LocalDate created;
	
	public WorkingHour() {
		
	}
	
	public WorkingHour(
			int id,
		    int type_id,
		    LocalDate created,
		    String name,
		    int stuff_id,
		    int work_id,
		    String workTime,
		    Stuff stuff,
		    Work work,
		    DailyReport dailyReport) {
		
		this.id =id;
		this.type_id=type_id;
		this.created = created;
		this.name = name;
		this.stuff_id =stuff_id;
		this.work_id = work_id;
		this.workTime =workTime;
		this.work = work;
		this.stuff=stuff;
		this.dailyReport=dailyReport;
		
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



	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate localDate) {
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

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	
	

}
