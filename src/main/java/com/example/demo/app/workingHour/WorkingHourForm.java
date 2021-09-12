package com.example.demo.app.workingHour;

public class WorkingHourForm {
	
	private int id;
	private int type_id;
	private int stuff_id;
	private int division_id;
	private int breakdown_time;
	
	public WorkingHourForm(
			int id,
			int type_id,
			int stuff_id,
			int division_id,
			int breakdown_time) {
		
		this.id =id;
		this.type_id=type_id;
		this.stuff_id  =stuff_id;
		this.division_id = division_id;
		this.breakdown_time = breakdown_time;
		
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

	public int getDivision_id() {
		return division_id;
	}

	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}

	public int getBreakdown_time() {
		return breakdown_time;
	}

	public void setBreakdown_time(int breakdown_time) {
		this.breakdown_time = breakdown_time;
	}

}
