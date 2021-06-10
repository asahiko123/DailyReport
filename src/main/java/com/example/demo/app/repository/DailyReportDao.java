package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;

public interface DailyReportDao {
	
	List<DailyReport> findAll();
	
	Optional<DailyReport> getDailyReport(int id);
	
	void insert(DailyReport dailyReport);
	
	int update(DailyReport dailyReport);
	
	int deleteById(int id);
	

}
