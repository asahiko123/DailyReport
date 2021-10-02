package com.example.demo.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		  .authorizeRequests()	     
		     .antMatchers("/h2-console/**").hasRole("ADMIN")
		     .antMatchers("/main").hasRole("USER")
		     .and().formLogin()
		        .loginPage("/login").permitAll()
		        .defaultSuccessUrl("/main")
		     .and().csrf().ignoringAntMatchers("/h2-console/**")
		     .and().headers().frameOptions().sameOrigin();

		     
	}
	
	@Override
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		    .userDetailsService(userDetailsManager).passwordEncoder(passwordEncoder);

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Bean
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
