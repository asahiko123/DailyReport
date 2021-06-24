package com.example.demo.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.Stuff;
//import com.example.demo.app.entity.StuffType;
@Repository
public class StuffDaoImpl implements StuffDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public StuffDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}

	@Override
	public List<Stuff> findAll() {
		
		String sql ="SELECT STUFF.id, type_id , name , detail, registeredId  FROM STUFF ";
					//+" INNER JOIN STUFF_TYPE ON STUFF.type_id = STUFF_TYPE.id";
		
		List<Map<String,Object>>resultList = jdbcTemplate.queryForList(sql);
		System.out.println(resultList);
		
		List<Stuff> list = new ArrayList<Stuff>();
		
		for(Map<String,Object>result:resultList) {
			
			Stuff stuff = new Stuff();
			stuff.setId((int)result.get("id"));
			stuff.setTypeId((int)result.get("type_id"));
			stuff.setName((String)result.get("name"));
			stuff.setDetail((String)result.get("detail"));
			stuff.setRegisteredId((String)result.get("registeredId"));
			
			
//			StuffType stuffType = new StuffType();
//			stuffType.setId((int)result.get("type_id"));
//			stuffType.setStuffId((String)result.get("stuffId"));
//			
//			stuff.setStuffType(stuffType);
            list.add(stuff);
			
		}
		return list;
	}

	@Override
	public Optional<Stuff> findById(int id) {
		
		String sql ="SELECT STUFF.id, type_id, name, detail, registeredId  FROM STUFF"
//				+" INNER JOIN STUFF_TYPE ON STUFF.type_id = STUFF_TYPE.id"
				+" WHERE STUFF.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
	
		
		Stuff stuff = new Stuff();
		stuff.setId((int)result.get("id"));
		stuff.setTypeId((int)result.get("type_id"));
		stuff.setName((String)result.get("name"));
		stuff.setDetail((String)result.get("detail"));
		stuff.setRegisteredId((String)result.get("registeredId"));
		
//		StuffType stuffType = new StuffType();
//		stuffType.setId((int)result.get("type_id"));
//		stuffType.setStuffId((String)result.get("stuffId"));
		
		Optional<Stuff> stuffOpt = Optional.ofNullable(stuff);
		
		return stuffOpt;
	}

	@Override
	public void insert(Stuff stuff) {
		jdbcTemplate.update("INSERT INTO STUFF( type_id, name, detail, registeredId) VALUES(?,?,?,?)",
							stuff.getTypeId(),stuff.getName(),stuff.getDetail(),stuff.getRegisteredId());
		
	}

	@Override
	public int update(Stuff stuff) {
		return jdbcTemplate.update("UPDATE STUFF SET type_id = ?,name = ? ,detail = ? ,registeredId = ? WHERE id = ?",
				                    stuff.getTypeId(),stuff.getName(),stuff.getDetail(),stuff.getRegisteredId(),stuff.getId());
		
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM STUFF WHERE id = ?",id);
	}

}
