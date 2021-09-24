package com.example.demo.app.service;


import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;

public interface WorkingHourService {
	
	List<WorkingHour> findAll();
	
	List<Stuff> findStuff();
	
	List<Work> findWork();
	
	List<DailyReport> findDailyReport();
	
	List<WorkingHour> search();
	
	
	Optional<WorkingHour> getWorkingHour(int id);
	
	
	
	void insert(WorkingHour workingHour);
	
	void update(WorkingHour workingHour);
	
	void deleteById(int id);
	
	
}
