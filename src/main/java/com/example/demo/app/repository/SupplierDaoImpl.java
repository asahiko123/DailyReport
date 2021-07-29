package com.example.demo.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Supplier> findAll() {
		
		String sql = "SELECT SUPPLIER.id,type_id,supplier,comment FROM SUPPLIER";
				
		
		List<Map<String,Object>>resultList = jdbcTemplate.queryForList(sql);
		
		List<Supplier> list = new ArrayList<Supplier>();
		
		System.out.println(resultList);
		
		for(Map<String,Object> result:resultList) {
			
			Supplier supplier = new Supplier();
			supplier.setId((int)result.get("id"));
			supplier.setSupplier((String)result.get("supplier"));
			supplier.setComment((String)result.get("comment"));
			
			list.add(supplier);
		}
		
		return list;
	}

	@Override
	public Optional<Supplier> findById(int id) {
		
		String sql = "SELECT SUPPLIER.id, type_id, supplier, comment FROM SUPPLIER"
				+" WHERE SUPPLIER.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		System.out.println(result);
	
		Supplier supplier  = new Supplier();
		supplier.setId((int)result.get("id"));
	    supplier.setTypeId((int)result.get("type_id"));
	    supplier.setSupplier((String)result.get("supplier"));
	    supplier.setComment((String)result.get("comment"));
	    

	    Optional<Supplier> supOpt = Optional.ofNullable(supplier);
		
		return supOpt;
	}

	@Override
	public void insert(Supplier supplier) {
		jdbcTemplate.update("INSERT INTO SUPPLIER( type_id,supplier,comment) VALUES(?,?,?)",
							supplier.getTypeId(),supplier.getSupplier(),supplier.getComment());
	}

	@Override
	public int update(Supplier supplier) {
		return jdbcTemplate.update("UPDATE SUPPLIER SET type_id = ?,supplier = ?,comment = ? WHERE id = ?",
									supplier.getTypeId(),supplier.getSupplier(),supplier.getComment(),supplier.getId());
	}

	@Override
	public int deleteById(int id) {
		
		return jdbcTemplate.update("DELETE FROM SUPPLIER WHERE id = ?",id);
	}

}
