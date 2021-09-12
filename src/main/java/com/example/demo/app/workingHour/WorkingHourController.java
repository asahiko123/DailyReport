package com.example.demo.app.workingHour;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main")
public class WorkingHourController{
	
	public WorkingHourController() {
		
	}
	
	@GetMapping("/workingHour")
	public String WorkingHour(Model model) {
		
		model.addAttribute("title","労務管理マスタ");
		return "WorkingHourForm";
	}
}