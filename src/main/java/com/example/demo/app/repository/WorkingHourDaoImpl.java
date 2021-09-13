package com.example.demo.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;

public class WorkingHourDaoImpl implements WorkingHourDao{
	
private final JdbcTemplate jdbcTemplate;
	
	public WorkingHourDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<WorkingHour> findAll() {
		
		String sql ="SELECT DISTINCT WORKING_HOUR.id ,WORKING_HOUR.type_id,stuff_id,work_id,workTime"
				+ " registeredId, workDivId FROM WORKING_HOUR"
				+ " INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
				+ " INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id";
		
		List<Map<String,Object>> resultList =jdbcTemplate.queryForList(sql);
		
		List<WorkingHour> list = new ArrayList<WorkingHour>();
		
		/*WorkingHourのentityのsetter,getterを呼び出しDB情報を詰めなおす*/
		
		for(Map<String,Object> result:resultList) {
			WorkingHour workingHour = new WorkingHour();
			
			workingHour.setId((int)result.get("id"));
			workingHour.setStuff_id((int)result.get("stuff_id"));
			workingHour.setType_id((int)result.get("type_id"));
			workingHour.setWork_id((int)result.get("work_id"));
			workingHour.setWorkTime((int)result.get("workTime"));
			
			Stuff stuff = new Stuff();
			stuff.setRegisteredId((String)result.get("registeredId"));
			
			Work work = new Work();
			work.setWorkDivId((String)result.get("workDivId"));
			
			workingHour.setStuff(stuff);
			workingHour.setWork(work);
			
			list.add(workingHour);
			
		}
		
		return list;
	}
	
	@Override
	public Optional<WorkingHour> getDailyReport(int id) {
		
		String sql ="SELECT DISTINCT WORKING_HOUR.id ,WORKING_HOUR.type_id,stuff_id,work_id,workTime"
				+ " registeredId, workDivId FROM WORKING_HOUR"
				+ " INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
				+ " INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id"
		        + " WHERE WORKING_HOUR.id =?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		
		WorkingHour workingHour =new WorkingHour();
		
		workingHour.setId((int)result.get("id"));
		workingHour.setStuff_id((int)result.get("stuff_id"));
		workingHour.setType_id((int)result.get("type_id"));
		workingHour.setWork_id((int)result.get("work_id"));
		workingHour.setWorkTime((int)result.get("workTime"));
		
		Stuff stuff = new Stuff();
		stuff.setRegisteredId((String)result.get("registeredId"));
		
		Work work = new Work();
		work.setWorkDivId((String)result.get("workDivId"));
		
		Optional<WorkingHour> workingOpt = Optional.ofNullable(workingHour);
		
		
		return workingOpt;
	}

	@Override
	public List<Stuff> findStuff() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Work> findWork() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public void insert(WorkingHour workingHour) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public int update(WorkingHour workingHour) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public List<WorkingHour> search() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
