package com.example.demo.app.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
		     .antMatchers("/main/**").authenticated()

		     .and()
		     .exceptionHandling()
		     .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
		     .accessDeniedHandler(new JWTAccessDeniedHandler())
		     

		     .and().formLogin()
		        .loginPage("/user/login").permitAll()
		        .defaultSuccessUrl("/main")
		        .failureHandler(new JWAuthenticationFailureHandler())
		     .and().logout()
		        .logoutUrl("/user/logout")
		        .logoutSuccessUrl("/user/login")
		    
		     .and().csrf().ignoringAntMatchers("/h2-console/**","/csv/**")
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
	
	public class JWAuthenticationFailureHandler implements AuthenticationFailureHandler{

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			
			String errorReason =null;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			if(exception instanceof BadCredentialsException) {
				errorReason ="ユーザー名またはパスワードが違います";
			}else {
				errorReason="不明なエラーが発生しました。管理者に連絡してください";
			}
			
			if(errorReason !=null && errorReason.length()>0){
				HttpSession session = request.getSession();
				session.setAttribute("errorReason", errorReason);
				httpResponse.sendRedirect("login");
			}
			
		}
	}
}
