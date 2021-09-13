package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;



public interface WorkingHourDao {
	
	List<WorkingHour> findAll();
	
	List<WorkingHour> search();
	
	List<Stuff> findStuff();
	
	List<Work> findWork();
	
	Optional<WorkingHour> getDailyReport(int id);
	
	void insert(WorkingHour workingHour);	
	
	int update(WorkingHour workingHour);
	
	int deleteById(int id);

}
