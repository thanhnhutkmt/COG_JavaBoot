/**
 * 
 */
package com.caveofprogramming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nhut
 *
 */
@Controller
public class PageController {
	@RequestMapping("/")	
	public String home() {
		return "app.homepage";
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
}
