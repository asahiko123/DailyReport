package com.example.demo.app.dailyreport;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.service.DailyReportService;

@Controller
@RequestMapping("/main")
public class DailyReportController {
	
	private final DailyReportService dailyReportService;
	
	public DailyReportController(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
	}
	
	@GetMapping
	public String top(Model model) {
		
		model.addAttribute("title","日報アプリ");
		return"top";
	}
	@GetMapping("/report")
	public String DailyReport(DailyReportForm dailyReportForm,Model model) {
		
		dailyReportForm.setNewReport(true);
		List<DailyReport> list = dailyReportService.findAll();
		
		model.addAttribute("list",list);
		model.addAttribute("title","日報入力");
		return "DailyReportForm";
	}
//	@GetMapping("/stuff")
//	public String Stuff(Model model) {
//		model.addAttribute("title","スタッフマスタ");
//		model.addAttribute("selector","部署名");
//		model.addAttribute("selector2","スタッフ名");
//		return "StuffForm";
//	}
//	@GetMapping("/work")
//	public String Work(Model model) {
//		model.addAttribute("title","作業区分マスタ");
//		model.addAttribute("selector","作業区分");
//		
//		return "WorkForm";
//	}
//	@GetMapping("/supplier")
//	public String Supplier(Model model) {
//		model.addAttribute("title","取引先マスタ");
//		model.addAttribute("selector","会社名");
//	
//		return "SupplierForm";
//	}
	
}
