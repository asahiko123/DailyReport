package com.example.demo.app.repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.DailyReportType;

@Repository
public class DailyReportDaoImpl implements DailyReportDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	public DailyReportDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<DailyReport> findAll() {
		
		String sql = "SELECT DAILYREPORT.id, created, startTime, endTime, detail, stuff_id,"
				+ " progress FROM DAILYREPORT "
				+ " INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.type_id";
		
		
		List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
		
		List<DailyReport> list  = new ArrayList<DailyReport>();
		
		/*DailyReportのentityのsetter,getterを呼び出し、DB情報を詰めなおす*/
		
		for(Map<String,Object> result:resultList) {
			DailyReport dailyReport = new DailyReport();
			dailyReport.setId((int)result.get("id"));
			dailyReport.setTypeId((int)result.get("type_id"));
			dailyReport.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			dailyReport.setStartTime((Time)result.get("startTime"));
			dailyReport.setEndTime((Time)result.get("endTime"));
			dailyReport.setStuffId((int)result.get("stuff_id"));
			
			DailyReportType dailyReportType = new DailyReportType();
			dailyReportType.setId((int)result.get("id"));
			dailyReportType.setProgress((String)result.get("progress"));
			
			dailyReport.setDailyReportType(dailyReportType);
			list.add(dailyReport);
			
		}
		
		return list;
	}

	@Override
	public Optional<DailyReport> getDailyReport(int id) {
		
		String sql = "SELECT DAILYREPORT.id,created,startTime,endTime,detail,stuff_id,"
				+" progress FROM DAILYREPORT"
				+" INNER JOIN DAILYREPORT_TYPE ON DAILYREPORT_TYPE.id = DAILYREPORT.type_id"
				+" WHERE DAILYREPORT.id = ?";
		
		Map<String,Object> result = jdbcTemplate.queryForMap(sql,id);
		
		DailyReport dailyReport = new DailyReport();
		
		dailyReport.setId((int)result.get("id"));
		dailyReport.setTypeId((int)result.get("type_id"));
		dailyReport.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
		dailyReport.setStartTime((Time)result.get("startTime"));
		dailyReport.setEndTime((Time)result.get("endTime"));
		dailyReport.setStuffId((int)result.get("stuff_id"));
		
		DailyReportType dailyReportType = new DailyReportType();
		dailyReportType.setId((int)result.get("id"));
		dailyReportType.setProgress((String)result.get("progress"));
		
	    Optional<DailyReport> reportOpt = Optional.ofNullable(dailyReport);
		
		
		
		return reportOpt;
	}

	@Override
	public void insert(DailyReport dailyReport) {
		jdbcTemplate.update("INSERT INTO DAILYREPORT(type_id,created,startTime,endTime,detail,stuff_id)VALUES(?,?,?,?,?,?)",
							dailyReport.getTypeId(),dailyReport.getCreated(),dailyReport.getStartTime(),dailyReport.getEndTime(),dailyReport.getDetail(),dailyReport.getStuffId());
		
	}

	@Override
	public int update(DailyReport dailyReport) {
		return jdbcTemplate.update("UPDATE DAILYREPORT SET type_id = ? , created = ?, startTime = ? ,endTime = ? ,detail = ? ,stuff_id = ?,WHERE id= ?",
							dailyReport.getTypeId(),dailyReport.getCreated(),dailyReport.getStartTime(),dailyReport.getEndTime(),dailyReport.getDetail(),dailyReport.getStuffId(),dailyReport.getId());
		
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM DAILYREPORT WHERE id = ?",id);
		
	}

}
