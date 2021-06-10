package com.example.demo.app.service;

public class DataNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
