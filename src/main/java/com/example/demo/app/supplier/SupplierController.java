package com.example.demo.app.supplier;

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

import com.example.demo.app.entity.Supplier;
import com.example.demo.app.service.SupplierService;

@Controller
@RequestMapping("/main")
public class SupplierController {
	
	private final SupplierService supplierService;
	
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@GetMapping("/supplier")
	public String Supplier(SupplierForm supplierForm, Model model) {
		
		supplierForm.setNewSupplier(true);
		List<Supplier> list = supplierService.findAll();
		
		model.addAttribute("title","取引先マスタ");
		model.addAttribute("name","取引先会社名");
		model.addAttribute("list",list);
		return"SupplierForm";
	}
	
	@GetMapping("/supplier/{id}")
	public String showUpdate(Model model,
			                  @PathVariable int id,
                              SupplierForm supplierForm) {
		
		Optional<Supplier> supplierOpt = supplierService.getSupplier(id);
		Optional<SupplierForm> supplierFormOpt = supplierOpt.map(t ->makeSupplierForm(t));
		
		if(supplierFormOpt.isPresent()) {
			supplierForm = supplierFormOpt.get();
		}
		
		model.addAttribute("title","取引先マスタ更新フォーム");
		model.addAttribute("supplierForm",supplierForm);
		List<Supplier> list = supplierService.findAll();
		model.addAttribute("list",list);
		model.addAttribute("supplierId",id);
		model.addAttribute("name","取引先会社名");
		
		return "SupplierForm";
		
	}
	
	@PostMapping("/supplier/insert")
	public String insert(
			             @Valid@ModelAttribute SupplierForm supplierForm,
			             BindingResult result,
			             Model model
			             ) {
		
		Supplier supplier = makeSupplier(supplierForm,0);
		
		if(!result.hasErrors()) {
			
			supplierService.insert(supplier);		
			return "redirect:/main/supplier";
		}else {
			
			
			supplierForm.setNewSupplier(true);
			
			List<Supplier> list = supplierService.findAll();
			model.addAttribute("SupplierForm",supplierForm);
			model.addAttribute("title","取引先マスタ");
			model.addAttribute("list",list);		
			return"SupplierForm";
		}
	}
	
	@PostMapping("/supplier/update")
	public String update(
			             @Valid@ModelAttribute SupplierForm supplierForm,
			             BindingResult result,
			             @RequestParam("supplierId")int supplierId,
			             Model model,
			             RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()) {
			Supplier supplier = makeSupplier(supplierForm,supplierId);
			supplierService.update(supplier);
			redirectAttributes.addFlashAttribute("complete","変更が完了しました");
			
			return "redirect:/main/supplier/"+ supplierId;
			
		}else {
			model.addAttribute("supplierForm",supplierForm);
			model.addAttribute("title","取引先マスタ");		
			return "SupplierForm";
		}
	}
	
	@PostMapping("/supplier/delete")
	public String delete(@RequestParam("supplierId")int id,
	                      Model model){
	                    	  
	          supplierService.deleteById(id);
	          
	          return"redirect:/main/supplier";
	          
	                      }
	
	private Supplier makeSupplier(SupplierForm supplierForm,int supplierId) {
		
		Supplier supplier = new Supplier();
		if(supplierId != 0) {
			supplier.setId(supplierId);
		}
		
		supplier.setComment(supplierForm.getComment());
		supplier.setTypeId(supplierForm.getTypeId());
		
		return supplier;
	}
	
	private SupplierForm makeSupplierForm(Supplier supplier) {
		
		SupplierForm supplierForm = new SupplierForm();
		
		supplierForm.setId(supplier.getId());
		supplierForm.setComment(supplier.getComment());
		supplierForm.setTypeId(supplier.getTypeId());
		supplierForm.setNewSupplier(false);
		
		return supplierForm;
	}
}
