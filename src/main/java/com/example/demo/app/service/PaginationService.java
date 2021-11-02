package com.example.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.repository.PaginationRepository;

@Service
public class PaginationService {
	
	@Autowired
	private PaginationRepository paginationRepository;
	
	public Page<DailyReport> getPages(Pageable pageable){
		return paginationRepository.findAll(pageable);
	}

}
