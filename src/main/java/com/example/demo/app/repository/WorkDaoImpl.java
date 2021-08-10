
package com.example.demo.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkType;
@Repository
public class WorkDaoImpl implements WorkDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public WorkDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Work> findAll() {
		
		String sql = "SELECT DISTINCT WORK.id,WORK.type_id,comment,workDivId,type FROM WORK"
				   +" INNER JOIN WORK_TYPE ON WORK.type_id = WORK_TYPE.id";
				   
		List<Map<String,Object>>resultList = jdbcTemplate.queryForList(sql);
		System.out.println(resultList);
		
		List<Work> list = new ArrayList<Work>();
		
		for(Map<String,Object>result:resultList) {
			
			Work work = new Work();
			work.setId((int)result.get("id"));
			work.setComment((String)result.get("comment"));
			work.setTypeId((int)result.get("type_id"));
			work.setWorkDivId((String)result.get("workDivId"));
			
			WorkType workType = new WorkType();
			workType.setId((int)result.get("type_id"));
			workType.setType((String)result.get("type"));
			
			work.setWorkType(workType);
			list.add(work);
		}
		
		return list;
	}

	@Override
	public Optional<Work> findById(int id) {
		
		String sql = "SELECT DISTINCT WORK.id, WORK.type_id, comment,workDivId, type FROM WORK"
				   +" INNER JOIN WORK_TYPE ON WORK.type_id = WORK_TYPE.id"
				   +" WHERE WORK.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		
		Work work = new Work();
		work.setId((int)result.get("id"));
		work.setTypeId((int)result.get("type_id"));
		work.setComment((String)result.get("comment"));
		work.setWorkDivId((String)result.get("workDivId"));
		
		WorkType workType = new WorkType();
		workType.setId((int)result.get("type_id"));
		workType.setType((String)result.get("type"));
	
		
		Optional<Work> workOpt = Optional.ofNullable(work);
		
		return workOpt;
	}

	@Override
	public void insert(Work work) {
		jdbcTemplate.update("INSERT INTO WORK(type_id,comment,workDivId)VALUES(?,?,?)",
				            work.getTypeId(),work.getComment(),work.getWorkDivId());
	}
	
	

	@Override
	public int update(Work work) {
		return jdbcTemplate.update("UPDATE WORK SET type_id = ?, comment = ? , workDivId = ? WHERE id =?",
				                    work.getTypeId(),work.getComment(),work.getWorkDivId(),work.getId());
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM WORK WHERE id = ?",id);
	}

}
