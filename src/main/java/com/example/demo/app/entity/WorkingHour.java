package com.example.demo.app.entity;

public class WorkingHour {
	
	private int id;
	private int type_id;
	private int stuff_id;
	private int work_id;
	private int workTime;
	private Stuff stuff;
	private Work work;
	
	public WorkingHour() {
		
	}
	
	public WorkingHour(
			int id,
		    int type_id,
		    int stuff_id,
		    int work_id,
		    int workTime,
		    Stuff stuff,
		    Work work) {
		
		this.id =id;
		this.type_id=type_id;
		this.stuff_id =stuff_id;
		this.work_id = work_id;
		this.workTime =workTime;
		this.work = work;
		this.stuff=stuff;
		
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

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	
	

}
