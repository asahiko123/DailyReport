package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.entity.Supplier;

public interface SupplierDao {
	
	List<Supplier> findAll();
	
	Optional<Supplier> findById(int id);

    void insert(Supplier supplier);
    
    int update(Supplier supplier);
    
    int deleteById(int id);
}
