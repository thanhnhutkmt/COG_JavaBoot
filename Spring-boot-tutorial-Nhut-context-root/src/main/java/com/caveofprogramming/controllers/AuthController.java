/**
 * 
 */
package com.caveofprogramming.controllers;

import java.io.FileNotFoundException;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.SiteUser;
import com.caveofprogramming.model.VerificationToken;
import com.caveofprogramming.service.EmailService;
import com.caveofprogramming.service.UserService;

/**
 * @author nhut
 *
 */
@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Value("${message.registration.confirmed}")
	private String registrationConfirmedMessage;
	
	@Value("${message.invalid.user}")
	private String invalidUserMessage;
	
	@Value("${message.expired.token}")
	private String expiredTokenMessage;
	
	@RequestMapping("/login")
	String login() {
		return "app.login";	
	}
	
	@RequestMapping("/verifyemail")
	String verifyemail() {
		return "app.verifyemail";	
	}
	
	@RequestMapping("/confirmregister")
	ModelAndView registrationConfirmed(ModelAndView modelAndView,
			@RequestParam("t") String tokenString) {		
		VerificationToken token = userService.getVerificationToken(tokenString);
				
		if (token == null) {
			modelAndView.setViewName("redirect:/invaliduser");
			return modelAndView;
		}
		
		userService.deleteToken(token);
		
		Date expiryDate = token.getExpiry();
		if (expiryDate.before(new Date())) {
			modelAndView.setViewName("redirect:/expiredtoken");
			return modelAndView;
		}
		
		SiteUser user = token.getUser();
		if (user == null) {
			modelAndView.setViewName("redirect:/invaliduser");
			return modelAndView;
		}
		
		user.setEnabled(true);
		userService.save(user);
		
		modelAndView.getModel().put("message", registrationConfirmedMessage);		
		modelAndView.setViewName("app.message");
		
		return modelAndView;	
	}
	
	@RequestMapping("/expiredtoken")
	ModelAndView expiredToken(ModelAndView modelAndView) {
		modelAndView.getModel().put("message", expiredTokenMessage);		
		modelAndView.setViewName("app.message");
		
		return modelAndView;	
	}
	
	@RequestMapping("/invaliduser")
	ModelAndView invalidUser(ModelAndView modelAndView) {
		modelAndView.getModel().put("message", invalidUserMessage);		
		modelAndView.setViewName("app.message");
		
		return modelAndView;	
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView) {		
		SiteUser user = new SiteUser();
		
		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("app.register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, 
			@ModelAttribute(value="user") @Valid SiteUser user, BindingResult result) {			
		modelAndView.setViewName("app.register");
		if (!result.hasErrors()) {			
			userService.register(user);
			String token = userService.createEmailVerificationToken(user);
			
			emailService.sendVerificationEmail1(user.getEmail(), token);
			userService.save(user);
			modelAndView.setViewName("redirect:/verifyemail");				
		}		
		return modelAndView;
	}
}
