package com.example.demo.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
	/*登録ページの表示*/
	
	@GetMapping("/signup")
		public ModelAndView signup(ModelAndView modelAndView) {
			modelAndView.setViewName("user/signup");
			modelAndView.addObject("userForm", new UserForm());
			
			return modelAndView;
	}
	
	@PostMapping("/signup")
	    public ModelAndView register(
	    		@Validated@ModelAttribute UserForm userForm,
	    		BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView =new ModelAndView("user/signup");
			modelAndView.addObject("userForm",userForm);
			return modelAndView;
		}
		
		return new ModelAndView("redirect:/main");
		
	}
	

}
