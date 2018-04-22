/**
 * 
 */
package com.caveofprogramming.controllers;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.Profile;
import com.caveofprogramming.model.SiteUser;
import com.caveofprogramming.service.ProfileService;
import com.caveofprogramming.service.UserService;

/**
 * @author java_dev
 *
 */
@Controller
public class ProfileController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private PolicyFactory htmlPolicy;
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userService.get(username);
	}	
	
	@RequestMapping(value="/profile")
	public ModelAndView showProfile(ModelAndView modelAndView) {		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		if (profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);			
		}
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);
		
		modelAndView.getModel().put("profile", webProfile);		
		modelAndView.setViewName("app.profile");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.GET)
	public ModelAndView editProfileAbout(ModelAndView modelAndView) {
		SiteUser user = getUser();
		Profile  profile = profileService.getUserProfile(user);
		
		Profile webProfile = new Profile();
		webProfile.safeCopyFrom(profile);
		
		modelAndView.getModel().put("profile", webProfile);		
		modelAndView.setViewName("app.editProfileAbout");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.POST)
	public ModelAndView editProfileAbout(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {
		modelAndView.setViewName("app.editProfileAbout");
			
		if (!result.hasErrors()) {
			SiteUser user = getUser();
			Profile profile = profileService.getUserProfile(user);
			System.out.println(profile.getAbout()+"\n===================");
			profile.safeMergeFrom(webProfile, htmlPolicy);	
			profileService.save(profile);
			
			System.out.println(profile.getAbout());
			modelAndView.setViewName("redirect:/profile");
		}		
		
		return modelAndView;
	}
}
