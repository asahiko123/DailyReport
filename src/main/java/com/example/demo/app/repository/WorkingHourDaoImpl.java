package com.example.demo.app.repository;




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.DailyReportType;
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
	
		String sql = "SELECT DISTINCT WORKING_HOUR.id ,WORKING_HOUR.type_id,WORKING_HOUR.name,WORKING_HOUR.created,WORKING_HOUR.end,WORKING_HOUR.stuff_id,WORKING_HOUR.work_id,workTime,"
				+" registeredId, workDivId ,STUFF.name, DAILYREPORT.name,DAILYREPORT.detail, progress FROM WORKING_HOUR"
				+" INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
				+" INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id"
				+" INNER JOIN DAILYREPORT ON DAILYREPORT.id = WORKING_HOUR.id"
				+" INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.id";
		
		
		
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
			workingHour.setCreated((String) result.get("created"));
			workingHour.setDate((String)result.get("end"));
			workingHour.setWork_id((int)result.get("work_id"));
			workingHour.setWorkTime((String)result.get("workTime"));
			
			
			Stuff stuff = new Stuff();
			stuff.setRegisteredId((String)result.get("registeredId"));
			stuff.setName((String)result.get("name"));
			
			Work work = new Work();
			work.setWorkDivId((String)result.get("workDivId"));
			
			DailyReport dailyReport = new DailyReport();
			dailyReport.setName((String)result.get("name"));
			dailyReport.setDetail((String)result.get("detail"));
			
			DailyReportType dailyReportType = new DailyReportType();
			dailyReportType.setProgress((String)result.get("progress"));
	
			workingHour.setStuff(stuff);
			workingHour.setWork(work);
			workingHour.setDailyReport(dailyReport);
			workingHour.setDailyReportType(dailyReportType);
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
		workingHour.setCreated(((String)result.get("created")));
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
	public List<DailyReport> findDailyReport() {
		
		String sql ="SELECT DAILYREPORT.id,created ,name ,startTime ,endTime FROM DAILYREPORT";


		
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		ArrayList<DailyReport> list = new ArrayList<DailyReport>();
		
		for(Map<String,Object>result:resultList) {
			
			DailyReport dailyReport = new DailyReport();
		
			
			dailyReport.setId((int)result.get("id"));
			dailyReport.setCreated((String)result.get("created"));
			dailyReport.setStartTime((String)result.get("startTime"));
			dailyReport.setEndTime((String)result.get("endTime"));
		
						
			list.add(dailyReport);
		}
		return list;
	}
	
	
	


	@Override
	public void insert(WorkingHour workingHour) {
		jdbcTemplate.update("INSERT INTO WORKING_HOUR(type_id,name,created,end,stuff_id,work_id,workTime)VALUES(?,?,?,?,?,?,?)",
							workingHour.getType_id(),workingHour.getName(),workingHour.getCreated(),workingHour.getEnd(),workingHour.getStuff_id(),workingHour.getWork_id(),workingHour.getWorkTime());
		
	}

	@Override
	public int update(WorkingHour workingHour) {
		return	jdbcTemplate.update("UPDATE WORKING_HOUR SET type_id=?, name =?,created =?,date =? ,stuff_id=? ,work_id=? ,workTime =? WHERE id=?",
				            workingHour.getType_id(),workingHour.getName(),workingHour.getCreated(),workingHour.getDate(),workingHour.getStuff_id(),workingHour.getWork_id(),workingHour.getWorkTime(),workingHour.getId());
		
	}

	@Override
	public int deleteById(int id) {	
		return jdbcTemplate.update("DELETE FROM WORKING_HOUR WHERE id=?",id);
	}

	@Override
	public List<WorkingHour> search(WorkingHour workingHour) {
		
	String sql ="SELECT DISTINCT DAILYREPORT.id, stuff_id, work_id, DAILYREPORT.type_id,stuff_id, created, startTime, endTime,  DAILYREPORT.detail,  DAILYREPORT.name,"
			+ "progress , registeredId ,workDivId FROM DAILYREPORT"
			+ " INNER JOIN STUFF ON STUFF.id = WORKING_HOUR.stuff_id"
			+ " INNER JOIN WORK ON WORK.id = WORKING_HOUR.work_id"
			+" BETWEEN \'" +workingHour.getCreated()  + "\' AND \'" + workingHour.getDate() + "\'";
	
	List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
	
	ArrayList<WorkingHour> list = new ArrayList<WorkingHour>();
	
	for(Map<String,Object>result:resultList) {
		
		Stuff stuff = new Stuff();		
		stuff.setRegisteredId((String)result.get("registeredId"));
		
		Work work = new Work();		
		work.setWorkDivId((String)result.get("workDivId"));
		
		WorkingHour workinst = new WorkingHour();	
		workinst.setCreated((String)result.get("created"));
		
		workinst.setStuff(stuff);
		workinst.setWork(work);
		
		list.add(workinst);
				
	}
		
		return list;
	}

	




}
