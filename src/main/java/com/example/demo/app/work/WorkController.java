package com.example.demo.app.work;



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
	
	@GetMapping("/work/{id}")
	public String showUpdate(	
			WorkForm workForm,
			@PathVariable int id,
			Model model
		) {
		
		Optional<Work> workOpt = workService.getWork(id);
		Optional<WorkForm>workFormOpt = workOpt.map(t -> makeWorkForm(t));
		
		if(workFormOpt.isPresent()) {
			
			workForm = workFormOpt.get();
		}
		
		model.addAttribute("workForm",workForm);
		model.addAttribute("title","作業区分マスタ更新フォーム");
		List<Work> list = workService.findAll();
		model.addAttribute("list",list);
		model.addAttribute("workId",id);
		
		return "WorkForm";
	}
	
	@PostMapping("/work/insert")
	public String insert(
			@Valid@ModelAttribute WorkForm workForm,
			BindingResult result,
			Model model) {
		
		//workForm(入力欄)の内容をworkEntityにつめる
		
		Work work = makeWork(workForm,0);
		
		
		if(!result.hasErrors()){
			workService.insert(work);
			return"redirect:/main/work";
		}else {
		workForm.setNewWork(true);
		model.addAttribute("title","作業区分マスタ");
		model.addAttribute("workForm",workForm);
		List<Work> list = workService.findAll();
		model.addAttribute("list",list);
		
		return "WorkForm";
		}
	}
	
	@PostMapping("/work/update")
	public String Update(
			@Valid@ModelAttribute WorkForm workForm,
			BindingResult result,
			@RequestParam("workId")int workId,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		 
		if(!result.hasErrors()) {
			Work work = makeWork(workForm,workId);
			workService.update(work);
			redirectAttributes.addFlashAttribute("complete","変更が完了しました");
			return "redirect:/main/work/"+ workId;
		}
		else {
			model.addAttribute("workForm",workForm);
			model.addAttribute("title","作業区分マスタ");
			return "WorkForm";
		}
		
	}
	
	@PostMapping("/work/delete")
	public String delete(
			@RequestParam("workId")int id,
			Model model) {
		
		workService.deleteById(id);
		return"redirect:/main/work";
		
	}
	
	
	private Work makeWork(WorkForm workForm,int workId) {
		
		Work work = new Work();
		
		if(workId != 0) {
			work.setId(workId);
		}
		
		work.setTypeId(workForm.getTypeId());
		work.setComment(workForm.getComment());
		work.setWorkDivId(workForm.getWorkDivId());
	
		
		return work;
	}
	
	private WorkForm makeWorkForm(Work work) {
		
		WorkForm workForm = new WorkForm();
		
		workForm.setId(work.getId());
		workForm.setTypeId(work.getTypeId());
		workForm.setComment(work.getComment());
		workForm.setWorkDivId(work.getWorkDivId());
		workForm.setNewWork(false);
		
		return workForm;
	}
}
