package com.example.demo.app.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.DailyReport;

@Repository
public interface PaginationRepository extends JpaRepository<DailyReport,Integer> {

	Page<DailyReport> findAll(Pageable pageable);
}
