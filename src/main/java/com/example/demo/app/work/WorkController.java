package com.example.demo.app.work;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.entity.Work;
import com.example.demo.app.service.WorkService;

@Controller
@RequestMapping("/main")
public class WorkController {

	private final WorkService workService;
	
	public WorkController(WorkService workService) {
		
		this.workService = workService;
	}
	
	@GetMapping("/work")
	public String Work(WorkForm workForm,Model model) {
		
		workForm.setNewWork(true);
		List<Work> list = workService.findAll();
		
		model.addAttribute("title","作業区分マスタ");
		model.addAttribute("name","作業区分");
		model.addAttribute("list",list);
		
		return"WorkForm";
	}
}
