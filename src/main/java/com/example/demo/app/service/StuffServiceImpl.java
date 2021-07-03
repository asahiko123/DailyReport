package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.repository.StuffDao;
@Service
public class StuffServiceImpl implements StuffService{
	
	private final StuffDao stuffDao;
	
	public StuffServiceImpl(StuffDao stuffDao) {
		this.stuffDao = stuffDao;
	}

	@Override
	public List<Stuff> findAll() {
			return stuffDao.findAll();
	
	}
	
	
	
	@Override
	public Optional<Stuff> getStuff(int id) {
		
		try {
			return stuffDao.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new DataNotFoundException("指定されたデータは存在しません");
		}
		
	}

	@Override
	public void insert(Stuff stuff) {
		
		stuffDao.insert(stuff);
		
	}

	@Override
	public void update(Stuff stuff) {
		
		if(stuffDao.update(stuff) == 0) {
			throw new DataNotFoundException("更新すべきデータが見つかりませんでした");
		}
		
	}

	@Override
	public void deleteById(int id) {
		if(stuffDao.deleteById(id) == 0) {
			throw new DataNotFoundException("削除すべきデータが見つかりませんでした");
		}
		
	}





}
