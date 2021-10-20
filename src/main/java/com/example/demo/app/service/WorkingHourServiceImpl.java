package com.example.demo.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;
import com.example.demo.app.repository.WorkingHourDao;

@Service
public class WorkingHourServiceImpl implements WorkingHourService{
	
	private final WorkingHourDao workingHourDao;
	
	public WorkingHourServiceImpl(WorkingHourDao workingHourDao) {
		this.workingHourDao = workingHourDao;
	}

	@Override
	public List<WorkingHour> findAll() {
		
		return workingHourDao.findAll();
	}

	@Override
	public List<Stuff> findStuff() {
		
		return workingHourDao.findStuff();
	}

	@Override
	public List<Work> findWork() {
		
		return workingHourDao.findWork();
	}
	
	@Override
	public List<DailyReport> findDailyReport() {
		
		return workingHourDao.findDailyReport();
	}



	@Override
	public Optional<WorkingHour> getWorkingHour(int id) {
		
		try {
			return workingHourDao.getWorkingHour(id);
		}catch(EmptyResultDataAccessException e) {
			throw new DataNotFoundException("指定されたデータは存在しません");
		}
	}

	@Override
	public void insert(WorkingHour workingHour) {
		workingHourDao.insert(workingHour);
		
	}

	@Override
	public void update(WorkingHour workingHour) {
		if(workingHourDao.update(workingHour)==0) {
			throw new DataNotFoundException("更新するデータが存在しません");
		}
	}

	@Override
	public void deleteById(int id) {
		
		if(workingHourDao.deleteById(id) == 0) {
			throw new DataNotFoundException("削除するデータは存在しません");
		}
		
	}

	@Override
	public List<WorkingHour> search(WorkingHour workingHour) {
		
		return workingHourDao.search(workingHour);
	}

	@Override
	public WorkingHour sum(WorkingHour workingHour) {
		
		return workingHourDao.sum(workingHour);
	}

	@Override
	public List<WorkingHour> searchAll(WorkingHour workingHour) {
		
		return workingHourDao.searchAll(workingHour);
	}





}
