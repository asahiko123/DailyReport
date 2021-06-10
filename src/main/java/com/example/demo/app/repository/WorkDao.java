package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Work;

public interface WorkDao {
	
	List<Work> findAll();
	
	Optional<Work> findById(int id);
	
	void insert(Work work);
	
	int update(Work work);
	
	int deleteById(int id);

}
