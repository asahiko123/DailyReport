package com.example.demo.app.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.app.service.DataNotFoundException;

@ControllerAdvice
public class WebMvcControllerAdvice {



/*空文字をnullに変換する*/
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
	}
	
	
	@ExceptionHandler(DataNotFoundException.class)
	public String handleException(DataNotFoundException e,Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}
}
