/**
 * 
 */
package com.caveofprogramming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.caveofprogramming.service.UserService;

/**
 * @author java_dev
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/", 
					"/about", 
					"/register", 
					"/verifyemail",
					"/confirmregister",
					"/invaliduser",
					"/expiredtoken"					
					)
				.permitAll()
				.antMatchers(
					"/js/*", 
					"/css/*",
					"/img/*")
				.permitAll()
				.antMatchers("/addstatus", 
					"/editstatus", 
					"/deletestatus", 
					"/viewstatus")
				.hasRole("ADMIN")	
				.antMatchers("/profile",
					"/profile/*",
					"/edit-profile-about",
					"/upload-profile-photo",
					"/profilePhoto",
					"/profilePhoto/*",
					"/save-interest",
					"/delete-interest"
					)
					.authenticated()					
				.anyRequest()
					.denyAll()
					.and()
//				.anyRequest()                    
//					.authenticated()            
//					.and()						  
				.formLogin()                    
					.loginPage("/login")  		
					.defaultSuccessUrl("/")		 
					.permitAll()				
					.and()						
				.logout()						
					.permitAll();
		
		// @formatter:on
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
			.inMemoryAuthentication()
			.withUser("Nhut")
			.password("Nhut")		
			.roles("USER");
			
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	
}
