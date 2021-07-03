package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;

public interface DailyReportService {
	
	List<DailyReport> findAll();
	
	List<Stuff> findStuff();
	
	List<Work> findWork();
	
	Optional<DailyReport> getDailyReport(int id);
	
	
	void insert(DailyReport dailyReport);
	
	void update(DailyReport dailyReport);
	
	void deleteById(int id);
	

	

}
