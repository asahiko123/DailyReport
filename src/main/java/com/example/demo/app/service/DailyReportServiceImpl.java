package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.repository.DailyReportDao;

@Service
public class DailyReportServiceImpl implements DailyReportService{

	private final DailyReportDao dailyReportDao;
	
	public DailyReportServiceImpl(DailyReportDao dailyReportDao) {
		this.dailyReportDao = dailyReportDao;
	}
	@Override
	public List<DailyReport> findAll() {
		
		return dailyReportDao.findAll();
	}
	
    public List<Stuff> findStuff() {
		
		return dailyReportDao.findStuff();
	}
    
    public List<Work> findWork(){
    	return dailyReportDao.findWork();
    }

	@Override
	public Optional<DailyReport> getDailyReport(int id) {
		
		try {
			return dailyReportDao.getDailyReport(id);
		}catch(EmptyResultDataAccessException e) {
			throw new DataNotFoundException("指定されたデータは存在しません");
		}
		
	}

	@Override
	public void insert(DailyReport dailyReport) {
		dailyReportDao.insert(dailyReport);
		
	}

	@Override
	public void update(DailyReport dailyReport) {
		
		if(dailyReportDao.update(dailyReport) == 0) {
			throw new DataNotFoundException("更新するデータが存在しません");
		}
		
	}

	@Override
	public void deleteById(int id) {
	
		if(dailyReportDao.deleteById(id) == 0) {
			throw new DataNotFoundException("削除するデータが存在しません");
		}
		
	}
	@Override
	public void resetNum(DailyReport dailyReport) {
		
		dailyReportDao.resetNum(dailyReport);
		
	}
	

}
