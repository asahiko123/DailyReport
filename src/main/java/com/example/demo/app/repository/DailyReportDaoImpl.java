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
//import com.example.demo.app.entity.StuffType;



@Repository
public class DailyReportDaoImpl implements DailyReportDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public DailyReportDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<DailyReport> findAll() {
		
		String sql = "SELECT DAILYREPORT.id, stuff_id, work_id, DAILYREPORT.type_id,stuff_id, created, startTime, endTime,  DAILYREPORT.detail,  DAILYREPORT.name,"
				+ " progress  , registeredId ,workDivId FROM DAILYREPORT "
				+ " INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.type_id"
		        //+ " INNER JOIN STUFF_TYPE ON STUFF_TYPE.id = DAILYREPORT.stuff_id";
			    +" INNER JOIN STUFF ON STUFF.id = DAILYREPORT.stuff_id"
		        +" INNER JOIN WORK ON WORK.id = DAILYREPORT.work_id";
		     
		
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		System.out.println(resultList);
		
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
			
//			StuffType stuffType = new StuffType();
//			stuffType.setId((int)result.get("stuff_id"));
//			stuffType.setStuffId((String)result.get("stuffId"));
			
			Stuff stuff = new Stuff();
			stuff.setRegisteredId((String)result.get("registeredId"));
			
			Work work = new Work();
			work.setWorkDivId((String)result.get("workDivId"));
			
			dailyReport.setDailyReportType(dailyReportType);
			dailyReport.setStuff(stuff);
			dailyReport.setWork(work);
//			dailyReport.setStuffType(stuffType);
			list.add(dailyReport);
			
		}
		
		return list;
	}

	@Override
	public Optional<DailyReport> getDailyReport(int id) {
		
		String sql = "SELECT DAILYREPORT.id, stuff_id , work_id, DAILYREPORT.type_id, created, startTime, endTime,  DAILYREPORT.detail,  DAILYREPORT.name,"
				+" progress ,registeredId  ,workDivId FROM DAILYREPORT"
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
		
		Work work = new Work();
		work.setWorkDivId((String)result.get("workDivId"));
		
	    Optional<DailyReport> reportOpt = Optional.ofNullable(dailyReport);
		
		
		
		return reportOpt;
	}

	@Override
	public void insert(DailyReport dailyReport) {
		jdbcTemplate.update("INSERT INTO DAILYREPORT(type_id,stuff_id,work_id,created,startTime,endTime,detail,name)VALUES(?,?,?,?,?,?,?,?)",
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
