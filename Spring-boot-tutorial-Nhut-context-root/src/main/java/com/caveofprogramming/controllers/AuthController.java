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
public class AuthController {
	
	@RequestMapping("/admin")
	String admin() {
		return "admin";	
	}
	
	
}
