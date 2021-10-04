package com.example.demo.app.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.app.repository.UserAuthRepository;
import com.example.demo.app.user.UserForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthUsecase {
	
	private final UserAuthRepository authRepository;
	
	public void userCreate(UserForm form,HttpServletRequest request) throws ServletException{
		authRepository.createUser(
				form.getUsername(),
				form.getPassword()
		);
		
		request.login(form.getUsername(), form.getPassword());
	}

}
