package com.example.demo.app.workingHour;

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

import com.example.demo.app.dailyreport.DailyReportForm;
import com.example.demo.app.entity.DailyReport;
import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;
import com.example.demo.app.service.DailyReportService;
import com.example.demo.app.service.WorkingHourService;
import com.example.demo.app.stuff.StuffForm;
import com.example.demo.app.work.WorkForm;


@Controller
@RequestMapping("/main")
public class WorkingHourController{
	
	private final WorkingHourService workingHourService;
	private final DailyReportService dailyReportService;


	
	public WorkingHourController(WorkingHourService workingHourService,DailyReportService dailyReportService) {
		this.workingHourService = workingHourService;
		this.dailyReportService = dailyReportService;
	}
	
	
	@GetMapping("/workingHour")
	public String WorkingHour(WorkingHourForm workingHourForm ,Model model) {
		
		
		System.out.println(workingHourForm.isNewHour());
		List<WorkingHour> list=workingHourService.findAll();
		List<Stuff> stuff= workingHourService.findStuff();
		List<Work> work = workingHourService.findWork();
		List<DailyReport> dailyReport = dailyReportService.findAll();
		

		
		model.addAttribute("title","労務管理マスタ");
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("dailyReport",dailyReport);
	
		
		return "WorkingHourForm";
	}
	
	
	@GetMapping("/workingHour/{id}")
	public String showUpdate(
			@PathVariable int id,
			WorkingHourForm workingHourForm,
			Model model) {
		
		Optional<WorkingHour>workingHourOpt = workingHourService.getWorkingHour(id);
		Optional<WorkingHourForm> workingHourFormOpt = workingHourOpt.map(t -> makeWorkingHourForm(t));
		
		if(workingHourOpt.isPresent()) {
			
			workingHourForm = workingHourFormOpt.get();
		}
		
		model.addAttribute("title","労働時間管理フォーム");
		model.addAttribute("WorkingHourForm",workingHourForm);
		List<WorkingHour> list = workingHourService.findAll();
		List<Stuff> stuff = workingHourService.findStuff();
		List<Work> work = workingHourService.findWork();
		
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		model.addAttribute("workingHourId",id);
		
		return "workingHourForm";
	}
	
	@PostMapping("/workingHour/search")
	public String search(
			@Valid@ModelAttribute WorkingHourForm workingHourForm,
			BindingResult result,
			StuffForm stuffForm,
			WorkForm workForm,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		WorkingHour workingHour = makeWorkingHour(workingHourForm,0);
		
		if(!result.hasErrors()) {
			
	 
		  workingHourService.insert(workingHour);	  
		  List<WorkingHour> search = workingHourService.search(workingHour);
		  List<Work> work = workingHourService.findWork();	  
		  WorkingHour sum = workingHourService.sum(workingHour);

			 
		  int size = search.size();
		  
		  redirectAttributes.addFlashAttribute("search",search);
		  redirectAttributes.addFlashAttribute("work",work);
		  redirectAttributes.addFlashAttribute("sum",sum);
		  redirectAttributes.addFlashAttribute("size",size);
		  
		  
		return "redirect:/main/workingHour";
		
		}else {
			workingHourForm.setNewHour(true);
			
			
			model.addAttribute("WorkingHourForm",workingHourForm);
			
			List<WorkingHour> list = workingHourService.findAll();
			List<Stuff> stuff      = workingHourService.findStuff();
			List<Work> work        = workingHourService.findWork();
			
			model.addAttribute("stuff",stuff);
			model.addAttribute("work",work);
			model.addAttribute("list",list);
			model.addAttribute("title","労務管理マスタ");
			
			return"WorkingHourForm";
		}
	}
	
	@PostMapping("/workingHour/update")
	public String update(
			@Valid@ModelAttribute WorkingHourForm workingHourForm,
			BindingResult result,
			@RequestParam("workingHourId")int workingHourId,
			StuffForm stuffForm,
			WorkForm workForm,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()) {
			WorkingHour workingHour = makeWorkingHour(workingHourForm,workingHourId);
			
			workingHourService.update(workingHour);
			
			redirectAttributes.addFlashAttribute("complete","変更しました");
			return "redirect:/main/workingHour/"+workingHourId;
		}else {
			model.addAttribute("workingHourForm",workingHourForm);
			model.addAttribute("title","労務管理マスタ");
			
			return "WorkingHourForm";
		}
		
	}
	@PostMapping("/workingHour/delete")
		public String delete(
				Model model,
				WorkingHour workingHour,
				@RequestParam("workingHourId")int id) {
			
		workingHourService.deleteById(id);
		return "redirect:/main/workingHour";
	}
	
	
	
	private WorkingHour makeWorkingHour(WorkingHourForm workingHourForm,int workingHourId) {
		
		WorkingHour workingHour= new WorkingHour();
		
		if(workingHourId !=0) {
			workingHour.setId(workingHourId);
		}
		
		workingHour.setStuff_id(workingHourForm.getStuff_id());
		workingHour.setType_id(workingHourForm.getType_id());
		workingHour.setWork_id(workingHourForm.getWork_id());
		workingHour.setCreated(workingHourForm.getCreated());
	    workingHour.setEnd(workingHourForm.getEnd());
//		workingHour.setWorkTime(workingHourForm.getWorkTime());
		
		return workingHour;
	}
	
	private WorkingHourForm makeWorkingHourForm(WorkingHour workingHour) {
		
		WorkingHourForm workingHourForm = new WorkingHourForm();
		
		workingHourForm.setId(workingHour.getId());
		workingHourForm.setStuff_id(workingHour.getStuff_id());
		workingHourForm.setType_id(workingHour.getType_id());
		workingHourForm.setWork_id(workingHour.getWork_id());
		workingHourForm.setCreated(workingHour.getCreated());
		workingHourForm.setEnd(workingHour.getEnd());
//		workingHourForm.setWorkTime(workingHour.getWorkTime());
		
		workingHourForm.setNewHour(false);
		
		return workingHourForm;
	}
	
	
}