package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Work;

public interface WorkService {
	
	List<Work> findAll();
	
	Optional<Work> getWork(int id);
	
	void insert(Work work);
	
	void update(Work work);
	
	void deleteById(int id);

}
