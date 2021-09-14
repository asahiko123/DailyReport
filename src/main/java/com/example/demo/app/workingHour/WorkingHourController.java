package com.example.demo.app.workingHour;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.entity.Work;
import com.example.demo.app.entity.WorkingHour;
import com.example.demo.app.service.WorkingHourService;


@Controller
@RequestMapping("/main")
public class WorkingHourController{
	
	private final WorkingHourService workingHourService;


	
	public WorkingHourController(WorkingHourService workingHourService) {
		this.workingHourService = workingHourService;
	}
	
	@GetMapping("/workingHour")
	public String WorkingHour(WorkingHourForm workingHourForm ,Model model) {
		
		
		List<WorkingHour> list=workingHourService.findAll();
		List<Stuff> stuff= workingHourService.findStuff();
		List<Work> work = workingHourService.findWork();
		
		
		model.addAttribute("title","労務管理マスタ");
		model.addAttribute("list",list);
		model.addAttribute("stuff",stuff);
		model.addAttribute("work",work);
		
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
	
	private WorkingHour makeWorkingHour(WorkingHourForm workingHourForm,int workingHourId) {
		
		WorkingHour workingHour= new WorkingHour();
		
		if(workingHourId !=0) {
			workingHour.setId(workingHourId);
		}
		
		workingHour.setStuff_id(workingHourForm.getStuff_id());
		workingHour.setType_id(workingHourForm.getType_id());
		workingHour.setWork_id(workingHourForm.getWork_id());
		
		return workingHour;
	}
	
	private WorkingHourForm makeWorkingHourForm(WorkingHour workingHour) {
		
		WorkingHourForm workingHourForm = new WorkingHourForm();
		
		workingHourForm.setId(workingHour.getId());
		workingHourForm.setStuff_id(workingHour.getStuff_id());
		workingHourForm.setType_id(workingHour.getType_id());
		workingHourForm.setWork_id(workingHour.getWork_id());
		
		return workingHourForm;
	}
}