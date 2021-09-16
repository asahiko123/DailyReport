package com.example.demo.app.repository;




import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;

@Repository
public class WorkingHourDaoImpl implements WorkingHourDao{
	
private final JdbcTemplate jdbcTemplate;
	
	public WorkingHourDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<WorkingHour> findAll() {
		
		String sql = "SELECT DISTINCT WORKING_HOUR.id ,WORKING_HOUR.type_id,WORKING_HOUR.name,created,stuff_id,work_id,workTime,"
				+" registeredId, workDivId FROM WORKING_HOUR"
				+" INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
				+" INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id";
		
		List<Map<String,Object>> resultList =jdbcTemplate.queryForList(sql);
		System.out.println(resultList);
		
		List<WorkingHour> list = new ArrayList<WorkingHour>();
		
		/*WorkingHourのentityのsetter,getterを呼び出しDB情報を詰めなおす*/
		
		for(Map<String,Object> result:resultList) {
			WorkingHour workingHour = new WorkingHour();
			
			workingHour.setId((int)result.get("id"));
			workingHour.setStuff_id((int)result.get("stuff_id"));
			workingHour.setType_id((int)result.get("type_id"));
			workingHour.setName((String) result.get("name"));
			workingHour.setCreated(((Timestamp) result.get("created")).toLocalDateTime().toLocalDate());
			workingHour.setWork_id((int)result.get("work_id"));
			workingHour.setWorkTime((String)result.get("workTime"));
			
			
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
	public Optional<WorkingHour> getWorkingHour(int id){
		
		String sql ="SELECT DISTINCT WORKING_HOUR.id ,WORKING_HOUR.type_id,WORKING_HOUR.name,created,stuff_id,work_id,workTime,"
				+ " registeredId, workDivId FROM WORKING_HOUR"
				+ " INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
				+ " INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id"
		        + " WHERE WORKING_HOUR.id =?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		
		WorkingHour workingHour =new WorkingHour();
		
		workingHour.setId((int)result.get("id"));
		workingHour.setStuff_id((int)result.get("stuff_id"));
		workingHour.setType_id((int)result.get("type_id"));
		workingHour.setName((String)result.get("name"));
		workingHour.setCreated(((LocalDate)result.get("created")));
		workingHour.setWork_id((int)result.get("work_id"));
		workingHour.setWorkTime((String)result.get("workTime"));
		
		Stuff stuff = new Stuff();
		stuff.setRegisteredId((String)result.get("registeredId"));
		
		Work work = new Work();
		work.setWorkDivId((String)result.get("workDivId"));
		
		Optional<WorkingHour> workingOpt = Optional.ofNullable(workingHour);
		
		
		return workingOpt;
	}

	@Override
	public List<Stuff> findStuff() {
		String sql ="SELECT STUFF.id,type_id,name,detail,registeredId FROM STUFF ";
		
		List<Map<String,Object>>resultList = jdbcTemplate.queryForList(sql);
		List<Stuff> list = new ArrayList<Stuff>();
		
		for(Map<String,Object>result :resultList){
		Stuff stuff = new Stuff();
		stuff.setId((int)result.get("id"));
		stuff.setName((String)result.get("name"));
		stuff.setRegisteredId((String)result.get("registeredId"));
		stuff.setDetail((String)result.get("detail"));
		
		
		list.add(stuff);
		}
		return list;
	}

	@Override
	public List<Work> findWork() {
		String sql ="SELECT WORK.id ,workDivId FROM WORK";
		
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		ArrayList<Work> list = new ArrayList<Work>();
		
		for(Map<String,Object>result:resultList) {
			Work work = new Work();
			work.setId((int)result.get("id"));
			work.setWorkDivId((String)result.get("workDivId"));
			
			list.add(work);
			
		}
		return list;
	}


	@Override
	public void insert(WorkingHour workingHour) {
		jdbcTemplate.update("INSERT INTO WORKING_HOUR(type_id,name,created,stuff_id,work_id,workTime)VALUES(?,?,?,?,?,?)",
							workingHour.getType_id(),workingHour.getName(),workingHour.getCreated(),workingHour.getStuff_id(),workingHour.getWork_id(),workingHour.getWorkTime());
		
	}

	@Override
	public int update(WorkingHour workingHour) {
		return	jdbcTemplate.update("UPDATE WORKING_HOUR SET type_id=?, name =?,created =?,stuff_id=? ,work_id=? ,workTime =? WHERE id=?",
				            workingHour.getType_id(),workingHour.getName(),workingHour.getCreated(),workingHour.getStuff_id(),workingHour.getWork_id(),workingHour.getWorkTime(),workingHour.getId());
		
	}

	@Override
	public int deleteById(int id) {	
		return jdbcTemplate.update("DELETE FROM WORKING_HOUR WHERE id=?",id);
	}

	@Override
	public List<WorkingHour> search() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
