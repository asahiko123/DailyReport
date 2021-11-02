package com.example.demo.app.repository;


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


@Repository
public class DailyReportDaoImpl implements DailyReportDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public DailyReportDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public List<DailyReport> findAll() {
		
		String sql = "SELECT DISTINCT DAILYREPORT.id, stuff_id, work_id, DAILYREPORT.type_id,stuff_id, created, startTime, endTime,  DAILYREPORT.detail,  DAILYREPORT.name,"
				+ " progress,registeredId,workDivId,STUFF.name FROM DAILYREPORT "
				+ " INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.type_id"
			    +" INNER JOIN STUFF ON STUFF.id = DAILYREPORT.stuff_id"
		        +" INNER JOIN WORK ON WORK.id = DAILYREPORT.work_id";
		     
		
		
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		
		
		
		List<DailyReport> list  = new ArrayList<DailyReport>();
		
		/*DailyReportのentityのsetter,getterを呼び出し、DB情報を詰めなおす*/
		
		for(Map<String,Object> result:resultList) {
			DailyReport dailyReport = new DailyReport();
			dailyReport.setId((int)result.get("id"));
			dailyReport.setStuffId((int)result.get("stuff_id"));
			dailyReport.setWorkId((int)result.get("work_id"));
			dailyReport.setTypeId((int)result.get("type_id"));
           	dailyReport.setCreated(((String)result.get("created")));		
			dailyReport.setStartTime((String)result.get("startTime"));
			dailyReport.setEndTime((String)result.get("endTime"));
			dailyReport.setDetail((String)result.get("detail"));
			dailyReport.setName((String)result.get("name"));

			
			
			DailyReportType dailyReportType = new DailyReportType();
			dailyReportType.setId((int)result.get("type_id"));
			dailyReportType.setProgress((String)result.get("progress"));

			
			Stuff stuff = new Stuff();
			stuff.setRegisteredId((String)result.get("registeredId"));
			stuff.setName((String)result.get("name"));
			
			Work work = new Work();
			work.setWorkDivId((String)result.get("workDivId"));
			
			dailyReport.setDailyReportType(dailyReportType);
			dailyReport.setStuff(stuff);
			dailyReport.setWork(work);
			list.add(dailyReport);
			
		}
		
		return list;
	}

	@Override
	public Optional<DailyReport> getDailyReport(int id) {
		
		String sql = "SELECT DISTINCT DAILYREPORT.id, stuff_id , work_id, DAILYREPORT.type_id, created, startTime, endTime,  DAILYREPORT.detail,  DAILYREPORT.name,"
				+" progress ,registeredId  ,workDivId,STUFF.name  FROM DAILYREPORT"
				+" INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.type_id"
				+" INNER JOIN STUFF ON STUFF.id = DAILYREPORT.stuff_id"
				+" INNER JOIN WORK ON WORK.id = DAILYREPORT.work_id"
				+" WHERE DAILYREPORT.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		
		DailyReport dailyReport = new DailyReport();
		
		dailyReport.setId((int)result.get("id"));
		dailyReport.setTypeId((int)result.get("type_id"));
		dailyReport.setStuffId((int)result.get("stuff_id"));
		dailyReport.setWorkId((int)result.get("work_id"));
        dailyReport.setCreated(((String)result.get("created")));
		dailyReport.setStartTime((String)result.get("startTime"));
		dailyReport.setEndTime((String)result.get("endTime"));
		dailyReport.setDetail((String)result.get("detail"));
		dailyReport.setName((String)result.get("name"));
		
		DailyReportType dailyReportType = new DailyReportType();
		dailyReportType.setId((int)result.get("type_id"));
		dailyReportType.setProgress((String)result.get("progress"));
		
		Stuff stuff = new Stuff();
		stuff.setRegisteredId((String)result.get("registeredId"));
		stuff.setName((String)result.get("name"));
		
		Work work = new Work();
		work.setWorkDivId((String)result.get("workDivId"));
		
	    Optional<DailyReport> reportOpt = Optional.ofNullable(dailyReport);

		return reportOpt;
	}
	
	@Override
	public List<Stuff> findStuff() {
		// TODO 自動生成されたメソッド・スタブ
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
	
	public List<Work> findWork(){
		
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
	public void insert(DailyReport dailyReport) {
		jdbcTemplate.update("INSERT INTO DAILYREPORT(type_id,stuff_id,work_id,created,startTime,endTime,detail,name) VALUES(?,?,?,?,?,?,?,?)",  
				             dailyReport.getTypeId(),dailyReport.getStuffId(),dailyReport.getWorkId(),dailyReport.getCreated(),dailyReport.getStartTime(),dailyReport.getEndTime(),dailyReport.getDetail(),dailyReport.getName());
				             	
	}
	
	

	@Override
	public int update(DailyReport dailyReport) {
		return jdbcTemplate.update("UPDATE DAILYREPORT SET type_id = ? , stuff_id = ?, work_id = ? ,created = ?, startTime = ? ,endTime = ? ,detail = ? ,name = ? WHERE id= ?",
							         dailyReport.getTypeId(),dailyReport.getStuffId(),dailyReport.getWorkId(),dailyReport.getCreated(),dailyReport.getStartTime(),dailyReport.getEndTime(),dailyReport.getDetail(),dailyReport.getName(),dailyReport.getId());	
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM DAILYREPORT WHERE id = ?",id);
		
	}
	
}
