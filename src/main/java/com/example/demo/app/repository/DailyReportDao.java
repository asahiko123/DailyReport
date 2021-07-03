package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;

public interface DailyReportDao {
	
	List<DailyReport> findAll();
	
	List<Stuff> findStuff();
	
	List<Work> findWork();
	
	Optional<DailyReport> getDailyReport(int id);
	
	void insert(DailyReport dailyReport);
	
	int update(DailyReport dailyReport);
	
	int deleteById(int id);
	

}
