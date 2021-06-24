package com.example.demo.app.stuff;

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

import com.example.demo.app.entity.Stuff;
import com.example.demo.app.service.StuffService;

@Controller
@RequestMapping("/main")
public class StuffController {

	private final StuffService stuffService;
	
	public StuffController(StuffService stuffService) {
		this.stuffService = stuffService;
	}
	
	@GetMapping("/stuff")
	public String Stuff(StuffForm stuffForm,Model model) {
		
		stuffForm.setNewStuff(true);
		
		List<Stuff> list = stuffService.findAll();
		
		model.addAttribute("title","スタッフマスタ");
		model.addAttribute("name","氏名");
		model.addAttribute("list",list);
		
		return"StuffForm";
	}
	@GetMapping("/stuff/{id}")
	public String showUpdate(
			StuffForm stuffForm,
			@PathVariable int id,
			Model model
			) {
		
		Optional<Stuff> stuffOpt = stuffService.getStuff(id);
		Optional<StuffForm> stuffFormOpt = stuffOpt.map(t -> makeStuffForm(t));
		
		if(stuffFormOpt.isPresent()) {
			stuffForm = stuffFormOpt.get();
		}
		model.addAttribute("stuffForm",stuffForm);
		model.addAttribute("title","スタッフマスタ更新フォーム");
		List<Stuff> list = stuffService.findAll();
		model.addAttribute("list",list);
		model.addAttribute("stuffId",id);
		model.addAttribute("name","氏名");
		
		
		return "StuffForm";
		
	}
	
	@PostMapping("/stuff/insert")
	public String insert(
			@Valid@ModelAttribute StuffForm stuffForm,
			BindingResult result,
			Model model	
			) {
		
		Stuff stuff = makeStuff(stuffForm,0);
		
		if(!result.hasErrors()) {
			stuffService.insert(stuff);
			return "redirect:/main/stuff";
		}else {
			stuffForm.setNewStuff(true);
			model.addAttribute("StuffForm",stuffForm);
			List<Stuff> list = stuffService.findAll();
			model.addAttribute("list",list);
			model.addAttribute("title","スタッフマスタ");
			
			return "StuffForm";
		}
		
	}
	
	@PostMapping("/stuff/update")
	public String update(
			@Valid@ModelAttribute StuffForm stuffForm,
			BindingResult result,
			@RequestParam("stuffId")int stuffId,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()) {
			Stuff stuff = makeStuff(stuffForm,stuffId);
			stuffService.update(stuff);
			redirectAttributes.addFlashAttribute("complete","変更が完了しました");
			return "redirect:/main/stuff/"+ stuffId;
		}else {
			model.addAttribute("stuffForm",stuffForm);
			model.addAttribute("title","スタッフマスタ");
			return "StuffForm";
		}
	}
	
	@PostMapping("/stuff/delete")
	public String delete(
			@RequestParam("stuffId") int id,
			Model model) 
	{
		stuffService.deleteById(id);
		return"redirect:/main/stuff";
		
	}
	
	
	
	private Stuff makeStuff(StuffForm stuffForm,int stuffId) {
		
		Stuff stuff = new Stuff();
		if(stuffId != 0) {
			stuff.setId(stuffId);
		}
		stuff.setName(stuffForm.getName());
		stuff.setDetail(stuffForm.getDetail());
		stuff.setTypeId(stuffForm.getTypeId());
		stuff.setRegisteredId(stuffForm.getRegisteredId());
		
		return stuff;
	}
	
	private StuffForm makeStuffForm(Stuff stuff) {
		
		StuffForm stuffForm = new StuffForm();
		
		stuffForm.setId(stuff.getId());
		stuffForm.setName(stuff.getName());
		stuffForm.setDetail(stuff.getDetail());
		stuffForm.setNewStuff(false);
		stuffForm.setTypeId(stuff.getTypeId());
		stuffForm.setRegisteredId(stuff.getRegisteredId());
		
		return stuffForm;
		
		
		
	}
	
}
