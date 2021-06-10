package com.example.demo.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.Supplier;
import com.example.demo.app.entity.SupplierType;
@Repository
public class SupplierDaoImpl implements SupplierDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Supplier> findAll() {
		
		String sql = "SELECT SUPPLIER.id,type_id,name," 
				+" type FROM SUPPLIER"
				+" INNER JOIN SUPPLIER_TYPE ON SUPPLIER.type_id = SUPPLIER_TYPE.id";
				
		
		List<Map<String,Object>>resultList = jdbcTemplate.queryForList(sql);
		
		List<Supplier> list = new ArrayList<Supplier>();
		
		for(Map<String,Object> result:resultList) {
			
			Supplier supplier = new Supplier();
			supplier.setId((int)result.get("id"));
			supplier.setName((String)result.get("name"));
			
			SupplierType supplierType  = new SupplierType();
			supplierType.setId((int)result.get("id"));
			supplierType.setType((String)result.get("type"));
			
			supplier.setSupplierType(supplierType);
			list.add(supplier);
		}
		
		return list;
	}

	@Override
	public Optional<Supplier> findById(int id) {
		
		String sql = "SELECT SUPPLIER.id, type_id, name," 
				+ "type FROM SUPPLIER"
				+ "INNER JOIN SUPPLIER_TYPE ON SUPPLIER.type_id = SUPPLIER_TYPE.id"
				+ "WHERE SUPPLIER.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
	
		Supplier supplier  = new Supplier();
		supplier.setId((int)result.get("id"));
	    supplier.setTypeId((int)result.get("type_id"));
	    supplier.setName((String)result.get("name"));
	    
	    SupplierType supplierType = new SupplierType();
	    supplierType.setId((int)result.get("id"));
	    supplierType.setType((String)result.get("type"));
	    
	    supplier.setSupplierType(supplierType);
	    
	    Optional<Supplier> supOpt = Optional.ofNullable(supplier);
		
		return supOpt;
	}

	@Override
	public void insert(Supplier supplier) {
		jdbcTemplate.update("INSERT INTO SUPPLIER(type_id,name)VALUES(?,?)",
							supplier.getTypeId(),supplier.getName());
	}

	@Override
	public int update(Supplier supplier) {
		
		return jdbcTemplate.update("UPDATE SUPPLIER SET type_id = ?,name = ? WHERE id = ?",
									supplier.getTypeId(),supplier.getName());
	}

	@Override
	public int deleteById(int id) {
		
		return jdbcTemplate.update("DELETE FROM SUPPLIER WHERE id = ?");
	}

}
