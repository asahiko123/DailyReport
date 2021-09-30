 package com.example.demo.app.repository;


import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;



public interface WorkingHourDao {
	
	List<WorkingHour> findAll();
	
	List<Stuff> findStuff();
	
	List<Work> findWork();
	
	List<DailyReport> findDailyReport();
	
	List<WorkingHour>search(WorkingHour workingHour);
	
	Optional<WorkingHour> getWorkingHour(int id);
	
	WorkingHour sum(WorkingHour workingHour);
	
	
	void  insert(WorkingHour workingHour);	
	
	int update(WorkingHour workingHour);
	
	int deleteById(int id);

}
