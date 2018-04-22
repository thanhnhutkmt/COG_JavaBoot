/**
 * 
 */
package com.caveofprogramming.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author java_dev
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value("${message.error.exception}")
	private String exceptionMessage;
	
	@Value("${message.error.duplicate.user}")
	private String duplicationUserMessage;
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.getModel().put("message", exceptionMessage);
		modelAndView.getModel().put("url", req.getRequestURL());
		modelAndView.getModel().put("exception", e);
		modelAndView.setViewName("app.exception");
		
		return modelAndView;
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ModelAndView duplicateUserHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView(); 
		
		modelAndView.getModel().put("message", duplicationUserMessage);
		modelAndView.getModel().put("url", req.getRequestURL());
		modelAndView.getModel().put("exception", e);
		modelAndView.setViewName("app.exception");
		
		return modelAndView;
	}
}
