package com.example.demo.app.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.app.service.UserAuthUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
	
	private final UserAuthUsecase userAuthUsecase;
	/*登録ページの表示*/
	
	@GetMapping("/login")
	public String loginPage(Model model,UserForm userForm) {
		
		model.addAttribute("userForm", userForm);
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model,UserForm userForm) {

		model.addAttribute("userForm", userForm);
		
		return "signup";
	}
	
	@PostMapping("/signup")
	    public String register(
	    		@Validated@ModelAttribute UserForm userForm,
	    		BindingResult bindingResult,
	    		HttpServletRequest request,
	    		Model model) {
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("userForm",userForm);
			return "signup";
		}
		
		try {
			userAuthUsecase.userCreate(userForm,request);
		}catch(Exception e) {
			log.error("ユーザー作成またはログインに失敗しました",e);
			return "redirect:/user/login";
		}
		
		return "redirect:/main";
		
	}
	

}
