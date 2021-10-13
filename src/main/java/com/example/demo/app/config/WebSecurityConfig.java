package com.example.demo.app.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
		  .authorizeRequests()	     

		     .antMatchers("/h2-console/**").hasRole("ADMIN")
		     .antMatchers("/main/workingHour").hasRole("ADMIN")

		     .and()
		     .exceptionHandling()
		     .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
		     .accessDeniedHandler(new JWTAccessDeniedHandler())

		     .and().formLogin()
		        .loginPage("/user/login").permitAll()
		        .defaultSuccessUrl("/main")
		     .and().logout()
		        .logoutUrl("/user/logout")
		        .logoutSuccessUrl("/user/login")
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
	
	public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint{
		@Override
		public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException except)throws IOException,ServletException {
//			response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
			HttpServletResponse httpResponse = (HttpServletResponse) response;
		    httpResponse.sendRedirect("error");
		}
	}
	
	public class JWTAccessDeniedHandler implements AccessDeniedHandler {

	    @Override
	    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
	        // レスポンスの設定(403 FORBIDDEN)
	        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
	    }
	}
	
}
