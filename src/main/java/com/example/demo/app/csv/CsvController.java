package com.example.demo.app.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.service.DailyReportService;
import com.opencsv.CSVWriter;

@Controller
@RequestMapping("/csv")
public class CsvController {
	
	private final DailyReportService dailyReportService;
	private String created;
	private String name;
	private String progress;
	
	public CsvController(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
	}
	
	
	public void writeDataToCsv(String filePath)throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filePath));
		
		String[] entries = "スタッフ名#進捗度#作業区分ID#作業日付#開始時間#終了時間#作業内容".split("#");
		writer.writeNext(entries);
		
		List<DailyReport> list = dailyReportService.findAll();
		
		for(DailyReport obj:list) {
			name    = obj.getName();
			created = obj.getCreated();
		}
		
		
		String[] entries2 = {"W", "Youngstown", "OH"};
		writer.writeNext(entries2);
		
		writer.close();
		
	}
	
	@GetMapping("/download")
	public String CsvDownload(RedirectAttributes redirectAttributes)throws IOException {
		
		writeDataToCsv("C:\\csv\\sample.csv");
		
		redirectAttributes.addFlashAttribute("csv","ファイルを出力しました");
		return "redirect:/main/report";
	}

}
