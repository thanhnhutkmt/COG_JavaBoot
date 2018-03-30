/**
 * 
 */
package com.caveofprogramming.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.StatusUpdate;
import com.caveofprogramming.service.StatusUpdateService;


/**
 * @author nhut
 *
 */
@Controller
public class PageController {
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/")	
	public String home() {
		return "app.homepage";
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
//	@RequestMapping(value="/addstatus", method=RequestMethod.GET)
//	ModelAndView addStatus(ModelAndView modelAndView) {
//		
//		modelAndView.setViewName("app.addStatus");
//		StatusUpdate statusUpdate = new StatusUpdate("Hello from the model", new Date());
//		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
//		
//		modelAndView.getModel().put("statusUpdate", statusUpdate);
//		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, 
		@ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {	
		
		modelAndView.setViewName("app.addStatus");
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
	
//	@RequestMapping(value="/addstatus", method=RequestMethod.POST)
//	ModelAndView addStatus(ModelAndView modelAndView, 
//			@Valid StatusUpdate statusUpdate, BindingResult result) {	
//		modelAndView.setViewName("app.addStatus");
//		
//		if (!result.hasErrors()) {
//			statusUpdateService.save(statusUpdate);
//			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
//		}
//
//		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
//		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value="/viewstatus", method=RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView, 
			@RequestParam(name="p", defaultValue="1") int pageNumber) {
//		System.out.println();
//		System.out.println("=============" + pageNumber + "=============");
//		System.out.println();
		
		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
		modelAndView.getModel().put("page", page);
		
		modelAndView.setViewName("app.viewStatus");
		return modelAndView;
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, 
			@Valid StatusUpdate statusUpdate, BindingResult result) {		
		if (!result.hasErrors()) {
			statusUpdateService.save(statusUpdate);
			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
		}
		
		addStatus(modelAndView, statusUpdate);		
		return modelAndView;
	}
}
