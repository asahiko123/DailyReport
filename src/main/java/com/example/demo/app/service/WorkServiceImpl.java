package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.Work;
import com.example.demo.app.repository.WorkDao;
@Service
public class WorkServiceImpl implements WorkService{
	
	private final WorkDao workDao;
	
	public WorkServiceImpl(WorkDao workDao) {
		this.workDao = workDao;
	}
		
	@Override
	public List<Work> findAll() {
		
		return workDao.findAll();
		
	}

	

	@Override
	public Optional<Work> getWork(int id) {
		try {
			return workDao.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new DataNotFoundException("指定されたデータは見つかりませんでした");
		}
		
	}

	@Override
	public void insert(Work work) {
		workDao.insert(work);
		
	}

	@Override
	public void update(Work work) {
		if(workDao.update(work) == 0) {
			throw new DataNotFoundException("更新すべきデータは見つかりませんでした");
		}
		
	}

	@Override
	public void deleteById(int id) {
		if(workDao.deleteById(id) == 0) {
			throw new DataNotFoundException("削除すべきデータはみつかりませんでした");
		}
		
	}


	
}
