package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Supplier;

public interface SupplierService {
	
	List<Supplier> findAll();
	
	Optional<Supplier> getSupplier(int id);
	
	void insert(Supplier supplier);
	
	void update(Supplier supplier);
	
	void deleteById(int id);

}
