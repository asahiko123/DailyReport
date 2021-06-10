package com.example.demo.app.supplier;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("name","取引先");
		model.addAttribute("list",list);
		return"SupplierForm";
	}
}
