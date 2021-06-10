package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.Supplier;
import com.example.demo.app.repository.SupplierDao;
@Service
public class SupplierServiceImpl implements SupplierService{
	
	private final SupplierDao supplierDao;
	
	public SupplierServiceImpl(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	@Override
	public List<Supplier> findAll() {
		
		return supplierDao.findAll();
	}

	@Override
	public Optional<Supplier> getSupplier(int id) {
		
		try {
			return supplierDao.findById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new DataNotFoundException("指定されたデータは見つかりませんでした");
		}
		
	}

	@Override
	public void insert(Supplier supplier) {
		
		supplierDao.insert(supplier);
		
	}

	@Override
	public void update(Supplier supplier) {
		
		if(supplierDao.update(supplier) == 0) {
			throw new DataNotFoundException("更新すべきデータが見つかりませんでした");
		}
		
	}

	@Override
	public void deleteById(int id) {
		if(supplierDao.deleteById(id) == 0) {
			throw new DataNotFoundException("削除すべきデータが見つかりませんした");
		}
		
	}



}
