/**
 * 
 */
package com.caveofprogramming.controllers;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.exceptions.ImageTooSmallException;
import com.caveofprogramming.exceptions.InvalidFileException;
import com.caveofprogramming.model.FileInfo;
import com.caveofprogramming.model.Profile;
import com.caveofprogramming.model.SiteUser;
import com.caveofprogramming.service.FileService;
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
	
	@Autowired
	private FileService fileService;
	
	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
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
	
	@RequestMapping(value="/upload-profile-photo", method=RequestMethod.POST)
	public ModelAndView handlePhotoUploads(ModelAndView modelAndView, 
			@RequestParam("file") MultipartFile file) throws IOException, InvalidFileException, ImageTooSmallException {
		modelAndView.setViewName("redirect:/profile");
//		Path outputFilePath = Paths.get(photoUploadDirectory, file.getOriginalFilename());
//		Files.deleteIfExists(outputFilePath);
//		Files.copy(file.getInputStream(), outputFilePath);
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		Path oldPhotoPath = profile.getPhoto(photoUploadDirectory);
		FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "p" + user.getId(), 100, 100);		
		profile.setPhotoDetails(photoInfo);
		profileService.save(profile);
		
		if (oldPhotoPath != null)
			Files.delete(oldPhotoPath);
				
		return modelAndView;
	}
	
	@RequestMapping(value="/profilephoto", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<InputStreamResource> servePhoto() throws IOException {
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		Path photoPath = Paths.get(photoUploadDirectory, "default", "avatar.png");
		if(profile != null && profile.getPhoto(photoUploadDirectory) != null) {
			photoPath = profile.getPhoto(photoUploadDirectory);
		}
		
		return ResponseEntity
			.ok()
			.contentLength(Files.size(photoPath))
			.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
			.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
	}
}
