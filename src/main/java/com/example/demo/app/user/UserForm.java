package com.example.demo.app.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm{
		@Size(max=20)
		@Pattern(regexp="^[0-9a-zA-Z]*$")
		@NotNull
		private String username;
		@Size(max=64)
		@Pattern(regexp="^[0-9a-zA-Z]*$")
		@NotNull
		private String password;
}


