package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Stuff;

public interface StuffDao {
	
	List<Stuff> findAll();
	
	Optional<Stuff> findById(int id);
	
	void insert(Stuff stuff);
	
	int update(Stuff stuff);
	
	int deleteById(int id);

}
