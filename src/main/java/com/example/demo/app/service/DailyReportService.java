package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;

public interface DailyReportService {
	
	List<DailyReport> findAll();
	
	Optional<DailyReport> getDailyReport(int id);
	
	void insert(DailyReport dailyReport);
	
	void update(DailyReport dailyReport);
	
	void deleteById(int id);
	

	

}
