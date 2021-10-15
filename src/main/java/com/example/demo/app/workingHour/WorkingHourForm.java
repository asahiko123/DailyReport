package com.example.demo.app.workingHour;


public class WorkingHourForm {
	
	private int id;
	private int type_id;
	private String name;
	private String created;
	private String end;
	private Long workTime;
	private String workTimeSum;
	private int stuff_id;
	private int work_id;

//	@NotNull(message="内容を入力してください")private String workTime;
	private boolean newHour;

	
	public WorkingHourForm() {
		
	}
	
	public WorkingHourForm(
			int id,
			int type_id,
			String name,
			String created,
			String end,
			Long workTime,
			String workTimeSum,
			int stuff_id,
			int work_id,
			int division_id,
			boolean newHour) {
		
		this.id =id;
		this.type_id=type_id;
		this.name = name;
		this.created = created;
		this.end = end;
		this.stuff_id  =stuff_id;
		this.work_id = work_id;
		this.workTimeSum = workTimeSum;
		this.workTime = workTime;
		this.newHour = newHour;
		
	}

	public Long getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Long workTime) {
		this.workTime = workTime;
	}

	public String getWorkTimeSum() {
		return workTimeSum;
	}

	public void setWorkTimeSum(String workTimeSum) {
		this.workTimeSum = workTimeSum;
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


	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String string) {
		this.created = string;
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



//	public String getWorkTime() {
//		return workTime;
//	}
//
//	public void setWorkTime(String workTime) {
//		this.workTime = workTime;
//	}



}
