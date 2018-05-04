/**
 * 
 */
package com.caveofprogramming.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.caveofprogramming.App;
import com.caveofprogramming.model.repository.UserDao;
import com.caveofprogramming.service.UserService;

/**
 * @author java_dev
 *
 */
public class UniqueCheckValidator implements ConstraintValidator<UniqueCheck, String> {
	@Autowired
	private UserDao userDao;
	
	@Override
	public void initialize(UniqueCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {			
		return (userDao == null) || userDao.findByEmail(email) == null;
	}
}
