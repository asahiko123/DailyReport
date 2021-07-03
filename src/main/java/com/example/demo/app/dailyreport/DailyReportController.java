package com.example.demo.app.dailyreport;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
//import com.example.demo.app.entity.Stuff;
import com.example.demo.app.service.DailyReportService;
import com.example.demo.app.stuff.StuffForm;
import com.example.demo.app.work.WorkForm;
//import com.example.demo.app.service.StuffService;

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
		List<Stuff> stuff = dailyReportService.findStuff();
		List<Work> work = dailyReportService.findWork();
		
		
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("title","日報入力");
	
		
		return "DailyReportForm";
	}
	
	
	@GetMapping("/report/{id}")
	public String showUpdate(
			@PathVariable int id,
			DailyReportForm dailyReportForm,
			Model model) {
		
		Optional<DailyReport>dailyReportOpt = dailyReportService.getDailyReport(id);
		Optional<DailyReportForm> dailyReportFormOpt = dailyReportOpt.map(t -> makeDailyReportForm(t));
		
		if(dailyReportOpt.isPresent()) {
			
			dailyReportForm  = dailyReportFormOpt.get();
		}
		
		
		model.addAttribute("title","日報更新フォーム");
		model.addAttribute("DailyReportForm",dailyReportForm);
		List<DailyReport> list = dailyReportService.findAll();
		List<Stuff> stuff = dailyReportService.findStuff();
		List<Work> work = dailyReportService.findWork();
		
		
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("dailyReportId",id);
		
		
		
		return "DailyReportForm";
		
	}
	
	@PostMapping("/report/insert")
	public String insert(
			@Valid@ModelAttribute DailyReportForm dailyReportForm,
			BindingResult result,
			StuffForm stuffForm,
			WorkForm workForm,
			Model model) {
		
		
		DailyReport dailyReport  = makeDailyReport(dailyReportForm,0);
		
		System.out.println(dailyReportForm.getStuffId());
		
		
		
		if(!result.hasErrors()) {
			dailyReportService.insert(dailyReport);
//			dailyReportService.insert(makestuff);
//			dailyReportService.insert(makeWork);
		
			return "redirect:/main/report";
		}else {
			dailyReportForm.setNewReport(true);
			model.addAttribute("DailyReportForm",dailyReportForm);
		
			List<DailyReport> list = dailyReportService.findAll();
			List<Stuff> stuff = dailyReportService.findStuff();
			List<Work> work = dailyReportService.findWork();
			
			model.addAttribute("stuff",stuff);
			model.addAttribute("work",work);
			model.addAttribute("list",list);
			model.addAttribute("title","日報入力");
			
			return"DailyReportForm";
		}
		
	}
	
	@PostMapping("/report/update")
	public String update(
			@Valid@ModelAttribute DailyReportForm dailyReportForm,
			BindingResult result,
			@RequestParam("dailyReportId")int dailyReportId,
			@RequestParam("stuffId")int stuffId,
			StuffForm stuffForm,
			WorkForm workForm,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		
		if(!result.hasErrors()) {
			DailyReport dailyReport = makeDailyReport(dailyReportForm,dailyReportId);
			

			dailyReportService.update(dailyReport);
			
			redirectAttributes.addFlashAttribute("complete","変更しました");
			return "redirect:/main/report/"+dailyReportId;
			
		}else {
			model.addAttribute("dailyReportForm",dailyReportForm);
			model.addAttribute("title","日報更新フォーム");
		
			
			return"DailyReportForm";
		}
		
	}
	
	@PostMapping("/report/delete")
	public String delete(
			Model model,
			@RequestParam("dailyReportId")int id
			) {
		
		dailyReportService.deleteById(id);
		return"redirect:/main/report";
		
	}
	
	private DailyReport makeDailyReport(DailyReportForm dailyReportForm,int dailyReportId) {
	
		DailyReport dailyReport = new DailyReport();
		
		if(dailyReportId != 0) {
			dailyReport.setId(dailyReportId);
		}
		
		dailyReport.setStartTime(dailyReportForm.getStartTime());
		dailyReport.setEndTime(dailyReportForm.getEndTime());
       	dailyReport.setCreated(dailyReportForm.getCreated());	
		dailyReport.setDetail(dailyReportForm.getDetail());
		dailyReport.setName(dailyReportForm.getName());
		dailyReport.setTypeId(dailyReportForm.getTypeId());
		dailyReport.setStuffId(dailyReportForm.getStuffId());
		dailyReport.setWorkId(dailyReportForm.getWorkId());
		dailyReport.setRegisteredId(dailyReportForm.getRegisteredId());
	
		
		return dailyReport;
	}
	
	private DailyReportForm makeDailyReportForm(DailyReport dailyReport) {
		
		DailyReportForm dailyReportForm = new DailyReportForm();
		
		dailyReportForm.setStartTime(dailyReport.getStartTime());
		dailyReportForm.setEndTime(dailyReport.getEndTime());
		dailyReportForm.setCreated(dailyReport.getCreated());
	    dailyReportForm.setName(dailyReport.getName());
		dailyReportForm.setDetail(dailyReport.getDetail());
		dailyReportForm.setTypeId(dailyReport.getTypeId());
		dailyReportForm.setStuffId(dailyReport.getStuffId());
		dailyReportForm.setWorkId(dailyReport.getWorkId());
		
		
		dailyReportForm.setNewReport(false);
		
		return dailyReportForm;
	}
	
//	private Stuff makeStuff(StuffForm stuffForm,int stuffId) {
//		
//		Stuff stuff  = new Stuff();
//		if(stuffId == 0) {
//			stuff.setId(stuffId);
//		}
//		
//		stuff.setRegisteredId(stuffForm.getRegisteredId());
//	
//		
//		return stuff;
//		
//		
//		
//	}
//	
//	private Work makeWork(WorkForm workForm,int workId) {
//		Work work  = new Work();
//		if(workId == 0) {
//			work.setId(workId);
//		}
//		work.setId(workForm.getId());
//		work.setWorkDivId(workForm.getWorkDivId());
//		
//		return work;
//	}
	

}
