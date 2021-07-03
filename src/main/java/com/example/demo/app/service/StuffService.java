package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Stuff;

public interface StuffService {
	
	List<Stuff> findAll();
	
	
	Optional<Stuff> getStuff(int id);
	
	void insert(Stuff stuff);
	
	void update(Stuff stuff);
	
	void deleteById(int id);

}
