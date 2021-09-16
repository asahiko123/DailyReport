package com.example.demo.app.workingHour;

import java.time.LocalDate;


import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class WorkingHourForm {
	
	private int id;
	private int type_id;
	private String name;
	@NotNull(message="内容を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate created;
	private int stuff_id;
	@NotNull(message="内容を入力してください")private int work_id;
	private int division_id;
	@NotNull(message="内容を入力してください")private String workTime;
	private boolean newHour;
	
	public WorkingHourForm() {
		
	}
	
	public WorkingHourForm(
			int id,
			int type_id,
			String name,
			LocalDate created,
			int stuff_id,
			int work_id,
			int division_id,
			String workTime,
			boolean newHour) {
		
		this.id =id;
		this.type_id=type_id;
		this.name = name;
		this.created = created;
		this.stuff_id  =stuff_id;
		this.work_id = work_id;
		this.division_id = division_id;
		this.workTime = workTime;
		this.newHour = newHour;
		
	}

	public boolean isNewHour() {
		return newHour;
	}

	public void setNewHour(boolean newHour) {
		this.newHour = newHour;
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

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public int getWork_id() {
		return work_id;
	}

	public void setWork_id(int work_id) {
		this.work_id = work_id;
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

	public  int getStuff_id() {
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

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}



}
